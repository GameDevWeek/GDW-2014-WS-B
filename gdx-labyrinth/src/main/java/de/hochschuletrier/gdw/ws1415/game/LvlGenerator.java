package de.hochschuletrier.gdw.ws1415.game;

import java.util.Random;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class LvlGenerator {

	private static Random rnd = new Random();

	private static float map_x = 0;
	private static float map_y = 0;

	public static void generate(AssetManagerX assetManager, PooledEngine engine) {

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
				createTile(assetManager, engine, x, y);
			}
		}

		// arrowTop
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, i
						* GameBoardInformation.TILE_SIZE + map_x, map_y
						- GameBoardInformation.TILE_SIZE, 270f, i, -1);
		}
		// arrowLeft
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, map_x
						- GameBoardInformation.TILE_SIZE, map_y + i
						* GameBoardInformation.TILE_SIZE, 180f, -1, i);
		}
		// arrowRight
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, map_x
						+ GameBoardInformation.TILE_SIZE * 7, map_y + i
						* GameBoardInformation.TILE_SIZE, 0f, 7, i);
		}
		// arrowBottom
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, i
						* GameBoardInformation.TILE_SIZE + map_x, map_y + 7
						* GameBoardInformation.TILE_SIZE, 90f, i, 7);
		}

	}

	public static void createTile(AssetManagerX assetManager,
			PooledEngine engine, float x, float y) {

		int random = rnd.nextInt(4);

		if ((x != 3) || (y != 3)) {

			switch (random) {

			case 0:
				int[] rotationDataCross = { 1, 1, 1, 1 };
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y, rotationDataCross,
						// assetManager.getTexture("cross"),
						assetManager.getTexture("crossStone"));
				break;
			case 1:
				int[] rotationDataT = { 1, 1, 0, 1 };
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y, rotationDataT,
						// assetManager.getTexture("tShapePurple"),
						// assetManager.getTexture("tShapeYellow"),
						assetManager.getTexture("tShapeStone"));
				break;
			case 2:
				int[] rotationDataL = { 1, 1, 0, 0 };
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y, rotationDataL,
						// assetManager.getTexture("lShapeGreen"),
						// assetManager.getTexture("lShapeBrown")
						assetManager.getTexture("lShapeStone"));
				break;
			case 3:
				int[] rotationDataStraight = { 1, 0, 1, 0 };
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y, rotationDataStraight,
						// assetManager.getTexture("straightRed"),
						// assetManager.getTexture("straightWhite"),
						assetManager.getTexture("straightStone"));
				break;
			}
		} else {
			int[] rotationDataStraightDef = { 1, 1, 1, 1 };
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y, rotationDataStraightDef,
					// assetManager.getTexture("straightRed"),
					// assetManager.getTexture("straightWhite"),
					assetManager.getTexture("crossStone"));
		}
	}

	private static void createTile(PooledEngine engine, Texture background,
			float x, float y, int[] rotationData, Texture... texture) {

		Entity entity = engine.createEntity();
		int tmp = rnd.nextInt(texture.length);

		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(TileComponent.class));

		int rotation = rnd.nextInt(4);
		entity.getComponent(TileComponent.class).rotationData = rotationData;
		entity.getComponent(PositionComponent.class).rotation = rotation * 90f;
		entity.getComponent(TileComponent.class).rotate(rotation * 90f);
		entity.getComponent(TextureComponent.class).texture = texture[tmp];
		entity.getComponent(TextureComponent.class).background = background;
		entity.getComponent(TextureComponent.class).scale = GameBoardInformation.GAME_SCALE;

//		for (int i = 0; i < 4; i++) {
//			System.out.print(""
//					+ entity.getComponent(TileComponent.class).rotationData[i] + ", ");
//		}
//		System.out.println("");

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
	}

	public static void createArrow(PooledEngine engine,
			AssetManagerX assetManager, float x, float y, float rotation,
			int xInlvl, int yInlvl) {

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(InputComponent.class));
		entity.add(engine.createComponent(PositionInLevelComponent.class));

		entity.getComponent(PositionComponent.class).x = x;
		entity.getComponent(PositionComponent.class).y = y;
		entity.getComponent(PositionComponent.class).rotation = rotation;

		entity.getComponent(PositionInLevelComponent.class).x = xInlvl;
		entity.getComponent(PositionInLevelComponent.class).y = yInlvl;

		entity.getComponent(InputComponent.class).active = true;

		switch ((int) rotation) {

		case 90:
			entity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
			break;
		case 180:
			entity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			break;
		case 270:
			entity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			break;
		}

		entity.getComponent(TextureComponent.class).scale = GameBoardInformation.GAME_SCALE;
		entity.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("arrow");

		engine.addEntity(entity);

	}

	public static void createPlayer(PooledEngine engine, String name,
			Color color, int playerNumber) {
		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PlayerInformationComponent.class));
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		entity.getComponent(PlayerInformationComponent.class).name = name;
		entity.getComponent(PlayerInformationComponent.class).color = color;
		entity.getComponent(PlayerInformationComponent.class).playerNumber = playerNumber;

		switch (playerNumber) {
		case 1:
			entity.getComponent(PositionComponent.class).x = map_x;
			entity.getComponent(PositionComponent.class).y = map_y;
			break;
		case 2:
			entity.getComponent(PositionComponent.class).x = map_x
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y;
			break;
		case 3:
			entity.getComponent(PositionComponent.class).x = map_x;
			entity.getComponent(PositionComponent.class).y = map_y
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			break;
		case 4:
			entity.getComponent(PositionComponent.class).x = map_x
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			entity.getComponent(PositionComponent.class).y = map_y
					+ (GameBoardInformation.NUMBER_OF_TILE - 1)
					* GameBoardInformation.TILE_SIZE;
			break;
		}

		engine.addEntity(entity);
	}
}
