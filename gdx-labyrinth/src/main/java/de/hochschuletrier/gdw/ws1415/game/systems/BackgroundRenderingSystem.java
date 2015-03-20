package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class BackgroundRenderingSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public BackgroundRenderingSystem(int priority) {
		super(Family.all(BackgroundComponent.class, PositionComponent.class)
				.get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		if (entity.getComponent(BackgroundComponent.class).texture != null) {
			DrawUtil.draw(
					entity.getComponent(BackgroundComponent.class).texture,
					entity.getComponent(PositionComponent.class).x,
					entity.getComponent(PositionComponent.class).y, 0, 0, 
					entity.getComponent(BackgroundComponent.class).width,
					entity.getComponent(BackgroundComponent.class).height,
					entity.getComponent(BackgroundComponent.class).scale_width,
					entity.getComponent(BackgroundComponent.class).scale_height, 0.0f);
		}
	}
}
