package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class RenderingSystem extends IteratingSystem {

	float scale = 1.0f;

	@SuppressWarnings("unchecked")
	public RenderingSystem(int priority) {
		super(Family.all(PositionComponent.class, TextureComponent.class)
				.exclude(PlayerInformationComponent.class, BackgroundComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		if(entity.getComponent(TextureComponent.class).visible == false) {
			return;
		}
		
		DrawUtil.draw(entity.getComponent(TextureComponent.class).texture,
				entity.getComponent(PositionComponent.class).x,
				entity.getComponent(PositionComponent.class).y, 0, 0,
				entity.getComponent(TextureComponent.class).texture.getWidth(),
				entity.getComponent(TextureComponent.class).texture.getHeight(),
				GameBoardInformation.GAME_SCALE,
				GameBoardInformation.GAME_SCALE,
				entity.getComponent(PositionComponent.class).rotation);
	}
}
