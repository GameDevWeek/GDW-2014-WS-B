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
import de.hochschuletrier.gdw.ws1415.game.utils.PlayerMovement;

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
		
		isChanged = false;
	}
	
	public static void nextPlayer(PooledEngine engine) {
		currentPlayerInList = ++currentPlayerInList % playerList.size();
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family.all(SpeciesComponent.class, PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class).get());
		for(Entity tmp : arrows) {
			if(tmp.getComponent(SpeciesComponent.class).isSpecies != null && 
					tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW) {
				tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
				tmp.getComponent(TextureComponent.class).visible = true;
				tmp.getComponent(InputComponent.class).active = true;
			}
		}
	}
	
	public static void PlayerMoveStep(PooledEngine engine) {
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family.all(SpeciesComponent.class, PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class).get());
		int anzMovementArrows = 0;
		for(Entity tmp : arrows) {
			if(tmp.getComponent(SpeciesComponent.class).isSpecies != null && 
					tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW) {
				tmp.getComponent(TextureComponent.class).visible = false;
				tmp.getComponent(InputComponent.class).active = false;
			}
			else if(tmp.getComponent(SpeciesComponent.class).isSpecies != null && 
					tmp.getComponent(SpeciesComponent.class).isSpecies == species.MOVEMENT_ARROW) {
				switch(anzMovementArrows) {
//					case 0: if(PlayerMovement.checkLeft(currentPlayer, engine) > 0) {
//								tmp.getComponent(TextureComponent.class).visible = true;
//								tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
//								tmp.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x;
//								tmp.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y;
//							}
//					case 1: if(PlayerMovement.checkUp(currentPlayer, engine) > 0) {
//								tmp.getComponent(TextureComponent.class).visible = true;
//								tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
//								tmp.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 30;
//								tmp.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y;
//								tmp.getComponent(PositionComponent.class).rotation = 90;
//							}
					case 2: 
						if(PlayerMovement.checkRight(currentPlayer, engine) > 0) {
						
						tmp.getComponent(TextureComponent.class).visible = true;
						tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
						tmp.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 30;
						tmp.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y - 30;
						tmp.getComponent(PositionComponent.class).rotation = 180;
						tmp.getComponent(InputComponent.class).active = true;
						anzMovementArrows++;
					}
//					case 3: if(PlayerMovement.checkDown(currentPlayer, engine) > 0) {
//						tmp.getComponent(TextureComponent.class).visible = true;
//						tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
//						tmp.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x;
//						tmp.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y - 30;
//						tmp.getComponent(PositionComponent.class).rotation = 270;
//					}
					default: anzMovementArrows += 1;
				}
				// System.out.println(PlayerMovement.checkRight(currentPlayer, engine));
				// System.out.println(PlayerMovement.checkDown(currentPlayer, engine));
			}
		}
	}
}
