package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TileComponent extends Component implements Pool.Poolable {

	public TileType tileType;

	@Override
	public void reset() {
		this.tileType = null;
	}

}
