package de.hochschuletrier.gdw.ws1415.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.hochschuletrier.gdw.ws1415.game.components.InputComponent;
import de.hochschuletrier.gdw.ws1415.game.input.Input_Puffer;

public class InputSystem extends IteratingSystem {

    @SuppressWarnings("unchecked")
    public InputSystem(int priority) {
        super(Family.all(InputComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // check if something was clicked !! 
        for(int i = 0; i < Input_Puffer.click.size(); i++) {
            System.out.println(Input_Puffer.click.get(i));
        }
        
        
    }
    
}
