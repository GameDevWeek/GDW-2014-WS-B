package de.hochschuletrier.gdw.ss14.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hochschuletrier.gdw.commons.gdx.assets.AnimationExtended;
import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.sceneanimator.SceneAnimator;
import de.hochschuletrier.gdw.commons.gdx.sceneanimator.SceneAnimatorActor;

//public class MenuPageCredits extends MenuPage {
    
//    private String[] persons;
    
//    public MenuPageCredits(Skin skin, MenuManager menuManager){
//        super(skin, "logo");
//        
//        persons = new String[]{"Tobias Meier", "David Siepen", "Martin Keren", "Vanessa Hoff"};
//        createLabel(100, 400).setText("" + crew());
//        
//        addCenteredButton(menuManager.getWidth()-100, 54, 100, 40, "ZURÜCK", ()->menuManager.popPage());
//    }
//    
//    private Label createLabel(int x, int y) {
//        Label label = new Label("", skin);
//        label.setBounds(x, y, 60, 30);
//        addActor(label);
//        return label;
//    }
//    
//    private String crew(){
//        String s = new String();
//        for(int i = 0; i < persons.length; i++){
//            s += (persons[i] + '\n');
//        }
//        return s;
//    }
//}


public class MenuPageCredits extends MenuPage implements SceneAnimator.Getter {

    private static final Logger logger = LoggerFactory.getLogger(MenuPageCredits.class);

    private SceneAnimator sceneAnimator;

    public MenuPageCredits(Skin skin, MenuManager menuManager) {
        super(skin);

        try {
            //sceneAnimator = new SceneAnimator(this, "data/json/credits.json");
            sceneAnimator = new SceneAnimator(this, "data/json/credits.json");
            addActor(new SceneAnimatorActor(sceneAnimator));
        } catch (Exception ex) {
            logger.error("Error loading credits", ex);
        }

        addCenteredButton(menuManager.getWidth() - 100, 54, 100, 40, "Zurück", () -> menuManager.popPage());
    }

    @Override
    public BitmapFont getFont(String name) {
        return skin.getFont(name);
    }
    @Override
    public AnimationExtended getAnimation(String name) {
        return assetManager.getAnimation(name);
    }

    @Override
    public Texture getTexture(String name) {
        return assetManager.getTexture(name);
    }
}

