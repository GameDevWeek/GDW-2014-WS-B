package de.hochschuletrier.gdw.ws1415.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TileComponent extends Component implements Pool.Poolable {

	public TileType tileType;
	/**
	*Information in welche Richtung ein Tile "offen" ist!
	*1 = offen, 0 = geschlossen!
	*erstes Element = Information nach oben, zweites Element Info nach rechts, usw. ...
	*Bsp.: L (bedeutet nach oben und nach rechts offen! Array = [1,1,0,0] */
	public int[] rotationData = new int[4];

	@Override
	public void reset() {
		this.tileType = null;
	}

	public void rotate(float rotation) {
		int[] rotationData = this.rotationData;
		int count;

		//erkennen wie oft rotier werden soll!
		switch ((int) rotation) {
		case 90:
			count = 1;
			break;
		case 180:
			count = 2;
			break;
		case 270:
			count = 3;
			break;
		default:
			count = 0;
			break;
		}
			
		//Anzahl Rotationen in Schleife!
		for (int i = 0; i < count; i++) {
			//letztes Element speichern
			int lastElement = rotationData[3];
			//die letzten 3 Elemente eins nach rechts verschieben
			for (int j = 3; j > 0; j--) {
				rotationData[j] = rotationData[j-1];
			}
			//ehemals letzes Element als erstes setzen
			rotationData[0] = lastElement;
		}
		//rotationData überschreiben!
		this.rotationData = rotationData;
	}

}
