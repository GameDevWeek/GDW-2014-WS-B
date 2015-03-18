package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.game.components.BackgroundComponent;

public class BackgroundRenderingSystem extends IteratingSystem {

    public BackgroundRenderingSystem(int priority) {
        super(Family.all(BackgroundComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (entity.getComponent(BackgroundComponent.class).texture != null) {
            DrawUtil.draw(entity.getComponent(BackgroundComponent.class).texture,
                    350,
                    100, 0, 0, 700f,
                    700f, 0.5f, 0.5f,
                    0.0f);
        }
    }

}
