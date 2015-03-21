package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import de.hochschuletrier.gdw.ws1415.game.utils.MovementDestinations;

public class DirectionComponent extends Component implements Pool.Poolable{

	public MovementDestinations direction;
	public int steps;
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
