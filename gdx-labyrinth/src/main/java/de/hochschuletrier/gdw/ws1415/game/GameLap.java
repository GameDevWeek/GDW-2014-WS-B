package de.hochschuletrier.gdw.ws1415.game;

import java.util.ArrayList;

public class GameLap {
	public enum conditions {
		TILE, PLAYER
	}
	
	public static conditions currentCondition;
	
	private static ArrayList<Long> playerList = new ArrayList<Long>();
	
	private static int currentPlayerInList = 0;
	public static long currentPlayerID;
	
	
	public static void nextStep() {
		switch(currentCondition) {
			case TILE: break;
			case PLAYER: nextPlayer(); break;
		}
	}
	
	public static void nextPlayer() {
		currentPlayerInList = ++currentPlayerInList % playerList.size();
		
		currentPlayerID = playerList.get(currentPlayerInList);
		currentCondition = conditions.TILE;
	}
	
	public static void PlayerMoveStep() {
		currentCondition = conditions.TILE;
	}
}
