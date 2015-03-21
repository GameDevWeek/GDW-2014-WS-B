package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.MovementComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class MovementUtil {

	static ImmutableArray<Entity> tileEntities;
	static PooledEngine engine;
	static AssetManagerX assetManager;

	public static void init(PooledEngine engine, ImmutableArray<Entity> tE,
			AssetManagerX assetManager) {
		tileEntities = tE;
		MovementUtil.engine = engine;
		System.out.println(tileEntities.size());
		MovementUtil.assetManager = assetManager;
	}

	public static void moveH(int row, float direction) {

		GameBoardInformation.nextTileEntity
				.getComponent(PositionInLevelComponent.class).y = row;
		GameBoardInformation.nextTileEntity
				.getComponent(PositionComponent.class).y = row
				* GameBoardInformation.TILE_SIZE + LvlGenerator.map_y;
		switch ((int) direction) {
		case -1:
			// nach links
			GameBoardInformation.nextTileEntity
					.getComponent(PositionInLevelComponent.class).x = 7;
			GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).x = 7
					* GameBoardInformation.TILE_SIZE + LvlGenerator.map_x;
			switch ((int) GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).rotation) {
			case 90:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 180:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 270:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			}

			break;
		case 1:
			// nach rechts
			GameBoardInformation.nextTileEntity
					.getComponent(PositionInLevelComponent.class).x = -1;
			GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).x = -1
					* GameBoardInformation.TILE_SIZE + LvlGenerator.map_x;

			switch ((int) GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).rotation) {
			case 90:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 180:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 270:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			}

		}

		for (Entity entity : tileEntities) {

			if (entity.getComponent(PositionInLevelComponent.class).y == row) {
				MovementComponent moveComponent = engine
						.createComponent(MovementComponent.class);
				entity.add(moveComponent);
				moveComponent.originX = entity
						.getComponent(PositionComponent.class).x;
				moveComponent.originY = entity
						.getComponent(PositionComponent.class).y;
				moveComponent.destinationX = entity
						.getComponent(PositionComponent.class).x
						+ GameBoardInformation.TILE_SIZE * direction;
				moveComponent.destinationY = entity
						.getComponent(PositionComponent.class).y;
				moveComponent.direction = direction;
				entity.getComponent(PositionInLevelComponent.class).x += direction;
				if (entity.getComponent(PositionInLevelComponent.class).x > 6
						|| entity.getComponent(PositionInLevelComponent.class).x < 0) {
					engine.removeEntity(entity);
				}
			}

		}
		// neues Tile links oben erzeugen, brauch ich nachher wieder !!
//		GameBoardInformation.nextTileEntity = LvlGenerator.createTile(
//				assetManager, engine, -5.2f, 0f);
		GameBoardInformation.CLICKABLE = false;

	}

	public static void moveV(int column, float direction) {

		GameBoardInformation.nextTileEntity
				.getComponent(PositionInLevelComponent.class).x = column;
		GameBoardInformation.nextTileEntity
				.getComponent(PositionComponent.class).x = column
				* GameBoardInformation.TILE_SIZE + LvlGenerator.map_x;
		switch ((int) direction) {
		case -1:
			// nach oben
			GameBoardInformation.nextTileEntity
					.getComponent(PositionInLevelComponent.class).y = 7;
			GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).y = 7
					* GameBoardInformation.TILE_SIZE + LvlGenerator.map_y;
			switch ((int) GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).rotation) {
			case 90:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 180:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 270:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			}

			break;
		case 1:
			// nach unten
			GameBoardInformation.nextTileEntity
					.getComponent(PositionInLevelComponent.class).y = -1;
			GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).y = -1
					* GameBoardInformation.TILE_SIZE + LvlGenerator.map_y;

			switch ((int) GameBoardInformation.nextTileEntity
					.getComponent(PositionComponent.class).rotation) {
			case 90:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 180:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
				break;
			case 270:
				GameBoardInformation.nextTileEntity
						.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
			}

		}

		for (Entity entity : tileEntities) {

			if (entity.getComponent(PositionInLevelComponent.class).x == column) {
				MovementComponent moveComponent = engine
						.createComponent(MovementComponent.class);
				entity.add(moveComponent);
				moveComponent.originX = entity
						.getComponent(PositionComponent.class).x;
				moveComponent.originY = entity
						.getComponent(PositionComponent.class).y;
				moveComponent.destinationX = entity
						.getComponent(PositionComponent.class).x;
				moveComponent.destinationY = entity
						.getComponent(PositionComponent.class).y
						+ GameBoardInformation.TILE_SIZE * direction;
				moveComponent.direction = direction;
				entity.getComponent(PositionInLevelComponent.class).y += direction;
				if (entity.getComponent(PositionInLevelComponent.class).y > 6
						|| entity.getComponent(PositionInLevelComponent.class).y < 0) {
					engine.removeEntity(entity);
				}
			}

		}
		// neues Tile links oben erzeugen, brauch ich nachher wieder !!
//		GameBoardInformation.nextTileEntity = LvlGenerator.createTile(
//				assetManager, engine, -5.2f, 0f);

		GameBoardInformation.CLICKABLE = false;
	}
}
