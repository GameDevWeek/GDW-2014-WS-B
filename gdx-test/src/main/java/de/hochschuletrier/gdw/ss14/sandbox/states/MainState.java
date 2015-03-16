package de.hochschuletrier.gdw.ss14.sandbox.states;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ss14.sandbox.SandboxGame;

import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;

public class MainState extends SandboxGame {

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(AssetManagerX assetManager) {
        System.out.println("MainStates wurde aufgerufen!");
        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        render();
    }
    
    public void render() {
        // DrawUtil.batch.draw(r, position.x - hw, position.y - hh, hw, hh, w, h, scale, scale, angle + angleAdd);
    }

}
