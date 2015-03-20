package de.hochschuletrier.gdw.ws1415.game.utils;

import com.badlogic.gdx.graphics.Texture;

public class GameBoardInformation {

	// WIDTH from the game, total of 1 !!
	/** width of the menu in percent to the total screen of the game */
	final public static float GAME_MENU_WIDTH = 1 / 4f;
	/** width of the menu in percent to the total screen of the game */
	final public static float GAME_SCREEN_WIDTH = 3 / 4f;

	// HEIGTH from the game, total of 1 !!
	final public static float GAME_NEXT_TILE = 0.3f;
	final public static float GAME_PLAYER_INFORMATION = 0.7f;

	// ARROWS WIDTH IN PIXLE initialized on GameStart
	public static int ARROWS_WIDTH = 0; // 2x !!!!!
	public static int ARROWS_HEIGHT = 0;

	// TileSize
	public static float TILE_SIZE_TEXTURE = 100f;
	public static float GAME_SCALE = 0.625f;
	public static final float TILE_SIZE = TILE_SIZE_TEXTURE * GAME_SCALE;

	final public static float NUMBER_OF_TILE = 7;

	// TITLE-FIELD IN PIXLE !!
	final public static float TILE_FIELD = TILE_SIZE * NUMBER_OF_TILE;

	// TileMovementSpeed
	public final static float MOVEMENT_SPEED = 5f;

	// texture
	public static Texture MENU_WOODPLANK = null;
}
