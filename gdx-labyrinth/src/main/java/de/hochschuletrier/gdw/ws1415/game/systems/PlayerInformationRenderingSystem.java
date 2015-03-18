package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;

public class PlayerInformationRenderingSystem extends IteratingSystem{

    @SuppressWarnings("unchecked")
    public PlayerInformationRenderingSystem(int priority) {
        super(Family.all(PlayerInformationComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DrawUtil.drawRect(0,0,100,100, Color.RED);
    }

}
