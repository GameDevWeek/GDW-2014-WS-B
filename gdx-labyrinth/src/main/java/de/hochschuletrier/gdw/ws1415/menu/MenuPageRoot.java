package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;

public class MenuPageRoot extends MenuPage {

//    public enum Type{
//        MAINMENU
//    }
    
    public MenuPageRoot(Skin skin, MenuManager menuManager) {
        super(skin, "menu_ingame");
        // TODO Auto-generated constructor stub
        
        int x = 90;  //100
        int i = 0;
        int y = 500;
        int yStep = 55;
        
       
        DecoImage start = new DecoImage(assetManager.getTexture("start_button"));
        DecoImage options = new DecoImage(assetManager.getTexture("optionen_button"));
        DecoImage credits = new DecoImage(assetManager.getTexture("credits_button"));
        DecoImage exit = new DecoImage(assetManager.getTexture("beenden_button"));
        
        //if(type == Type.MAINMENU){
            //addCenteredPlayButton(menuManager.getWidth() - 365, x, y - yStep * (i), 40, start, () -> {});
            //addCenteredPlayButton(menuManager.getWidth() - 365, 450, 280, 40, start, () -> {});
            addPageEntry(menuManager, x, y - yStep * i, start, new MenuPageGame(skin, menuManager));
            addPageEntry(menuManager, x, y - yStep * (i+2), options, new MenuPageOptions(skin, menuManager));
            addPageEntry(menuManager, x, y - yStep * (i+4), credits, new MenuPageCredits(skin, menuManager));
            addCenteredExitButton(menuManager.getWidth()- 935, 90, 280, 40, exit, () -> System.exit(0));
        //}
    }

    protected final void addPageEntry(MenuManager menuManager, int x, int y, DecoImage button, MenuPage page){
        menuManager.addLayer(page);
        addCenteredImage(x,y,280, 40, button, () -> menuManager.pushPage(page));
    }
}
