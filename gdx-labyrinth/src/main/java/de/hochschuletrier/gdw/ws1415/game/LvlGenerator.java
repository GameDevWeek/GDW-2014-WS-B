package de.hochschuletrier.gdw.ws1415.game;

import java.util.Random;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;
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

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(BackgroundComponent.class));
		entity.add(engine.createComponent(PositionComponent.class));

		entity.getComponent(BackgroundComponent.class).texture = assetManager
				.getTexture("gameBackgroundStone");
		entity.getComponent(PositionComponent.class).x = map_x;
		entity.getComponent(PositionComponent.class).y = map_y;

		engine.addEntity(entity);

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
						- GameBoardInformation.TILE_SIZE, 270f);
		}
		// arrowLeft
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, map_x
						- GameBoardInformation.TILE_SIZE, map_y + i
						* GameBoardInformation.TILE_SIZE, 180f);
		}
		// arrowRight
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, map_x
						+ GameBoardInformation.TILE_SIZE * 7, map_y + i
						* GameBoardInformation.TILE_SIZE, 0f);
		}
		// arrowBottom
		for (int i = 0; i < 7; i++) {
			if (i != 3)
				createArrow(engine, assetManager, i
						* GameBoardInformation.TILE_SIZE + map_x, map_y + 7
						* GameBoardInformation.TILE_SIZE, 90f);
		}

	}

	public static void createTile(AssetManagerX assetManager,
			PooledEngine engine, float x, float y) {

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TileComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		int random = rnd.nextInt(4);

		if (x != 3 && y != 3) {
			switch (random) {

			case 0:
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y,
						// assetManager.getTexture("cross"),
						assetManager.getTexture("crossStone"));
				break;
			case 1:
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y,
						// assetManager.getTexture("tShapePurple"),
						// assetManager.getTexture("tShapeYellow"),
						assetManager.getTexture("tShapeStone"));
				break;
			case 2:
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y,
						// assetManager.getTexture("lShapeGreen"),
						// assetManager.getTexture("lShapeBrown")
						assetManager.getTexture("lShapeStone"));
				break;
			case 3:
				createTile(engine, assetManager.getTexture("backgroundStone"),
						x, y,
						// assetManager.getTexture("straightRed"),
						// assetManager.getTexture("straightWhite"),
						assetManager.getTexture("straightStone"));
				break;
			}
		} else {
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y,
					// assetManager.getTexture("straightRed"),
					// assetManager.getTexture("straightWhite"),
					assetManager.getTexture("crossStone"));
		}
	}

	private static void createTile(PooledEngine engine, Texture background,
			float x, float y, Texture... texture) {

		Entity entity = engine.createEntity();
		int tmp = rnd.nextInt(texture.length);

		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));
		entity.add(engine.createComponent(TileComponent.class));

		int rotation = rnd.nextInt(4);

		entity.getComponent(PositionComponent.class).rotation = rotation * 90f;

		entity.getComponent(TextureComponent.class).texture = texture[tmp];
		entity.getComponent(TextureComponent.class).background = background;
		entity.getComponent(TextureComponent.class).scale = GameBoardInformation.GAME_SCALE;

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
			AssetManagerX assetManager, float x, float y, float rotation) {

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		entity.getComponent(PositionComponent.class).x = x;
		entity.getComponent(PositionComponent.class).y = y;
		entity.getComponent(PositionComponent.class).rotation = rotation;

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
}
