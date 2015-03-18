package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerInformationComponent extends Component implements Pool.Poolable {

	public String name;
	public int points;
	
	@Override
	public void reset() {
		this.name = null;
	}

}
