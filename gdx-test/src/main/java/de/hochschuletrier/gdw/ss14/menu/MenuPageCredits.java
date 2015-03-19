package de.hochschuletrier.gdw.ss14.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;

public class MenuPageCredits extends MenuPage {
    
    private String[] persons;
    
    public MenuPageCredits(Skin skin, MenuManager menuManager){
        super(skin, "logo");
        
        persons = new String[]{"Tobias Meier", "David Siepen", "Martin Keren", "Vanessa Hoff"};
        createLabel(100, 400).setText("" + crew());
        
        addCenteredButton(menuManager.getWidth()-100, 54, 100, 40, "ZURÜCK", ()->menuManager.popPage());
    }
    
    private Label createLabel(int x, int y) {
        Label label = new Label("", skin);
        label.setBounds(x, y, 60, 30);
        addActor(label);
        return label;
    }
    
    private String crew(){
        String s = new String();
        for(int i = 0; i < persons.length; i++){
            s += (persons[i] + '\n');
        }
        return s;
    }
}
