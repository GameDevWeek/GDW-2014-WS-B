package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;

public class MenuPageGame extends MenuPage{
    
    public MenuPageGame(Skin skin, MenuManager menuManager) {
        super(skin, "menu_ingame");
        
        DecoImage play = new DecoImage(assetManager.getTexture("start_button"));
        addCenteredPlayButton(menuManager.getWidth() - 300, 450, 280, 320, play, () -> {});
        
        addCenteredButton(menuManager.getWidth() - 170, 54, 100, 40, "Zurück", () -> menuManager.popPage());
    }
}