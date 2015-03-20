package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ws1415.game.GameConstants;

public class MenuPageGame extends MenuPage{
    
    public MenuPageGame(Skin skin, MenuManager menuManager) {
        super(skin, "menu_ingame");
        //super(skin, "ladescreen");
        //addActor(new DecoImage(assetManager.getTexture("menu_ingame")));
        
        DecoImage play = new DecoImage(assetManager.getTexture("start_button"));
        addCenteredPlayButton(menuManager.getWidth() - 300, 650, 280, 320, play, () -> {});
        
        addCenteredButton(menuManager.getWidth() - 170, 54, 100, 40, "Zurück", () -> menuManager.popPage());
        addPlayer(menuManager.getWidth() - 220, 260, 100, 100, "Add Player", () -> {});
        //addCenteredButton(300, 54, 100, 100, "Add Player", () -> {});
        
        removePlayer(menuManager.getWidth() - 220, 160, 100, 100, "Remove Player", () -> {});
        
        DecoImage player = new DecoImage(assetManager.getTexture("player_RED"));
        addPlayerButton(100, 450, 100, 100, player, GameConstants.playerNames[0], 0);
        
        DecoImage player2 = new DecoImage(assetManager.getTexture("player_GREEN"));
        addPlayerButton(100, 300, 100, 100, player2, GameConstants.playerNames[1], 1);
        
//        TextField test = new TextField("logo", skin);
//        test.setPosition(400, 450);
//        addActor(test);
    }
}