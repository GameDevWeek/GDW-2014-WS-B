package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileType;

public class LevelGeneratingSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public LevelGeneratingSystem(int priority) {
		super(Family.all(PositionComponent.class, TextureComponent.class,
				TileComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

	

	}
}
