package de.hochschuletrier.gdw.ss14.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;

public class MenuPageRoot extends MenuPage {

    public enum Type{
        STARTSCREEN,
        MAINMENU
    }
    
    public MenuPageRoot(Skin skin, MenuManager menuManager, Type type) {
        super(skin, "crosshair");
        // TODO Auto-generated constructor stub
        
        int x = 100;
        int i = 0;
        int y = 370;
        int yStep = 55;
        
        if(type == Type.STARTSCREEN){
            //addPageEntry(menuManager, x, y - yStep * (i++), "StartScreen", new MenuPageConnection(skin, menuManager, MenuPageConnection.Type.MAINMENU));
        }
    }
    
    //folge content wenn man auf button klickt zum beispiel, keine komplett neue Seite
    protected final void addPageEntry(MenuManager menuManager, int x, int y, String text, MenuPage page){
        menuManager.addLayer(page);
        addLeftAlignedButton(x,y,300, 40, text, () -> menuManager.pushPage(page));
    }

}
