package de.hochschuletrier.gdw.ws1415.game;

public class GameConstants {

    // Priorities for entity systems
    public static final int PRIORITY_INPUT = 0;
    public static final int PRIORITY_RENDERING_BACKGROUND = 19;
    public static final int PRIORITY_RENDERING = 20;
    

    // PooledEngine parameters
    public static final int ENTITY_POOL_INITIAL_SIZE = 32;
    public static final int ENTITY_POOL_MAX_SIZE = 256;
    public static final int COMPONENT_POOL_INITIAL_SIZE = 32;
    public static final int COMPONENT_POOL_MAX_SIZE = 256;

    // Physix parameters
    public static final int POSITION_ITERATIONS = 3;
    public static final int VELOCITY_ITERATIONS = 8;
    public static final int BOX2D_SCALE = 40;
    
    // Music parameters
    public static float MUSIC_FADE_TIME = 2;
    
    //Playernames
    public static String[] playerNames = {"Player 1", "Player 2", "Player 3", "Der Spaten"};
    public static boolean p1present = true;
    public static boolean p2present = true;
    public static boolean p3present = false;
    public static boolean p4present = false;
}
