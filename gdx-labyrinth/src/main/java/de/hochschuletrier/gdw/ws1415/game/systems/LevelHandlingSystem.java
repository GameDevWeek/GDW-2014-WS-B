package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;

public class LevelHandlingSystem extends IteratingSystem {

	public LevelHandlingSystem(int priority) {
		super(Family.all(PositionInLevelComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub

	}

}
