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

	private float moveTime = 1f;
	private float progress = 0f;

	@SuppressWarnings("unchecked")
	public LevelHandlingSystem(int priority) {
		super(Family.all(MovementComponent.class).get(), priority);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		this.progress += deltaTime;

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		entity.getComponent(MovementComponent.class).originX += entity
				.getComponent(MovementComponent.class).destinationX
				* deltaTime
				* GameBoardInformation.MOVEMENT_SPEED;
	}

}
