package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class PlayerInformationComponent extends Component implements Pool.Poolable {

	public int playerNumber;
	public String name;
	public Color color;
	
	@Override
	public void reset() {
		this.name = null;
	}

}
