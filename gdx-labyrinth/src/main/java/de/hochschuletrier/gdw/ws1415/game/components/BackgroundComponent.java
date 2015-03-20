package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

public class BackgroundComponent extends Component implements Pool.Poolable {
    
    public Texture texture;
    public float height = 0;
    public float width = 0;
    public float scale_width = 1.0f;
    public float scale_height = 1.0f;

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }
}
