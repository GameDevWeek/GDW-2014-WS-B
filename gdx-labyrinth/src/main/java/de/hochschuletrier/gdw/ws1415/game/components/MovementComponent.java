package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MovementComponent extends Component implements Pool.Poolable {

	public float originX;
	public float originY;

	public float destinationX;
	public float destinationY;

	public float movementSpeed;

	@Override
	public void reset() {
		originX = originY = destinationX = destinationY = movementSpeed = 0f;
	}

}
