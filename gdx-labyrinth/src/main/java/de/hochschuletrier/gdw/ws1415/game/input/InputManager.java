package de.hochschuletrier.gdw.ws1415.game.input;

import com.badlogic.gdx.Gdx;

public class InputManager {
    
    public enum Listener {
        MouceKeyboard
    }
    
    public InputManager() {
        // default listener
        this.setMouceKeyboardListener();
    }
    
    public void setListener(Listener listener) {
        switch(listener) {
            case MouceKeyboard: this.setMouceKeyboardListener(); break;
        }
    }
    
    private void setMouceKeyboardListener() {
        Game_Input_Processor inputProcessor = new Game_Input_Processor();
        Gdx.input.setInputProcessor(inputProcessor);
    }
}
