package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Wav.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.audio.SoundEmitter;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.GameConstants;
import de.hochschuletrier.gdw.ws1415.states.GameplayState;

public class MenuPage extends Group {
    
    protected Main main = Main.getInstance();
    protected AssetManagerX assetManager = main.getAssetManager();
    protected final Skin skin;
    protected int buttonCount = 2;
    protected DecoImage player3, player4;
    protected Label p3, p4;
    protected boolean hovered = true;
    //private com.badlogic.gdx.audio.Sound sound;
    
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
    
    public MenuPage(Skin skin, String background, float x, float y){
        super();
        this.skin = skin;
        DecoImage tmp = new DecoImage(assetManager.getTexture(background));
        tmp.setSize(x, y);
        addActor(tmp);
        setVisible(false);
    }
    
//    protected final void addLeftAlignedButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
//        button.setPosition(x, y);
//        addActor(button);
//    }
    
    protected final void addCenteredImage(int x, int y, int width, int height, DecoImage button, Runnable runnable, int index){
        button.setPosition(x , y - height / 2); //vorher noch x+width
        
        button.setTouchable(Touchable.enabled);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float width, float height) {
                runnable.run();
            }
        });
        switch(index){
        case 1: addHover(button, new DecoImage(assetManager.getTexture("start_hover")));
        break;
        case 2: addHover(button, new DecoImage(assetManager.getTexture("option_hover")));
        break;
        case 3: addHover(button, new DecoImage(assetManager.getTexture("credits_hover")));
        break;
        }
        
        addHoverSound(button);
        addClickSound(button);
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
        addHoverSound(button);
        addHover(button, new DecoImage(assetManager.getTexture("play_hover")));
        addClickSound(button);
    }
    
    protected final void addCenteredExitButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        button.setPosition(x, y - height ); //vorher x-width
        
        addActor(button);
        
        DecoImage hover = new DecoImage(assetManager.getTexture("beenden_hover"));
        button.setTouchable(Touchable.enabled);
        button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return true;
            }
        });   
        addHover(button, hover);
        addHoverSound(button);
        addClickSound(button);
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
    
    protected final void addPlayerButton(int x, int y, int width, int height, DecoImage button, String name, int index){
        button.setPosition(x, y); 
        button.setTouchable(Touchable.enabled);
        //addHoverSound(button);
        addClickSound(button);
        addActor(button);
        createLabel(x, y, width, height, index).setText(name);
    }
    
    protected final void addP34Button(int x, int y, int width, int height, DecoImage button, String name, int index){
        button.setPosition(x, y); 
        button.setTouchable(Touchable.enabled);
    
        addHoverSound(button);
       
        addClickSound(button);
        addActor(button);
        //createLabel(x, y, width, height, index).setText(name); 
        createLabel(x, y, width, height, index); 
    }
    
    private Label createLabel(int x, int y, int width, int height, int index) {
        if(index == 2){
            p3 = new Label(GameConstants.playerNames[index], skin, "connectionTitle");
            p3.setBounds(x+125, y+20, width, height);
            p3.addListener(new InputListener(){
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    TextField name = new TextField("", skin, "transparent");
                    //name.setPosition(600, 506); //funktioniert !!
                    p3.setText("");
                    
                    //name.setPosition(p3.getX()-45, p3.getY()+30);
                    name.setPosition(p3.getX()-45, p3.getY()+30);
                    name.addListener(new InputListener(){
                        public boolean keyDown (InputEvent event, int keycode) {
                            if(keycode == Keys.ENTER){
                                p3.setText(name.getText());
                                GameConstants.playerNames[index] = p3.getText().toString();
                                removeActor(name);
                            }
                            return true;
                        } 
                        
                    });

                    addActor(name);
                    return true;
                }
             });
            addActor(p3);
            return p3;
        }
        else if(index == 3){
            p4 = new Label(GameConstants.playerNames[index], skin, "connectionTitle");
            p4.setBounds(x+125, y+20, width, height);
            p4.addListener(new InputListener(){
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    TextField name = new TextField("", skin, "transparent");
                    //name.setPosition(600, 506); //funktioniert !!
                    p4.setText("");
                    name.setPosition(p4.getX()-45, p4.getY()+30);
                    name.addListener(new InputListener(){
                        public boolean keyDown (InputEvent event, int keycode) {
                            if(keycode == Keys.ENTER){
                                p4.setText(name.getText());
                                GameConstants.playerNames[index] = p4.getText().toString();
                                removeActor(name);
                            }
                            return true;
                        } 
                        
                    });

                    addActor(name);
                    return true;
                }
             });
            addActor(p4);
            return p4;
  
        }
        else{
        Label label = new Label("", skin, "connectionTitle");
        label.setBounds(x+125, y+20, width, height);
        
        label.addListener(new InputListener(){
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
               TextField name = new TextField("", skin, "transparent");
               //name.setPosition(600, 506); //funktioniert !!
               label.setText("");
               name.setPosition(label.getX()-45, label.getY()+30);
               name.addListener(new InputListener(){
                   public boolean keyDown (InputEvent event, int keycode) {
                       if(keycode == Keys.ENTER){
                           label.setText(name.getText());
                           GameConstants.playerNames[index] = label.getText().toString();
                           removeActor(name);
                       }
                       return true;
                   } 
                   
               });

               addActor(name);
               return true;
           }
        });
        addActor(label);
        return label;
        }
    }
    
    protected final void addPlayer(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x - 80, y-height/2);
        button.setTouchable(Touchable.enabled);
        addHoverSound(button);
        addHover(button, new DecoImage(assetManager.getTexture("add_hover")));
        addClickSound(button);
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(buttonCount == 2){
                    player3 = new DecoImage(assetManager.getTexture("player_BLUE"));
                    addP34Button(627, 150, 100, 100, player3, GameConstants.playerNames[buttonCount], 2);
                    GameConstants.p3present = true;
                    buttonCount = 3;
                    return true;
                }
                else if(buttonCount == 3){
                    player4 = new DecoImage(assetManager.getTexture("player_YELLOW"));
                    addP34Button(627, 0, 100, 100, player4, GameConstants.playerNames[buttonCount], 3);
                    GameConstants.p4present = true;
                    buttonCount = 4;
                    return true;
                } 
                
                return true;
            }
        });
        addActor(button);
    }
    
    
    protected final void removePlayer(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x - 80, y -170);
        button.setTouchable(Touchable.enabled);
        addClickSound(button);
        button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(buttonCount == 4){
                    removeActor(p4);
                    removeActor(player4);
                    GameConstants.p4present = false;
                    buttonCount = 3;
                }
                else if(buttonCount == 3){
                    removeActor(p3);
                    removeActor(player3);
                    GameConstants.p3present = false;
                    buttonCount = 2;
                }
                return true;
            }
        });
        addHoverSound(button);
        addHover(button, new DecoImage(assetManager.getTexture("remove_hover")));
        addActor(button);
    }
    
    protected final void addHoverSound(DecoImage button){

        button.addListener(new InputListener(){
           
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
               // sound = assetManager.getSound("click");
                    SoundEmitter.playGlobal(assetManager.getSound("hover"), false);
            }
        });
    }
    
    protected final void addClickSound(DecoImage button){
        button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
               // sound = assetManager.getSound("click");
                SoundEmitter.playGlobal(assetManager.getSound("accept"), false);
                return true;
            }
        });
    }
    
    protected final void addHover(DecoImage button, DecoImage hover){
        button.addListener(new InputListener(){
            
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){                
                hover.setPosition(button.getX(), button.getY());
                addActor(hover);  
            }
        });
        button.addListener(new InputListener(){
                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                    removeActor(hover);
                    addActor(button);
                    hovered = true;
                }
            });
        
    }
}
