package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;

public class MenuPageGame extends MenuPage{
    
    public MenuPageGame(Skin skin, MenuManager menuManager) {
        super(skin, "menu_ingame");
        
        DecoImage play = new DecoImage(assetManager.getTexture("start_button"));
        addCenteredPlayButton(menuManager.getWidth() - 300, 450, 280, 320, play, () -> {});
        
        addCenteredButton(menuManager.getWidth() - 170, 54, 100, 40, "Zurück", () -> menuManager.popPage());
        addPlayer(100, 25, 100, 100, "Add Player", () -> {});
        //addCenteredButton(300, 54, 100, 100, "Add Player", () -> {});
        
        DecoImage player = new DecoImage(assetManager.getTexture("player"));
        addPlayerButton(100, 450, 100, 100, player, "Player 1");
        
        DecoImage player2 = new DecoImage(assetManager.getTexture("player"));
        addPlayerButton(100, 300, 100, 100, player2, "Player 2");
        
//        TextField test = new TextField("logo", skin);
//        test.setPosition(400, 450);
//        addActor(test);
    }
}