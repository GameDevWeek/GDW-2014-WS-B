package de.hochschuletrier.gdw.ss14.sandbox.states;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.Texture;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ss14.sandbox.SandboxGame;
public class MainState extends SandboxGame {

    private AssetManagerX assetManager;
    
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(AssetManagerX assetManager) {
        
        this.assetManager = assetManager;
        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        render();
    }
    
    public void render() {
        DrawUtil.batch.draw(assetManager.getTexture("logo"), 100, 150);
    }
    
    public void test() {
        Skin s = new Skin();
        TextButton buttonPlay = new TextButton("Play", s);
    }

}
