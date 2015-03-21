package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.ws1415.game.GameLap;
import de.hochschuletrier.gdw.ws1415.game.MovementUtil;
import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.input.Input_Puffer;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

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
							.get(i).x - GameBoardInformation.TILE_SIZE <= entity
								.getComponent(PositionComponent.class).x)
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y
									+ GameBoardInformation.TILE_SIZE)) {
						GameLap.nextCondition();
						MovementUtil
								.moveH(entity
										.getComponent(PositionInLevelComponent.class).y,
										-1f);
					}
					break;
				case 90:
					if ((Input_Puffer.click.get(i).x <= entity
							.getComponent(PositionComponent.class).x && Input_Puffer.click
							.get(i).x >= entity
							.getComponent(PositionComponent.class).x
							- GameBoardInformation.TILE_SIZE)
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y
									+ GameBoardInformation.TILE_SIZE)) {
						GameLap.nextCondition();
						MovementUtil
								.moveV(entity
										.getComponent(PositionInLevelComponent.class).x,
										-1f);
					}
					break;
				case 180:
					if ((Input_Puffer.click.get(i).x <= entity
							.getComponent(PositionComponent.class).x && Input_Puffer.click
							.get(i).x >= entity
							.getComponent(PositionComponent.class).x
							- GameBoardInformation.TILE_SIZE)
							&& (Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y
									- GameBoardInformation.TILE_SIZE && Input_Puffer.click
									.get(i).y <= entity
									.getComponent(PositionComponent.class).y)) {
						GameLap.nextCondition();
						MovementUtil
								.moveH(entity
										.getComponent(PositionInLevelComponent.class).y,
										1f);
					}
					break;
				case 270:
					if ((Input_Puffer.click.get(i).x >= entity
							.getComponent(PositionComponent.class).x)
							&& (Input_Puffer.click.get(i).x <= entity
									.getComponent(PositionComponent.class).x
									+ GameBoardInformation.TILE_SIZE)
							&& (Input_Puffer.click.get(i).y <= entity
									.getComponent(PositionComponent.class).y)
							&& Input_Puffer.click.get(i).y >= entity
									.getComponent(PositionComponent.class).y
									- GameBoardInformation.TILE_SIZE) {
						GameLap.nextCondition();
						MovementUtil
								.moveV(entity
										.getComponent(PositionInLevelComponent.class).x,
										1f);
					}
				}
			}
		}
	}
}
