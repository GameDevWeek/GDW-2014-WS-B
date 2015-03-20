package de.hochschuletrier.gdw.ws1415.game;

public class GameConstants {

	// Priorities for entity systems
	public static final int PRIORITY_INPUT = 0;
	public static final int PRIORITY_RENDERING_BACKGROUND = 19;
	public static final int PRIORITY_RENDERING = 20;
	public static final int PRIORITY_LEVEL_HANDLING = 30;

	// PooledEngine parameters
	public static final int ENTITY_POOL_INITIAL_SIZE = 32;
	public static final int ENTITY_POOL_MAX_SIZE = 256;
	public static final int COMPONENT_POOL_INITIAL_SIZE = 32;
	public static final int COMPONENT_POOL_MAX_SIZE = 256;

    // Music parameters
    public static float MUSIC_FADE_TIME = 2;
}
