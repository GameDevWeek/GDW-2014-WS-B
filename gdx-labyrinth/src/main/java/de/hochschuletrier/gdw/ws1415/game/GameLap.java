package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent.species;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
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
		switch (currentCondition) {
		case TILE:
			currentCondition = conditions.PLAYER;
			break;
		case PLAYER:
			currentCondition = conditions.TILE;
			break;
		}
	}

	/** NUR VON DER GAME KLASSE AUFGERUFEN */
	public static void newCondition(PooledEngine engine) {
		switch (currentCondition) {
		case TILE:
			PlayerMoveStep(engine);
			break;
		case PLAYER:
			nextPlayer(engine);
			break;
		}

		isChanged = false;
	}

	public static void nextPlayer(PooledEngine engine) {
		currentPlayerInList = ++currentPlayerInList % playerList.size();
		System.out.println(currentPlayer.getComponent(PlayerInformationComponent.class).playerNumber);
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family.all(SpeciesComponent.class, PositionComponent.class, TextureComponent.class).exclude(PlayerInformationComponent.class).get());
		for(Entity tmp : arrows) {
			if (tmp.getComponent(SpeciesComponent.class).isSpecies == null) {
				throw new NullPointerException();
			}
			else if(tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW) {
				tmp.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
				tmp.getComponent(TextureComponent.class).visible = true;
				tmp.getComponent(InputComponent.class).active = true;
			}
		}
	}

	public static void PlayerMoveStep(PooledEngine engine) {
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family
				.all(SpeciesComponent.class, PositionComponent.class,
						TextureComponent.class)
				.exclude(PlayerInformationComponent.class).get());
		int anzMovementArrows = 0;
		for (Entity tmp : arrows) {
				// NullPointer abfangen
			if (tmp.getComponent(SpeciesComponent.class).isSpecies == null) {
				throw new NullPointerException("isSpecies in SpeciesComponent ist null");
			}	// Wenn es ein normaler Arrow ist
			else if (tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW || tmp.getComponent(SpeciesComponent.class).isSpecies == species.ROTATION_ARROW) {
				tmp.getComponent(TextureComponent.class).visible = false;
				tmp.getComponent(InputComponent.class).active = false;
			}	// Wenn es ein MoveArrow ist 
			else if (tmp.getComponent(SpeciesComponent.class).isSpecies == species.MOVEMENT_ARROW) {
				switch (anzMovementArrows) {
				 	case 0: System.out.println("Left: " + PlayerMovement.checkLeft(currentPlayer, engine));
				 		checkMoveLeft(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 1: System.out.println("Up: " + PlayerMovement.checkUp(currentPlayer, engine));
				 		checkMoveUp(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 2:
				 		System.out.println("Right: " + PlayerMovement.checkRight(currentPlayer, engine));
				 		checkMoveRight(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 3:
				 		System.out.println("Down: " + PlayerMovement.checkDown(currentPlayer, engine));
				 		checkMoveDown(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				}
			}
		}
	}
	private static void checkMoveDown(Entity moveArrow, PooledEngine engine) {
		if (PlayerMovement.checkDown(currentPlayer, engine) > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y - 30;
			moveArrow.getComponent(PositionComponent.class).rotation = 270;
		}
	}
	
	private static void checkMoveRight(Entity moveArrow, PooledEngine engine) {
		if (PlayerMovement.checkRight(currentPlayer, engine) > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 30;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y - 30;
			moveArrow.getComponent(PositionComponent.class).rotation = 180;
		}
	}
	
	private static void checkMoveUp(Entity moveArrow, PooledEngine engine) {
		if(PlayerMovement.checkUp(currentPlayer, engine) > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 30;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y;
			moveArrow.getComponent(PositionComponent.class).rotation = 90;
		}
	}
	
	private static void checkMoveLeft(Entity moveArrow, PooledEngine engine) {
		if(PlayerMovement.checkLeft(currentPlayer, engine) > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y;
		}
	}
}
