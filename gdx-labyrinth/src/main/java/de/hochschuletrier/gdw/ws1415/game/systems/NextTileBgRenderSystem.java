package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.NextTileBgRenderComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class NextTileBgRenderSystem extends IteratingSystem {

    private BitmapFont font = new BitmapFont(true);
    
	@SuppressWarnings("unchecked")
	public NextTileBgRenderSystem(int priority) {
		super(Family.all(NextTileBgRenderComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
	    
	    float box_height = Gdx.graphics.getHeight();

	    float y = 0.0f;
	    
	    float box_width = (float) Math.ceil(Gdx.graphics.getWidth() * GameBoardInformation.GAME_MENU_WIDTH);
	    
	    float x = 0.0f;
	    
	    DrawUtil.draw(entity.getComponent(NextTileBgRenderComponent.class).texture, x, y, box_width, box_height);

		
        font.draw(DrawUtil.batch, "This is SPAAAAAATEN ", (int) Math.floor(x + 0.15 * box_width), (int) Math.floor(y + 0.05 * box_height));
	}

}

