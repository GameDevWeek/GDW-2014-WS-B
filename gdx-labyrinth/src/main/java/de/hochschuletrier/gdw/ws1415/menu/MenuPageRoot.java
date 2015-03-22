package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;

public class MenuPageRoot extends MenuPage {

//    public enum Type{
//        MAINMENU
//    }
    
    public MenuPageRoot(Skin skin, MenuManager menuManager) {
        super(skin, "menuscreen", 1024, 600);
        // TODO Auto-generated constructor stub
//      DecoImage test = new DecoImage(assetManager.getTexture("background"));
//        test.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        //test.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        
        
        
//        Texture background = new Texture(Gdx.files.internal("data/images/menuscreen.png"));
//        float tex_x = (Gdx.graphics.getWidth()/2 - background.getWidth()) / 2;
//        float tex_y = (Gdx.graphics.getHeight()/2 - background.getHeight()) / 2;
//        // draw(Texture texture, float x, float y, int srcX, int srcY, float width, float height,
//        //float scaleX, float scaleY, float rotation)
//        DrawUtil.draw(background, tex_x, tex_y, 50, 50, 1412, 900, 1.0f, 1.0f, 0);
//        
        int x = 355;  //100
        int i = 0;
        int y = 350;
        int yStep = 55;
        
       
        DecoImage start = new DecoImage(assetManager.getTexture("start_button"));
        DecoImage options = new DecoImage(assetManager.getTexture("optionen_button"));
        DecoImage credits = new DecoImage(assetManager.getTexture("credits_button"));
        DecoImage exit = new DecoImage(assetManager.getTexture("beenden_button"));
        
        //if(type == Type.MAINMENU){
            //addCenteredPlayButton(menuManager.getWidth() - 365, x, y - yStep * (i), 40, start, () -> {});
            //addCenteredPlayButton(menuManager.getWidth() - 365, 450, 280, 40, start, () -> {});
            addPageEntry(menuManager, x, y - yStep * i, start, new MenuPageGame(skin, menuManager), 1);
            addPageEntry(menuManager, x, y - yStep * (i+2), options, new MenuPageOptions(skin, menuManager), 2);
            addPageEntry(menuManager, x, y - yStep * (i+4), credits, new MenuPageCredits(skin, menuManager), 3);
            addCenteredExitButton(menuManager.getWidth()- 669, 40, 280, 40, exit, () -> System.exit(0));
        //}
    }

    protected final void addPageEntry(MenuManager menuManager, int x, int y, DecoImage button, MenuPage page, int index){
        menuManager.addLayer(page);
        addCenteredImage(x,y,280, 40, button, () -> menuManager.pushPage(page), index);
    }
}
