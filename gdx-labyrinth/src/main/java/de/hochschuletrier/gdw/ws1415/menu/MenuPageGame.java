package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ws1415.game.GameConstants;

public class MenuPageGame extends MenuPage{
    
    public MenuPageGame(Skin skin, MenuManager menuManager) {
        super(skin, "background");

        DecoImage right = new DecoImage(assetManager.getTexture("menu_ingame"));
        right.setPosition(600, 0);
        addActor(right);
        
        DecoImage play = new DecoImage(assetManager.getTexture("play_button"));
        addCenteredPlayButton(menuManager.getWidth() - 940, 650, 280, 220, play, () -> {});
        
        DecoImage back = new DecoImage(assetManager.getTexture("back"));
        back.setPosition(25, 530);
        addActor(back);
        addCenteredButton(menuManager.getWidth() - 965, 560, 100, 40, "<", () -> menuManager.popPage());
        
        DecoImage add = new DecoImage(assetManager.getTexture("addplayer"));
        addPlayer(menuManager.getWidth() - 860, 360, 100, 100, add, () -> {});
        
        DecoImage remove = new DecoImage(assetManager.getTexture("remove"));
        removePlayer(menuManager.getWidth() - 860, 360, 100, 100, remove, () -> {});
        
        DecoImage player = new DecoImage(assetManager.getTexture("player_RED"));
        addPlayerButton(627, 450, 100, 100, player, GameConstants.playerNames[0], 0);
        
        DecoImage player2 = new DecoImage(assetManager.getTexture("player_GREEN"));
        addPlayerButton(627, 300, 100, 100, player2, GameConstants.playerNames[1], 1);
        
    }
}