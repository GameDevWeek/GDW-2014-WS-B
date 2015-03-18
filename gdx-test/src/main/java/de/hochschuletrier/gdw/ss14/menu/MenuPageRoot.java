package de.hochschuletrier.gdw.ss14.menu;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ss14.Main;

public class MenuPageRoot extends MenuPage {

    public enum Type{
        MAINMENU
    }
    
    public MenuPageRoot(Skin skin, MenuManager menuManager, Type type) {
        super(skin, "logo");
        // TODO Auto-generated constructor stub
        
        //addCenteredButton(menuManager.getWidth()-80, 54, 100, 40, "crosshair", () -> System.exit(-1));
        
        int x = 100;
        int i = 0;
        int y = 370;
        int yStep = 55;
        
       
        DecoImage start = new DecoImage(assetManager.getTexture("start_button"));
        DecoImage options = new DecoImage(assetManager.getTexture("optionen_button"));
        DecoImage credits = new DecoImage(assetManager.getTexture("credits_button"));
        DecoImage exit = new DecoImage(assetManager.getTexture("beenden_button"));
        //TextField text = new TextField("logo", skin);
        
        if(type == Type.MAINMENU){
            addPageEntry(menuManager, x, y - yStep * (i++), start, new MenuPageConnection(skin, menuManager, MenuPageConnection.Type.OPTIONMENU));
            addPageEntry(menuManager, x, y - yStep * (++i), options, new MenuPageConnection(skin, menuManager, MenuPageConnection.Type.OPTIONMENU));
            addPageEntry(menuManager, x, y - yStep * (i+2), credits, new MenuPageConnection(skin, menuManager, MenuPageConnection.Type.OPTIONMENU));
            //addPageEntry(menuManager, x, y - yStep * (i+2), credits, new MenuPageCredits(skin, menuManager));
            //addActor(text);
            //text.setBounds(x, y, 330, 500);
            //addPageEntry(menuManager, x, y - yStep * (i+4), exit, new MenuPageConnection(skin, menuManager, MenuPageConnection.Type.OPTIONMENU));
            addCenteredExitButton(menuManager.getWidth()-250, 50, 20, 100, exit, () -> System.exit(0));
        }
    }

    //folge content wenn man auf button klickt zum beispiel, keine komplett neue Seite
    protected final void addPageEntry(MenuManager menuManager, int x, int y, DecoImage button, MenuPage page){
        menuManager.addLayer(page);
        //addLeftAlignedButton(x,y,300, 40, button, () -> menuManager.pushPage(page));
        addCenteredImage(x,y,280, 40, button, () -> menuManager.pushPage(page));
    }
}
