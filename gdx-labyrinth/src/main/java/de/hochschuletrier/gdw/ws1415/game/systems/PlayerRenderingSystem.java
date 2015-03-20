package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;

public class PlayerRenderingSystem extends IteratingSystem  {

	@SuppressWarnings("unchecked")
	public PlayerRenderingSystem(int priority) {
		super(Family.all(PositionComponent.class, TextureComponent.class, PlayerInformationComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		DrawUtil.fillRect(entity.getComponent(PositionComponent.class).x + 20,
				entity.getComponent(PositionComponent.class).y + 20,
				20, 20, 
				entity.getComponent(PlayerInformationComponent.class).color);
	}
	
}
