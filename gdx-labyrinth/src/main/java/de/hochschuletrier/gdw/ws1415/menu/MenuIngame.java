package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.Main;

public class MenuIngame extends MenuPage {
    
    protected Main main = Main.getInstance();
    protected AssetManagerX assetManager = main.getAssetManager();
    //protected final Skin skin;

    public MenuIngame(Skin skin, String s){
        super(skin, "menu_ingame");
        //this.skin = skin;  
    }
}
