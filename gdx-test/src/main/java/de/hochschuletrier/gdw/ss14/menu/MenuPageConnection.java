package de.hochschuletrier.gdw.ss14.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;

public class MenuPageConnection extends MenuPage{

    //private static final Logger logger = LoggerFactory.getLogger(MenuPageConnection.class);
    
    public enum Type {
        OPTIONMENU("crosshair", "Optionen");

        public final String image;
        public final String acceptText;
        
        Type(String image, String acceptText){
            this.image = image;
            this.acceptText = acceptText;
        }
    }
    
    public MenuPageConnection(Skin skin, MenuManager menuManager, Type type) {
        super(skin, type.image);
        // TODO Auto-generated constructor stub
    }
}
