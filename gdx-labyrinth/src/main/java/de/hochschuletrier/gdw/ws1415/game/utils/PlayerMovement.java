package de.hochschuletrier.gdw.ws1415.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;

public class PlayerMovement {

	public static Entity getTileOfPlayer(Entity player, PooledEngine engine) {

		float playerPosX = player.getComponent(PositionComponent.class).x;
		float playerPosY = player.getComponent(PositionComponent.class).y;
		float tilePosX;
		float tilePosY;
		Entity tileOfPlayer = null;
				
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> tileList =  	engine.getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class)
				.get());
		
		for(Entity entity: tileList) {
			tilePosX = entity.getComponent(PositionComponent.class).x;
			tilePosY = entity.getComponent(PositionComponent.class).y;
			
			if ((tilePosX == playerPosX) && (tilePosY == playerPosY)) {
				tileOfPlayer =  entity;
			}
		}
		
		return tileOfPlayer;
	}
}
