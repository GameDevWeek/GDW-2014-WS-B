package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.GameConstants;
import de.hochschuletrier.gdw.ws1415.states.GameplayState;

public class MenuPage extends Group {
    
    protected Main main = Main.getInstance();
    protected AssetManagerX assetManager = main.getAssetManager();
    protected final Skin skin;
    //protected final Skin transparent;
    protected int buttonCount = 2;
    protected DecoImage player3, player4;
    protected Label p3, p4;
    
    public MenuPage(Skin skin) {
    	super(); 
    	this.skin = skin;
    	//transparent = new Skin(Gdx.files.internal("data/skins/sotf.json"));
    	
    	setVisible(false);
    }
    
    public MenuPage(Skin skin, String background){
        super();
        this.skin = skin;
        
        //transparent = new Skin(Gdx.files.internal("data/skins/sotf.json"));
        addActor(new DecoImage(assetManager.getTexture(background)));
        setVisible(false);
    }
    
    protected final void addLeftAlignedButton(int x, int y, int width, int height, DecoImage button, Runnable runnable) {
        button.setPosition(x, y);
        addActor(button);
    }
    
    protected final void addCenteredImage(int x, int y, int width, int height, DecoImage button, Runnable runnable){
        button.setPosition(x , y - height / 2); //vorher noch x+width
        
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
        button.setPosition(x, y - height ); //vorher x-width
        
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
    
    protected final void addPlayerButton(int x, int y, int width, int height, DecoImage button, String name, int index){
        button.setPosition(x, y); 
        addActor(button);
        createLabel(x, y, width, height, index).setText(name);
    }
    
    protected final void addP34Button(int x, int y, int width, int height, DecoImage button, String name, int index){
        button.setPosition(x, y); 
        addActor(button);
        //createLabel(x, y, width, height, index).setText(name); 
        createLabel(x, y, width, height, index); 
    }
    
    private Label createLabel(int x, int y, int width, int height, int index) {
        if(index == 2){
            p3 = new Label(GameConstants.playerNames[index], skin);
            p3.setBounds(x+170, y+20, width, height);
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
            p4 = new Label(GameConstants.playerNames[index], skin);
            p4.setBounds(x+170, y+20, width, height);
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
        Label label = new Label("", skin);
        label.setBounds(x+170, y+20, width, height);
        
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
    
    protected final void addPlayer(int x, int y, int width, int height, String name, Runnable runnable){
        TextButton button = addButton(x, y - height / 2, width, height, name, runnable, "mainMenu");
        
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(buttonCount == 2){
                    player3 = new DecoImage(assetManager.getTexture("player_BLUE"));
                    addP34Button(100, 150, 100, 100, player3, GameConstants.playerNames[buttonCount], 2);
                    GameConstants.p3present = true;
                    buttonCount = 3;
                    return true;
                }
                else if(buttonCount == 3){
                    player4 = new DecoImage(assetManager.getTexture("player_YELLOW"));
                    addP34Button(100, 0, 100, 100, player4, GameConstants.playerNames[buttonCount], 3);
                    GameConstants.p4present = true;
                    buttonCount = 4;
                    return true;
                } 
                return true;
            }
        });
    }
    
    protected final void removePlayer(int x, int y, int width, int height, String name, Runnable runnable){
        TextButton button = addButton(x, y - height / 2, width, height, name, runnable, "mainMenu");
        
        button.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(buttonCount == 4){
                    removeActor(p4);
                    removeActor(player4);
                    GameConstants.p3present = false;
                    buttonCount = 3;
                }
                else if(buttonCount == 3){
                    removeActor(p3);
                    removeActor(player3);
                    GameConstants.p4present = false;
                    buttonCount = 2;
                }
                return true;
            }
        });
    }
}
