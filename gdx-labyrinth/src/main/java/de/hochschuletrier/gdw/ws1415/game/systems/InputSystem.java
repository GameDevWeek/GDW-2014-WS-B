package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;

import de.hochschuletrier.gdw.commons.gdx.input.InputForwarder;
import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.GameLap;
import de.hochschuletrier.gdw.ws1415.game.MovementUtil;
import de.hochschuletrier.gdw.ws1415.game.components.DirectionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.RotationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.input.Input_Puffer;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;
import de.hochschuletrier.gdw.ws1415.states.GameplayState;
import de.hochschuletrier.gdw.ws1415.states.LoadGameState;
import de.hochschuletrier.gdw.ws1415.states.MainMenuState;

public class InputSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public InputSystem(int priority) {
		super(Family.all(InputComponent.class).get(), priority);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Input_Puffer.click.clear();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		if(entity.getComponent(InputComponent.class).active == false) {
			return;
		}		
		
		// check if something was clicked !!
		for (int i = 0; i < Input_Puffer.click.size(); i++) {
			if (GameBoardInformation.CLICKABLE) {

				switch ((int) entity.getComponent(PositionComponent.class).rotation) {

				case 0:
					if ((Input_Puffer.click.get(i).x >= entity
							.getComponent(PositionComponent.class).x && Input_Puffer.click
							.get(i).x <= entity
								.getComponent(PositionComponent.class).x + entity.getComponent(TextureComponent.class).texture.getWidth())
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y
									+ entity.getComponent(TextureComponent.class).texture.getHeight())) {
						if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_RIGHT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: 
								GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;	
							break;
							case 90: 
								GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;	
								break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
							}
							 float tmp = GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate;
							 if(tmp < 0) {
								 tmp += 360;
							 }
							 GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = tmp;
							 int puffer = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[0];
							 for (int s = 0; s < GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData.length - 1; s++) {
								 GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s] = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s + 1];
							 }
							 GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[3] = puffer;
						}
						else if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_LEFT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
							}
							float tmp = GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate;
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = tmp % 360;
							
							int puffer = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[3];
							for (int s = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData.length - 1; s > 0; s--) {
								GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s] = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s - 1];
							}
							GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[0] = puffer;
						}
						else if (entity.getComponent(InputComponent.class).action == InputComponent.clickAction.MOVEMENT){
							GameLap.hideMovementArrows();
							MovementUtil.playerMovement(entity.getComponent(DirectionComponent.class).steps, entity.getComponent(DirectionComponent.class).direction);
						}
						else if (entity.getComponent(InputComponent.class).action == InputComponent.clickAction.MENU){						    
						    Main.getInstance().changeState(new MainMenuState(Main.getInstance().getAssetManager()));
						}
						else{
							GameLap.hideRotationArrows();
							MovementUtil.moveH(entity.getComponent(PositionInLevelComponent.class).y,-1f);
						}
					}
					break;
				case 90:
					if ((Input_Puffer.click.get(i).x <= entity
							.getComponent(PositionComponent.class).x && Input_Puffer.click
							.get(i).x >= entity
							.getComponent(PositionComponent.class).x
							- entity.getComponent(TextureComponent.class).texture.getWidth())
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y
									+ entity.getComponent(TextureComponent.class).texture.getHeight())) {
						if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_RIGHT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
							}
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = Math.abs(GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate);
						}
						else if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_LEFT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
							}
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = Math.abs(GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate);
						}
						else if (entity.getComponent(InputComponent.class).action == InputComponent.clickAction.MOVEMENT){
							GameLap.hideMovementArrows();
							MovementUtil.playerMovement(entity.getComponent(DirectionComponent.class).steps, entity.getComponent(DirectionComponent.class).direction);
						}
						else {
							GameLap.hideRotationArrows();
							MovementUtil.moveV(entity.getComponent(PositionInLevelComponent.class).x,-1f);
						}
					}
					break;
				case 180:
					if ((Input_Puffer.click.get(i).x <= entity
							.getComponent(PositionComponent.class).x && Input_Puffer.click
							.get(i).x >= entity
							.getComponent(PositionComponent.class).x
							- entity.getComponent(TextureComponent.class).texture.getWidth())
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y
									- entity.getComponent(TextureComponent.class).texture.getHeight() && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y)) {
						if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_RIGHT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
							}
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = Math.abs(GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate);
						}
						else if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_LEFT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
							}
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = Math.abs(GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate);
						}
						else if (entity.getComponent(InputComponent.class).action == InputComponent.clickAction.MOVEMENT){
							GameLap.hideMovementArrows();
							MovementUtil.playerMovement(entity.getComponent(DirectionComponent.class).steps, entity.getComponent(DirectionComponent.class).direction);
						}
						else {
							GameLap.hideRotationArrows();
							MovementUtil.moveH(entity.getComponent(PositionInLevelComponent.class).y,1f);
						}
					}
					break;
				case 270:
					if ((Input_Puffer.click.get(i).x >= entity
							.getComponent(PositionComponent.class).x)
							&& (Input_Puffer.click.get(i).x <= entity
									.getComponent(PositionComponent.class).x
									+ entity.getComponent(TextureComponent.class).texture.getWidth())
							&& (Input_Puffer.click.get(i).y <= entity
									.getComponent(PositionComponent.class).y)
							&& Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y
									- entity.getComponent(TextureComponent.class).texture.getHeight()) {
						if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_RIGHT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: 
								GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;	
							break;
							case 90: 
								GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;	
								break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
							}
							 float tmp = GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate;
							 if(tmp < 0) {
								 tmp += 360;
							 }
							 GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = tmp;
							 int puffer = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[0];
							 for (int s = 0; s < GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData.length - 1; s++) {
								 GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s] = GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[s + 1];
							 }
							 GameBoardInformation.nextTileEntity.getComponent(TileComponent.class).rotationData[3] = puffer;
						}
						else if(entity.getComponent(InputComponent.class).action == InputComponent.clickAction.ROTATION_LEFT){
							switch((int)GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation) {
							case 0: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x += GameBoardInformation.TILE_SIZE;
									break;
							case 90: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y += GameBoardInformation.TILE_SIZE;
									break;
							case 180: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).x -= GameBoardInformation.TILE_SIZE;
									break;
							case 270: GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).y -= GameBoardInformation.TILE_SIZE;
							}
							GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation = Math.abs(GameBoardInformation.nextTileEntity.getComponent(PositionComponent.class).rotation + entity.getComponent(RotationComponent.class).rotate);
						}
						else if (entity.getComponent(InputComponent.class).action == InputComponent.clickAction.MOVEMENT){
							GameLap.hideMovementArrows();
							MovementUtil.playerMovement(entity.getComponent(DirectionComponent.class).steps, entity.getComponent(DirectionComponent.class).direction);
						}
						else {
							GameLap.hideRotationArrows();
							MovementUtil.moveV(entity.getComponent(PositionInLevelComponent.class).x,1f);
						}
					}
				}
			}
		}
	}
}
