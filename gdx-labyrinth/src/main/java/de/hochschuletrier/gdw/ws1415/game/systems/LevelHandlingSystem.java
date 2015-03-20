package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.ws1415.game.components.MovementComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class LevelHandlingSystem extends IteratingSystem {

	public boolean inProgress = false;

	private float distance;
	private float distanceX;
	private float distanceY;

	@SuppressWarnings("unchecked")
	public LevelHandlingSystem(int priority) {
		super(Family.all(MovementComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		distanceX = (entity.getComponent(MovementComponent.class).destinationX - entity
				.getComponent(PositionComponent.class).x);

		distanceY = (entity.getComponent(MovementComponent.class).destinationY - entity
				.getComponent(PositionComponent.class).y);

		distance = distanceX + distanceY;

		if ((distance >= 0f & distance < 1f)
				|| (distance <= 0 & distance > -1f)) {
			entity.getComponent(PositionComponent.class).x = entity
					.getComponent(MovementComponent.class).destinationX;
			entity.getComponent(PositionComponent.class).y = entity
					.getComponent(MovementComponent.class).destinationY;
			entity.remove(MovementComponent.class);

			GameBoardInformation.CLICKABLE = true;
		} else {

			entity.getComponent(PositionComponent.class).x += distanceX
					* deltaTime * GameBoardInformation.MOVEMENT_SPEED;

			entity.getComponent(PositionComponent.class).y += distanceY
					* deltaTime * GameBoardInformation.MOVEMENT_SPEED;

		}
	}
}
