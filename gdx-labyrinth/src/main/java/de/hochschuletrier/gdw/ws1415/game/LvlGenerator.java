package de.hochschuletrier.gdw.ws1415.game;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileType;

public class LvlGenerator {

	private static Random rnd = new Random();
	private static Random rnd2 = new Random();

	public static void generate(AssetManagerX assetManager, PooledEngine engine) {
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

		// randomTile
		// double random = Math.random();
		// double random2 = Math.random();

		int random = rnd.nextInt(3);
		int random2 = rnd2.nextInt(1);
		if (random == 0) {
			System.out.println("cross");
			entity.getComponent(TileComponent.class).tileType = TileType.cross;
			entity.getComponent(TextureComponent.class).texture = assetManager
					.getTexture("cross");
		} else if (random == 1) {

			System.out.println("straight");
			entity.getComponent(TileComponent.class).tileType = TileType.straight;
			if (random2 == 0) {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("straightRed");
			} else {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("straightWhite");
			}
		} else if (random == 2) {

			System.out.println("lShape");
			entity.getComponent(TileComponent.class).tileType = TileType.lShape;
			if (random2 == 0) {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("lShapeGreen");
			} else {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("lShapeBrown");
			}
		} else {
			System.out.println("tShape");
			entity.getComponent(TileComponent.class).tileType = TileType.tShape;
			if (random2 == 0) {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("tShapePurple");
			} else {
				entity.getComponent(TextureComponent.class).texture = assetManager
						.getTexture("tShapeYellow");
			}
		}

		entity.getComponent(TextureComponent.class).background = assetManager
				.getTexture("background");

		entity.getComponent(PositionComponent.class).x = x * 50 + 350f;
		entity.getComponent(PositionComponent.class).y = y * 50 + 100f;

		random = rnd.nextInt(3);

		if (random == 0) {
			entity.getComponent(PositionComponent.class).rotation = 0f;
		} else if (random == 1) {
			entity.getComponent(PositionComponent.class).rotation = 90f;
			entity.getComponent(PositionComponent.class).x += 50;
		} else if (random == 2) {
			entity.getComponent(PositionComponent.class).rotation = 180;
			entity.getComponent(PositionComponent.class).x += 50;
			entity.getComponent(PositionComponent.class).y += 50;
		} else {
			entity.getComponent(PositionComponent.class).rotation = 270f;

			entity.getComponent(PositionComponent.class).y += 50;
		}

		entity.add(engine.createComponent(PositionInLevelComponent.class));
		entity.getComponent(PositionInLevelComponent.class).x = (int) x;
		entity.getComponent(PositionInLevelComponent.class).y = (int) y;
		System.out.println(random + "    " + random2);
		engine.addEntity(entity);
	}
	
	private static void createTile(Engine engine, Texture texture, float x, float y, Float rotation) {
	    
	}
}
