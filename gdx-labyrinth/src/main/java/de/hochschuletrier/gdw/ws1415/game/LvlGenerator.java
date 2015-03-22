package de.hochschuletrier.gdw.ws1415.game;

import java.util.Random;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;
import de.hochschuletrier.gdw.ws1415.game.components.DirectionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.ItemComponent;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent.clickAction;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.RotationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class LvlGenerator {

	private static Random rnd = new Random();

	public static float map_x = 0;
	public static float map_y = 0;

	private static AssetManagerX assetManager = null;

	public static void generate(AssetManagerX assetManager, PooledEngine engine) {

		LvlGenerator.assetManager = assetManager;

		map_x = Gdx.graphics.getWidth() * GameBoardInformation.GAME_MENU_WIDTH
				+ GameBoardInformation.ARROWS_WIDTH;

		map_y = GameBoardInformation.ARROWS_HEIGHT;

		if (GameConstants.p1present) {
			createPlayer(engine, GameConstants.playerNames[0], Color.RED, 1);
		}

		if (GameConstants.p2present) {
			createPlayer(engine, GameConstants.playerNames[1], Color.GREEN, 2);
		}

		if (GameConstants.p3present) {
			createPlayer(engine, GameConstants.playerNames[2], Color.BLUE, 3);
		}

		if (GameConstants.p4present) {
			createPlayer(engine, GameConstants.playerNames[3], Color.YELLOW, 4);
		}

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(BackgroundComponent.class));
		entity.add(engine.createComponent(PositionComponent.class));

		entity.getComponent(BackgroundComponent.class).texture = assetManager
				.getTexture("gameBackgroundStone");
		entity.getComponent(PositionComponent.class).x = map_x;
		entity.getComponent(PositionComponent.class).y = map_y;
		entity.getComponent(BackgroundComponent.class).width = 700f;
		entity.getComponent(BackgroundComponent.class).height = 700f;
		entity.getComponent(BackgroundComponent.class).scale_width = GameBoardInformation.GAME_SCALE;
		entity.getComponent(BackgroundComponent.class).scale_height = GameBoardInformation.GAME_SCALE;

		engine.addEntity(entity);
		
		Entity menuButton = engine.createEntity();
		menuButton.add(engine.createComponent(TextureComponent.class));
		menuButton.add(engine.createComponent(PositionComponent.class));
		menuButton.getComponent(TextureComponent.class).texture = assetManager.getTexture("back");
		menuButton.getComponent(PositionComponent.class).x = 190;
		menuButton.getComponent(PositionComponent.class).y = 20;
		engine.addEntity(menuButton);
		
		Entity zahnrad = engine.createEntity();
		zahnrad.add(engine.createComponent(TextureComponent.class));
		zahnrad.add(engine.createComponent(PositionComponent.class));
		zahnrad.add(engine.createComponent(InputComponent.class));
		zahnrad.getComponent(TextureComponent.class).texture = assetManager.getTexture("zahnrad");
		zahnrad.getComponent(PositionComponent.class).x = 200;
		zahnrad.getComponent(PositionComponent.class).y = 26;
		zahnrad.getComponent(InputComponent.class).active = true;
		zahnrad.getComponent(InputComponent.class).action = clickAction.MENU;
        engine.addEntity(zahnrad);

		Entity goal = engine.createEntity();
		goal.add(engine.createComponent(PositionComponent.class));
		goal.add(engine.createComponent(PositionInLevelComponent.class));
		goal.add(engine.createComponent(TextureComponent.class));
		goal.add(engine.createComponent(ItemComponent.class));

		goal.getComponent(PositionInLevelComponent.class).x = 3;
		goal.getComponent(PositionInLevelComponent.class).y = 3;
		goal.getComponent(PositionComponent.class).x = map_x + 3
				* GameBoardInformation.TILE_SIZE + 8;
		goal.getComponent(PositionComponent.class).y = map_y + 3
				* GameBoardInformation.TILE_SIZE + 8;
		goal.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("ziel");
		engine.addEntity(goal);

		Entity menu_Background = engine.createEntity();
		menu_Background.add(engine.createComponent(BackgroundComponent.class));
		menu_Background.add(engine.createComponent(PositionComponent.class));

		menu_Background.getComponent(BackgroundComponent.class).texture = assetManager
				.getTexture("menu_ingame");
		menu_Background.getComponent(PositionComponent.class).x = 0.0f;
		menu_Background.getComponent(PositionComponent.class).y = 0.0f;
		menu_Background.getComponent(BackgroundComponent.class).width = 424.0f; //
		menu_Background.getComponent(BackgroundComponent.class).height = 900.0f;
		menu_Background.getComponent(BackgroundComponent.class).scale_width = (float) Math
				.ceil(Gdx.graphics.getWidth()
						* GameBoardInformation.GAME_MENU_WIDTH) / 424.0f;
		menu_Background.getComponent(BackgroundComponent.class).scale_height = Gdx.graphics
				.getHeight() / 900.0f;

		engine.addEntity(menu_Background);

		// LevelGeneration
		for (int y = 0; y < GameBoardInformation.NUMBER_OF_TILE; y++) {
			for (int x = 0; x < GameBoardInformation.NUMBER_OF_TILE; x++) {
				createTile(engine, x, y);
			}
		}

		// wird mit GameLap initialisiert.
		// GameBoardInformation.nextTileEntity = createTile(engine,
		// -5.2f, 0f);

		// Rotation Button
		rotationButton(engine);

		// Movement Button
		createMovementArray(engine);

		// arrowTop
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, i * GameBoardInformation.TILE_SIZE + map_x,
						map_y - GameBoardInformation.TILE_SIZE, 270f, i, -1);
		}
		// arrowLeft
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, map_x - GameBoardInformation.TILE_SIZE,
						map_y + i * GameBoardInformation.TILE_SIZE, 180f, -1, i);
		}
		// arrowRight
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, map_x + GameBoardInformation.TILE_SIZE * 7,
						map_y + i * GameBoardInformation.TILE_SIZE, 0f, 7, i);
		}
		// arrowBottom
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, i * GameBoardInformation.TILE_SIZE + map_x,
						map_y + 7 * GameBoardInformation.TILE_SIZE, 90f, i, 7);
		}
	}

	public static Entity createTile(PooledEngine engine, float x, float y) {

		// Entity entity = engine.createEntity();
		// entity.add(engine.createComponent(PositionComponent.class));
		// entity.add(engine.createComponent(TileComponent.class));
		// entity.add(engine.createComponent(TextureComponent.class));

		int random = rnd.nextInt(4);

		if (x == 3 && y == 3) {
			random = 0;
		} else if ((x == 0 && y == 0) || (x == 0 && y == 6)
				|| (x == 6 && y == 0) || (x == 6 && y == 6)) {
			random = 2;
		}

		switch (random) {

		case 0:
			int[] rotationDataCross = { 1, 1, 1, 1 };
			return createTile(engine,
					assetManager.getTexture("backgroundStone"), x, y,
					rotationDataCross,
					// assetManager.getTexture("cross"),
					assetManager.getTexture("crossStone"));
		case 1:
			int[] rotationDataT = { 1, 1, 0, 1 };
			return createTile(engine,
					assetManager.getTexture("backgroundStone"), x, y,
					rotationDataT,
					// assetManager.getTexture("tShapePurple"),
					// assetManager.getTexture("tShapeYellow"),
					assetManager.getTexture("tShapeStone"));
		case 2:
			int[] rotationDataL = { 1, 1, 0, 0 };
			return createTile(engine,
					assetManager.getTexture("backgroundStone"), x, y,
					rotationDataL,
					// assetManager.getTexture("lShapeGreen"),
					// assetManager.getTexture("lShapeBrown")
					assetManager.getTexture("lShapeStone"));
		case 3:
			int[] rotationDataStraight = { 1, 0, 1, 0 };
			return createTile(engine,
					assetManager.getTexture("backgroundStone"), x, y,
					rotationDataStraight,
					// assetManager.getTexture("straightRed"),
					// assetManager.getTexture("straightWhite"),
					assetManager.getTexture("straightStone"));
		default:
			return null;
		}
		// else {
		// int[] rotationDataStraightDef = { 1, 1, 1, 1 };
		// return createTile(engine, assetManager.getTexture("backgroundStone"),
		// x,
		// y, rotationDataStraightDef,
		// // assetManager.getTexture("straightRed"),
		// // assetManager.getTexture("straightWhite"),
		// assetManager.getTexture("crossStone"));
		// }
	}

	private static Entity createTile(PooledEngine engine, Texture background,
			float x, float y, int[] rotationData, Texture... texture) {

		Entity entity = engine.createEntity();
		int tmp = rnd.nextInt(texture.length);

		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(TileComponent.class));

		int rotation = rnd.nextInt(4);

		if (x == 0 && y == 0) {
			rotation = 1;
		} else if (x == 0 && y == 6) {
			rotation = 0;
		} else if (x == 6 && y == 0) {
			rotation = 2;
		} else if (x == 6 && y == 6) {
			rotation = 3;
		}

		entity.getComponent(TileComponent.class).rotationData = rotationData;
		entity.getComponent(PositionComponent.class).rotation = rotation * 90f;
		entity.getComponent(TileComponent.class).rotate(rotation * 90f);
		entity.getComponent(TextureComponent.class).texture = texture[tmp];
		entity.getComponent(TextureComponent.class).background = background;
		entity.getComponent(TextureComponent.class).scale = GameBoardInformation.GAME_SCALE;

		// for (int i = 0; i < 4; i++) {
		// System.out.print(""
		// + entity.getComponent(TileComponent.class).rotationData[i] + ", ");
		// }
		// System.out.println("");

		switch (rotation) {
		case 0:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SIZE;
			break;
		case 1:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SIZE
					+ GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SIZE;
			break;
		case 2:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SIZE
					+ GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SIZE
					+ GameBoardInformation.TILE_SIZE;
			break;
		case 3:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SIZE
					+ GameBoardInformation.TILE_SIZE;
			break;
		}

		entity.add(engine.createComponent(PositionInLevelComponent.class));
		entity.getComponent(PositionInLevelComponent.class).x = (int) x;
		entity.getComponent(PositionInLevelComponent.class).y = (int) y;

		engine.addEntity(entity);
		return entity;
	}

	public static void createArrow(PooledEngine engine, float x, float y,
			float rotation, int xInlvl, int yInlvl) {

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(InputComponent.class));
		entity.add(engine.createComponent(PositionInLevelComponent.class));
		entity.add(engine.createComponent(SpeciesComponent.class));

		entity.getComponent(PositionComponent.class).x = x;
		entity.getComponent(PositionComponent.class).y = y;
		entity.getComponent(PositionComponent.class).rotation = rotation;

		entity.getComponent(PositionInLevelComponent.class).x = xInlvl;
		entity.getComponent(PositionInLevelComponent.class).y = yInlvl;

		entity.getComponent(InputComponent.class).active = false;

		entity.getComponent(SpeciesComponent.class).isSpecies = SpeciesComponent.species.ARROW;

		switch ((int) rotation) {
		case 0:
			entity.getComponent(PositionComponent.class).y += 0.3f * GameBoardInformation.TILE_SIZE;
			break;
		case 90:
			entity.getComponent(PositionComponent.class).x += 0.7f * GameBoardInformation.TILE_SIZE;
			break;
		case 180:
			entity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y += 0.7f * GameBoardInformation.TILE_SIZE;
			break;
		case 270:
			entity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).x += 0.3f * GameBoardInformation.TILE_SIZE;
			break;
		}

		entity.getComponent(TextureComponent.class).scale = 1.0f;
		entity.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("RED_ARROW");
		entity.getComponent(TextureComponent.class).visible = false;

		engine.addEntity(entity);

	}

	public static void createPlayer(PooledEngine engine, String name,
			Color color, int playerNumber) {
		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PlayerInformationComponent.class));
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(PositionInLevelComponent.class));

		entity.getComponent(PlayerInformationComponent.class).name = name;
		entity.getComponent(PlayerInformationComponent.class).color = color;
		entity.getComponent(PlayerInformationComponent.class).playerNumber = playerNumber;

		switch (playerNumber) {
		case 1:
			entity.getComponent(PositionComponent.class).x = map_x;
			entity.getComponent(PositionComponent.class).y = map_y;
			entity.getComponent(PositionInLevelComponent.class).x = 0;
			entity.getComponent(PositionInLevelComponent.class).y = 0;
			entity.getComponent(PlayerInformationComponent.class).arrow = assetManager
					.getTexture("RED_ARROW");
			entity.getComponent(TextureComponent.class).texture = assetManager
					.getTexture("RED_PLAYER");
			break;
		case 2:
			entity.getComponent(PositionComponent.class).x = map_x
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y;
			entity.getComponent(PositionInLevelComponent.class).x = 6;
			entity.getComponent(PositionInLevelComponent.class).y = 0;
			entity.getComponent(PlayerInformationComponent.class).arrow = assetManager
					.getTexture("GREEN_ARROW");
			entity.getComponent(TextureComponent.class).texture = assetManager
					.getTexture("GREEN_PLAYER");
			break;
		case 3:
			entity.getComponent(PositionComponent.class).x = map_x;
			entity.getComponent(PositionComponent.class).y = map_y
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionInLevelComponent.class).x = 0;
			entity.getComponent(PositionInLevelComponent.class).y = 6;
			entity.getComponent(PlayerInformationComponent.class).arrow = assetManager
					.getTexture("BLUE_ARROW");
			entity.getComponent(TextureComponent.class).texture = assetManager
					.getTexture("BLUE_PLAYER");
			break;
		case 4:
			entity.getComponent(PositionComponent.class).x = map_x
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionInLevelComponent.class).x = 6;
			entity.getComponent(PositionInLevelComponent.class).y = 6;
			entity.getComponent(PlayerInformationComponent.class).arrow = assetManager
					.getTexture("YELLOW_ARROW");
			entity.getComponent(TextureComponent.class).texture = assetManager
					.getTexture("YELLOW_PLAYER");
			break;
		}

		engine.addEntity(entity);
	}

	private static void rotationButton(PooledEngine engine) {
		Entity rotationArrow = engine.createEntity();
		rotationArrow.add(engine.createComponent(PositionComponent.class));
		rotationArrow.add(engine.createComponent(InputComponent.class));
		rotationArrow.add(engine.createComponent(TextureComponent.class));
		rotationArrow.add(engine.createComponent(SpeciesComponent.class));
		rotationArrow.add(engine.createComponent(RotationComponent.class));

		rotationArrow.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("ROTATION_Button_LEFT");
		rotationArrow.getComponent(PositionComponent.class).x = 50;
		rotationArrow.getComponent(PositionComponent.class).y = 95;

		rotationArrow.getComponent(PositionComponent.class).rotation = 0;
		rotationArrow.getComponent(RotationComponent.class).rotate = 90.0f;

		rotationArrow.getComponent(SpeciesComponent.class).isSpecies = SpeciesComponent.species.ROTATION_ARROW_LEFT;

		rotationArrow.getComponent(InputComponent.class).action = InputComponent.clickAction.ROTATION_LEFT;
		rotationArrow.getComponent(InputComponent.class).active = true;
		engine.addEntity(rotationArrow);

		Entity rotationArrow2 = engine.createEntity();
		rotationArrow2.add(engine.createComponent(PositionComponent.class));
		rotationArrow2.add(engine.createComponent(InputComponent.class));
		rotationArrow2.add(engine.createComponent(TextureComponent.class));
		rotationArrow2.add(engine.createComponent(SpeciesComponent.class));
		rotationArrow2.add(engine.createComponent(RotationComponent.class));

		rotationArrow2.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("ROTATION_Button_RIGHT");
		rotationArrow2.getComponent(PositionComponent.class).x = 180;
		rotationArrow2.getComponent(PositionComponent.class).y = 100;

		rotationArrow2.getComponent(PositionComponent.class).rotation = 0;
		rotationArrow2.getComponent(RotationComponent.class).rotate = -90.0f;

		rotationArrow2.getComponent(SpeciesComponent.class).isSpecies = SpeciesComponent.species.ROTATION_ARROW_RIGHT;

		rotationArrow2.getComponent(InputComponent.class).action = InputComponent.clickAction.ROTATION_RIGHT;
		rotationArrow2.getComponent(InputComponent.class).active = true;
		engine.addEntity(rotationArrow2);

	}

	public static void createMovementArray(PooledEngine engine) {
		for (int i = 0; i < 4; i++) {
			Entity movementArrow = engine.createEntity();
			movementArrow.add(engine.createComponent(PositionComponent.class));
			movementArrow.add(engine.createComponent(InputComponent.class));
			movementArrow.add(engine.createComponent(TextureComponent.class));
			movementArrow.add(engine.createComponent(SpeciesComponent.class));
			movementArrow.add(engine.createComponent(DirectionComponent.class));

			movementArrow.getComponent(SpeciesComponent.class).isSpecies = SpeciesComponent.species.MOVEMENT_ARROW;

			movementArrow.getComponent(TextureComponent.class).visible = false;

			movementArrow.getComponent(InputComponent.class).action = InputComponent.clickAction.MOVEMENT;
			engine.addEntity(movementArrow);
		}
	}
}
