package de.hochschuletrier.gdw.ws1415.game.utils;

import java.util.HashMap;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;

public class PlayerMovement {

	public static Entity getTileByPos(int x, int y, PooledEngine engine) {

		int tilePosX;
		int tilePosY;
		Entity tileToReturn = null;

		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> tileList = engine.getEntitiesFor(Family
				.all(PositionComponent.class, TextureComponent.class,
						PositionInLevelComponent.class, TileComponent.class)
				.exclude(PlayerInformationComponent.class).get());

		for (Entity entity : tileList) {
			tilePosX = entity.getComponent(PositionInLevelComponent.class).x;
			tilePosY = entity.getComponent(PositionInLevelComponent.class).y;

			if ((tilePosX == x) && (tilePosY == y)) {
				tileToReturn = entity;
			}
		}
		return tileToReturn;
	}

	public static Entity getTileOfPlayer(Entity player, PooledEngine engine) {

		int playerPosX = player.getComponent(PositionInLevelComponent.class).x;
		int playerPosY = player.getComponent(PositionInLevelComponent.class).y;
		int tilePosX;
		int tilePosY;
		Entity tileOfPlayer = null;

		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> tileList = engine.getEntitiesFor(Family
				.all(PositionComponent.class, TextureComponent.class,
						PositionInLevelComponent.class, TileComponent.class)
				.exclude(PlayerInformationComponent.class).get());

		for (Entity entity : tileList) {
			tilePosX = entity.getComponent(PositionInLevelComponent.class).x;
			tilePosY = entity.getComponent(PositionInLevelComponent.class).y;

			if ((tilePosX == playerPosX) && (tilePosY == playerPosY)) {
				tileOfPlayer = entity;
			}
		}
		return tileOfPlayer;
	}

	public static int checkUp(Entity player, PooledEngine engine) {
		Entity currentTile = PlayerMovement.getTileOfPlayer(player, engine);
		Entity nextTile;

		int moveCount = 0;

		int currentTilePosX = currentTile
				.getComponent(PositionInLevelComponent.class).x;
		int currentTilePosY = currentTile
				.getComponent(PositionInLevelComponent.class).y;

		int nextTilePosX = currentTilePosX;
		int nextTilePosY = currentTilePosY - 1;

		int[] currentRotationData;
		int[] nextRotationData;

		while (nextTilePosY >= 0) {
			nextTile = PlayerMovement.getTileByPos(nextTilePosX, nextTilePosY,
					engine);

			currentRotationData = currentTile.getComponent(TileComponent.class).rotationData;
			nextRotationData = nextTile.getComponent(TileComponent.class).rotationData;

			if ((currentRotationData[0] == 1) && (nextRotationData[2] == 1)) {
				moveCount++;
			} else {
				break;
			}

			currentTile = nextTile;
			nextTilePosY -= 1;
		}

		return moveCount;
	}

	public static int checkRight(Entity player, PooledEngine engine) {
		Entity currentTile = PlayerMovement.getTileOfPlayer(player, engine);
		Entity nextTile;

		int moveCount = 0;

		int currentTilePosX = currentTile
				.getComponent(PositionInLevelComponent.class).x;
		int currentTilePosY = currentTile
				.getComponent(PositionInLevelComponent.class).y;

		int nextTilePosX = currentTilePosX + 1;
		int nextTilePosY = currentTilePosY;

		int[] currentRotationData;
		int[] nextRotationData;

		while (nextTilePosX <= 6) {

			nextTile = PlayerMovement.getTileByPos(nextTilePosX, nextTilePosY,
					engine);

			currentRotationData = currentTile.getComponent(TileComponent.class).rotationData;
			nextRotationData = nextTile.getComponent(TileComponent.class).rotationData;

			if ((currentRotationData[1] == 1) && (nextRotationData[3] == 1)) {
				moveCount++;
			} else {
				break;
			}

			currentTile = nextTile;
			nextTilePosX += 1;
		}

		return moveCount;
	}

	public static int checkDown(Entity player, PooledEngine engine) {
		Entity currentTile = PlayerMovement.getTileOfPlayer(player, engine);
		Entity nextTile;

		int moveCount = 0;

		int currentTilePosX = currentTile
				.getComponent(PositionInLevelComponent.class).x;
		int currentTilePosY = currentTile
				.getComponent(PositionInLevelComponent.class).y;

		int nextTilePosX = currentTilePosX;
		int nextTilePosY = currentTilePosY + 1;

		int[] currentRotationData;
		int[] nextRotationData;

		while (nextTilePosY <= 6) {
			nextTile = PlayerMovement.getTileByPos(nextTilePosX, nextTilePosY,
					engine);

			currentRotationData = currentTile.getComponent(TileComponent.class).rotationData;
			nextRotationData = nextTile.getComponent(TileComponent.class).rotationData;

			if ((currentRotationData[2] == 1) && (nextRotationData[0] == 1)) {
				moveCount++;
			} else {
				break;
			}

			currentTile = nextTile;
			nextTilePosY += 1;
		}

		return moveCount;
	}

	public static int checkLeft(Entity player, PooledEngine engine) {
		Entity currentTile = PlayerMovement.getTileOfPlayer(player, engine);
		Entity nextTile;

		int moveCount = 0;

		int currentTilePosX = currentTile
				.getComponent(PositionInLevelComponent.class).x;
		int currentTilePosY = currentTile
				.getComponent(PositionInLevelComponent.class).y;

		int nextTilePosX = currentTilePosX - 1;
		int nextTilePosY = currentTilePosY;

		int[] currentRotationData;
		int[] nextRotationData;

		while (nextTilePosX >= 0) {
			nextTile = PlayerMovement.getTileByPos(nextTilePosX, nextTilePosY,
					engine);

			currentRotationData = currentTile.getComponent(TileComponent.class).rotationData;
			nextRotationData = nextTile.getComponent(TileComponent.class).rotationData;

			if ((currentRotationData[3] == 1) && (nextRotationData[1] == 1)) {
				moveCount++;
			}
			else {
				break;
			}

			currentTile = nextTile;
			nextTilePosX -= 1;
		}

		return moveCount;
	}

	public static HashMap<MovementDestinations, Integer> checkPaths(
			Entity player, PooledEngine engine) {

		// check Up
		int upDistance = PlayerMovement.checkUp(player, engine);
		// checkRight
		int rightDistance = PlayerMovement.checkRight(player, engine);
		// checkDown
		int downDistance = PlayerMovement.checkDown(player, engine);
		// checkLeft
		int leftDistance = PlayerMovement.checkLeft(player, engine);

		HashMap<MovementDestinations, Integer> hash = new HashMap<MovementDestinations, Integer>();
		hash.put(MovementDestinations.UP, new Integer(upDistance));
		hash.put(MovementDestinations.RIGHT, new Integer(rightDistance));
		hash.put(MovementDestinations.DOWN, new Integer(downDistance));
		hash.put(MovementDestinations.LEFT, new Integer(leftDistance));
		return hash;
	}
}
