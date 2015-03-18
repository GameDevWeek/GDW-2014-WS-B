package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class LevelComponent extends Component implements Pool.Poolable {

	Entity[][] level;

	@Override
	public void reset() {
		level = null;
	}

}
