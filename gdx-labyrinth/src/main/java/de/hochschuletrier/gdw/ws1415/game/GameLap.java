package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import de.hochschuletrier.gdw.ws1415.game.components.DirectionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent;
import de.hochschuletrier.gdw.ws1415.game.components.SpeciesComponent.species;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;
import de.hochschuletrier.gdw.ws1415.game.utils.MovementDestinations;
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
		isChanged = false;
		switch (currentCondition) {
			case TILE:
				PlayerMoveStep(engine);
				break;
			case PLAYER:
				nextPlayer(engine);
				break;
		}		
	}

	public static void nextPlayer(PooledEngine engine) {
		currentPlayerInList = ++currentPlayerInList % playerList.size();
		currentPlayer = playerList.get(currentPlayerInList);
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
			else if(tmp.getComponent(SpeciesComponent.class).isSpecies == species.ROTATION_ARROW_RIGHT || tmp.getComponent(SpeciesComponent.class).isSpecies == species.ROTATION_ARROW_LEFT) {
				tmp.getComponent(TextureComponent.class).visible = true;
				tmp.getComponent(InputComponent.class).active = true;
			}
			else if(tmp.getComponent(SpeciesComponent.class).isSpecies == species.MOVEMENT_ARROW) {
				tmp.getComponent(TextureComponent.class).visible = false;
				tmp.getComponent(InputComponent.class).active = false;
			}
		}
		GameBoardInformation.nextTileEntity = LvlGenerator.createTile(engine, -5.2f, 0f);
	}

	public static void PlayerMoveStep(PooledEngine engine) {
		@SuppressWarnings("unchecked")
		ImmutableArray<Entity> arrows = engine.getEntitiesFor(Family
				.all(SpeciesComponent.class, PositionComponent.class,
						TextureComponent.class)
				.exclude(PlayerInformationComponent.class).get());
		int anzMovementArrows = 0;
		boolean noWay = false;
		for (Entity tmp : arrows) {
				// NullPointer abfangen
			if (tmp.getComponent(SpeciesComponent.class).isSpecies == null) {
				throw new NullPointerException("isSpecies in SpeciesComponent ist null");
			}	// Wenn es ein normaler Arrow ist
			else if (tmp.getComponent(SpeciesComponent.class).isSpecies == species.ARROW 
					|| tmp.getComponent(SpeciesComponent.class).isSpecies == species.ROTATION_ARROW_LEFT
					|| tmp.getComponent(SpeciesComponent.class).isSpecies == species.ROTATION_ARROW_RIGHT) {
				tmp.getComponent(TextureComponent.class).visible = false;
				tmp.getComponent(InputComponent.class).active = false;
			}	// Wenn es ein MoveArrow ist 
			else if (tmp.getComponent(SpeciesComponent.class).isSpecies == species.MOVEMENT_ARROW) {
				switch (anzMovementArrows) {
				 	case 0: 
				 		noWay = noWay | checkMoveLeft(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 1:
				 		noWay = noWay | checkMoveUp(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 2:
				 		noWay = noWay | checkMoveRight(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				 	case 3:
				 		noWay = noWay | checkMoveDown(tmp, engine);
				 		anzMovementArrows++;
				 		break;
				}
			}
		}
		if(noWay == false) {
			nextCondition();
		}
	}
	private static boolean checkMoveDown(Entity moveArrow, PooledEngine engine) {
		int tmp = PlayerMovement.checkDown(currentPlayer, engine);
		if (tmp > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 20;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y + 80;
			moveArrow.getComponent(PositionComponent.class).rotation = 270;
			moveArrow.getComponent(DirectionComponent.class).direction = MovementDestinations.DOWN;
			moveArrow.getComponent(DirectionComponent.class).steps = tmp;
			
			return true;
		}
		return false;
	}
	
	private static boolean checkMoveRight(Entity moveArrow, PooledEngine engine) {
		int tmp = PlayerMovement.checkRight(currentPlayer, engine);
		if (tmp > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 80;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y + 43;
			moveArrow.getComponent(PositionComponent.class).rotation = 180;
			moveArrow.getComponent(DirectionComponent.class).direction = MovementDestinations.RIGHT;
			moveArrow.getComponent(DirectionComponent.class).steps = tmp;
			return true;
		}
		return false;
	}
	
	private static boolean checkMoveUp(Entity moveArrow, PooledEngine engine) {
		int tmp = PlayerMovement.checkUp(currentPlayer, engine);
		if(tmp > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x + 45;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y - 19;
			moveArrow.getComponent(PositionComponent.class).rotation = 90;
			moveArrow.getComponent(DirectionComponent.class).direction = MovementDestinations.UP;
			moveArrow.getComponent(DirectionComponent.class).steps = tmp;
			return true;
		}
		return false;
	}
	
	private static boolean checkMoveLeft(Entity moveArrow, PooledEngine engine) {
		int tmp = PlayerMovement.checkLeft(currentPlayer, engine);
		if(tmp > 0) {
			moveArrow.getComponent(TextureComponent.class).visible = true;
			moveArrow.getComponent(InputComponent.class).active = true;
			moveArrow.getComponent(TextureComponent.class).texture = currentPlayer.getComponent(PlayerInformationComponent.class).arrow;
			moveArrow.getComponent(PositionComponent.class).x = currentPlayer.getComponent(PositionComponent.class).x - 18;
			moveArrow.getComponent(PositionComponent.class).y = currentPlayer.getComponent(PositionComponent.class).y + 17;
			moveArrow.getComponent(DirectionComponent.class).direction = MovementDestinations.LEFT;
			moveArrow.getComponent(DirectionComponent.class).steps = tmp;
			return true;
		}
		return false;
	}
}
