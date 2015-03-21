package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent extends Component implements Poolable {

	public Texture texture;
	public Texture background;
	public float scale = 1f;
	
	public boolean visible = true;

	@Override
	public void reset() {
		this.texture = null;
	}

}
