package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.states.GameplayState;

public class MenuPage extends Group {
    
    protected Main main = Main.getInstance();
    protected AssetManagerX assetManager = main.getAssetManager();
    protected final Skin skin;
    protected int buttonCount = 3;
    
    public MenuPage(Skin skin) {
    	super(); 
    	this.skin = skin;
    	
    	setVisible(false);
    }
    
    public MenuPage(Skin skin, String background){
        super();
        this.skin = skin;
        
        addActor(new DecoImage(assetManager.getTexture(background)));
        setVisible(false);
    }
    
    protected final void addLeftAlignedButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        button.setPosition(x, y);
        addActor(button);
    }
    
    protected final void addCenteredImage(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x + width , y - height / 2);
        
        button.setTouchable(Touchable.enabled);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float width, float height) {
                runnable.run();
            }
        });
        addActor(button);
    }

    protected final void addCenteredButton(int x, int y, int width, int height, String text, Runnable runnable) {
        TextButton button = addButton(x - width / 2, y - height / 2, width, height, text, runnable, "mainMenu");
        button.getLabel().setAlignment(Align.center);
    }
    
    protected final void addCenteredPlayButton(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x , y-height);
        
        addActor(button);
        button.setTouchable(Touchable.enabled);
        button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                main.changeState(new GameplayState(assetManager));
                return true;
            }
        });   
    }
    
    protected final void addCenteredExitButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        button.setPosition(x - width , y - height );
        
        addActor(button);
        button.setTouchable(Touchable.enabled);
        button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return true;
            }
        });   
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
    
    protected final void addPlayerButton(int x, int y, int width, int height, DecoImage button, String name){
        button.setPosition(x, y);
        addActor(button);
        createLabel(x, y, width, height).setText(name);
        
        //button.addL
    }
    
    private Label createLabel(int x, int y, int width, int height) {
        Label label = new Label("", skin);
        label.setBounds(x+170, y+20, width, height);
        addActor(label);
        return label;
    }
    
    protected final void addPlayer(int x, int y, int width, int height, String name, Runnable runnable){
        TextButton button = addButton(x, y - height / 2, width, height, name, runnable, "mainMenu");
        
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(buttonCount == 3){
                    DecoImage player = new DecoImage(assetManager.getTexture("player"));
                    addPlayerButton(100, 150, 100, 100, player, "Player " + buttonCount);
                    buttonCount = 4;
                    return true;
                }
                else if(buttonCount == 4){
                    DecoImage player = new DecoImage(assetManager.getTexture("player"));
                    addPlayerButton(100, 0, 100, 100, player, "Player " + buttonCount);
                    buttonCount = 0;
                    return true;
                } 
                return true;
            }
        });
    }
}
