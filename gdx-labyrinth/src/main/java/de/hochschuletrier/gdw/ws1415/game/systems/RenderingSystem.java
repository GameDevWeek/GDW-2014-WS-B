package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;

public class RenderingSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public RenderingSystem(int priority) {
		super(
				Family.all(PositionComponent.class, TextureComponent.class)
						.get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		DrawUtil.draw(entity.getComponent(TextureComponent.class).background,
				entity.getComponent(PositionComponent.class).x,
				entity.getComponent(PositionComponent.class).y, 0, 0, 100f,
				100f, 0.5f, 0.5f,
				entity.getComponent(PositionComponent.class).rotation);

		DrawUtil.draw(entity.getComponent(TextureComponent.class).texture,
				entity.getComponent(PositionComponent.class).x,
				entity.getComponent(PositionComponent.class).y, 0, 0, 100.0f,
				100.0f, 0.5f, 0.5f,
				entity.getComponent(PositionComponent.class).rotation);
	}
}
