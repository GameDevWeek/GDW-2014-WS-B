package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class RenderingSystem extends IteratingSystem {
    
    float scale = 1.0f;

	@SuppressWarnings("unchecked")
	public RenderingSystem(int priority) {
		super(Family.all(PositionComponent.class, TextureComponent.class).get(), priority);
	}

	@Override
	public void update (float deltaTime) {
	    
//	    VIELLEICHT GARNICHT MEHR NÖTIG, WEIL FESTER BILDSCHIRM
//	    float scale_width = Gdx.graphics.getWidth() * GameBoardInformation.GAME_SCREEN_WIDTH * 
//	            GameBoardInformation.TILDE_FIELD / GameBoardInformation.NUMBER_OF_TILE / 100;
//	    float scale_height = Gdx.graphics.getHeight() * 
//                GameBoardInformation.TILDE_FIELD / GameBoardInformation.NUMBER_OF_TILE / 100;
//	    scale = Math.max(scale_width, scale_height);
	    super.update(deltaTime);
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
