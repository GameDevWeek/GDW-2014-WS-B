package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SpeciesComponent extends Component implements Pool.Poolable{

	public static enum species {
		ARROW, MOVEMENT_ARROW, ROTATION_ARROW_RIGHT, ROTATION_ARROW_LEFT
	}
	
	public species isSpecies = null;
	
	@Override
	public void reset() {
		isSpecies = null;
		
	}

}
