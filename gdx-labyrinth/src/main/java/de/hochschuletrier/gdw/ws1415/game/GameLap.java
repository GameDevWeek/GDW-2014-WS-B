package de.hochschuletrier.gdw.ws1415.game;

import java.util.ArrayList;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent.species;

public class GameLap {
	public enum conditions {
		TILE, PLAYER
	}
	
	public static conditions currentCondition = conditions.PLAYER;
	
	/** Player IDs */
	public static ImmutableArray<Entity> playerList;
	
	private static int currentPlayerInList = 0;
	public static Entity currentPlayer;
	
	public static boolean isChanged = false;
	
	/** WIRD VON DEN SYSTEMEN AUFGERUFEN !! */
	public static void nextCondition() {
		isChanged = true;
		switch(currentCondition) {
			case TILE: currentCondition = conditions.PLAYER; break;
			case PLAYER: currentCondition = conditions.TILE; break;
		}
	}
	
	/** NUR VON DER GAME KLASSE AUFGERUFEN */
	public static void newCondition(PooledEngine engine) {
		switch(currentCondition) {
			case TILE: PlayerMoveStep(engine); break;
			case PLAYER: nextPlayer(engine); break;
		}
	}
	
	public static void nextPlayer(PooledEngine engine) {
		currentPlayerInList = ++currentPlayerInList % playerList.size();
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family.all(SpeciesComponent.class, PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class).get());
		for(Entity tmp : arrows) {
			if(tmp.getComponent(SpeciesComponent.class).isSpecies != null && 
					tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW) {
				tmp.getComponent(TextureComponent.class).visible = true;
				tmp.getComponent(InputComponent.class).active = true;
			}
		}
	}
	
	public static void PlayerMoveStep(PooledEngine engine) {
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family.all(SpeciesComponent.class, PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class).get());
		for(Entity tmp : arrows) {
			if(tmp.getComponent(SpeciesComponent.class).isSpecies != null && 
					tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW) {
				tmp.getComponent(TextureComponent.class).visible = false;
				tmp.getComponent(InputComponent.class).active = false;
			}
		}
	}
}
