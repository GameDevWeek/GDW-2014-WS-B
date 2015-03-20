package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;

public class PlayerInformationComponent extends Component implements Pool.Poolable {

	public int playerNumber;
	public String name;
	public Color color;
	public Texture texture;
	
	@Override
	public void reset() {
		this.name = null;
	}

}
