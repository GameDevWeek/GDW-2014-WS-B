package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent extends Component implements Poolable {

	public String texture;

	@Override
	public void reset() {
		this.texture = null;
	}

}
