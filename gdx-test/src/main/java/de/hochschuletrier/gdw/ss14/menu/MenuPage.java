package de.hochschuletrier.gdw.ss14.menu;

import java.awt.event.ActionEvent;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ss14.Main;

public class MenuPage extends Group {
    
    protected Main main = Main.getInstance();
    protected AssetManagerX assetManager = main.getAssetManager();
    protected final Skin skin;
    
    public MenuPage(Skin skin, String background){
        super();
        this.skin = skin;
        
        addActor(new DecoImage(assetManager.getTexture(background)));
        setVisible(false);
    }
    
    protected final void addLeftAlignedButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        //TextButton button = addButton(x, y, width, height, text, runnable, "mainMenu");
        //button.getLabel().setAlignment(Align.left);
        button.setPosition(x, y);
        addActor(button);
    }
    
    protected final void addCenteredImage(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x + width , y - height / 2);
        //button.setAlign(Align.center);
        addActor(button);
    }
    
    protected final void addCenteredButton(int x, int y, int width, int height, String text, Runnable runnable) {
        TextButton button = addButton(x - width / 2, y - height / 2, width, height, text, runnable, "mainMenu");
        button.getLabel().setAlignment(Align.center);
    }
    protected final void addCenteredExitButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        button.setPosition(x - width , y - height );
        addActor(button);        
    }

    protected final TextButton addButton(int x, int y, int width, int height, String text, Runnable runnable, String style) {
        TextButton button = new TextButton(text, skin, style);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                runnable.run();
            }
        });
        addActor(button);
        return button;
    }
}
