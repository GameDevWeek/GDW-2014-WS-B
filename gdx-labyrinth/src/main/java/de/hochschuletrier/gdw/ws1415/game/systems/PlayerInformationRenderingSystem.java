package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

public class PlayerInformationRenderingSystem extends IteratingSystem {

    private BitmapFont font = new BitmapFont(true);
    
	@SuppressWarnings("unchecked")
	public PlayerInformationRenderingSystem(int priority) {
		super(Family.all(PlayerInformationComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
	    
	    float box_height = (float) Math.ceil(Gdx.graphics.getHeight() * GameBoardInformation.GAME_PLAYER_INFORMATION / 4);

	    float y = (float) Math.floor((Gdx.graphics.getHeight() * GameBoardInformation.GAME_NEXT_TILE) +
	            (box_height * 
	            (entity.getComponent(PlayerInformationComponent.class).playerNumber - 1)));
	    
	    float box_width = (float) Math.ceil(Gdx.graphics.getWidth() * GameBoardInformation.GAME_MENU_WIDTH);
	    
	    float x = 0.0f;
	    
	    Color box_color = entity.getComponent(PlayerInformationComponent.class).color;

        DrawUtil.fillRect(x, y, box_width, box_height, box_color);

		
        font.draw(DrawUtil.batch, "TEST", (int) Math.floor(x + 0.15 * box_width), (int) Math.floor(y + 0.5 * box_height));
	}

}
