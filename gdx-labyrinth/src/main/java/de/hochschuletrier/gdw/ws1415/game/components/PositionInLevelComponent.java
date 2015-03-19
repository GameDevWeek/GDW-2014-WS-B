package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionInLevelComponent extends Component implements
		Pool.Poolable {

	public int x;
	public int y;

	@Override
	public void reset() {
		this.x = y = 0;

	}

}
