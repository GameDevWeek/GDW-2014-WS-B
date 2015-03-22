package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class InputComponent extends Component implements Pool.Poolable {

	public enum clickAction {
		ROTATION_RIGHT, ROTATION_LEFT, TILE, MOVEMENT, MENU
	}
	
	public boolean active;
	public clickAction action = clickAction.TILE;

	@Override
	public void reset() {
		// TODO SCHREIBEN

	}

}
