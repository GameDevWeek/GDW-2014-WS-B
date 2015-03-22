package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ItemComponent extends Component implements Pool.Poolable {

   public boolean spaten_is_real = true;
   public boolean isReached = false;

    @Override
    public void reset() {
    	isReached = false;
    	spaten_is_real = false;
    }
}


