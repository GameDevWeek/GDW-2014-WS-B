package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RotationComponent extends Component implements Pool.Poolable{

	public Float rotate;
	
	@Override
	public void reset() {
		rotate = null;
	}

}
