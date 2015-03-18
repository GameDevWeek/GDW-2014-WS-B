package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;

public class PlayerInformationRenderingSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public PlayerInformationRenderingSystem(int priority) {
		super(Family.all(PlayerInformationComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		float box_width = (float) (Gdx.graphics.getWidth() * 0.2);
		float box_height = (float) (Gdx.graphics.getHeight() * 0.18);
		float box_pos_x = 0;
		float box_pos_y = (Gdx.graphics.getHeight() - 4 * box_height)
				+ (entity.getComponent(PlayerInformationComponent.class).playerNumber - 1)
				* box_height;
		Color box_color = entity.getComponent(PlayerInformationComponent.class).color;

		DrawUtil.fillRect(box_pos_x, box_pos_y, box_width, box_height,
				box_color);

	}

}
