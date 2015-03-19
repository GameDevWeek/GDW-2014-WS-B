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
	private static Random rnd2 = new Random();

	private static float map_x = 0;
	private static float map_y = 0;

	public static void generate(AssetManagerX assetManager, PooledEngine engine) {

		map_x = Gdx.graphics.getWidth() * GameBoardInformation.GAME_MENU_WIDTH
				+ GameBoardInformation.ARROWS_WIDTH + 50f;
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
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {

				createTile(assetManager, engine, x, y);

			}
		}

	}

	public static void createTile(AssetManagerX assetManager,
			PooledEngine engine, float x, float y) {

		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TileComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		int random = rnd.nextInt(4);
		switch (random) {

		case 0:
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y,
					// assetManager.getTexture("cross"),
					assetManager.getTexture("crossStone"));
			break;
		case 1:
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y,
					// assetManager.getTexture("tShapePurple"),
					// assetManager.getTexture("tShapeYellow"),
					assetManager.getTexture("tShapeStone"));
			break;
		case 2:
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y,
					// assetManager.getTexture("lShapeGreen"),
					// assetManager.getTexture("lShapeBrown")
					assetManager.getTexture("lShapeStone"));
			break;
		case 3:
			createTile(engine, assetManager.getTexture("backgroundStone"), x,
					y,
					// assetManager.getTexture("straightRed"),
					// assetManager.getTexture("straightWhite"),
					assetManager.getTexture("straightStone"));
			break;
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

		switch (rotation) {
		case 0:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SCALE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SCALE;
			break;
		case 1:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SCALE + 50;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SCALE;
			break;
		case 2:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SCALE + 50;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SCALE + 50f;
			break;
		case 3:
			entity.getComponent(PositionComponent.class).x = map_x + x
					* GameBoardInformation.TILE_SCALE;
			entity.getComponent(PositionComponent.class).y = map_y + y
					* GameBoardInformation.TILE_SCALE + 50f;
			break;
		}

		entity.add(engine.createComponent(PositionInLevelComponent.class));
		entity.getComponent(PositionInLevelComponent.class).x = (int) x;
		entity.getComponent(PositionInLevelComponent.class).y = (int) y;

		engine.addEntity(entity);

	}
}
