package de.hochschuletrier.gdw.ws1415.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.commons.gdx.state.BaseGameState;
import de.hochschuletrier.gdw.commons.gdx.utils.DrawUtil;
import de.hochschuletrier.gdw.ws1415.Main;

public class LoadGameState extends BaseGameState {

    private boolean isDone;
    private final Texture loadscreen;
    private final AssetManagerX assetManager;
    private final Runnable completeFunc;

    public LoadGameState(AssetManagerX assetManager, Runnable completeFunc) {
        this.assetManager = assetManager;
        this.completeFunc = completeFunc;
        loadscreen = new Texture(Gdx.files.internal("data/images/ladescreen.png"));
    }

    public void render() {
        Main.getInstance().screenCamera.bind();
        Color onLoad = new Color(Color.rgb888(14, 51, 91));
        DrawUtil.fillRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Color.BLACK);
        
        float x = (Gdx.graphics.getWidth() - loadscreen.getWidth()) / 2;
        float y = (Gdx.graphics.getHeight() - loadscreen.getHeight()) / 2;
        
        DrawUtil.draw(loadscreen, x-15, y, 1100, 635);
        DrawUtil.fillRect(Gdx.graphics.getWidth() / 2 - 335, Gdx.graphics.getHeight() / 2 +114, (int) (755f * assetManager.getProgress()), 40, onLoad);

    }

    @Override
    public void update(float delta) {
        if (!isDone) {
            assetManager.update();

            if (assetManager.getProgress() == 1) {
                completeFunc.run();
                isDone = true;
            }
        }

        render();
    }

    @Override
    public void dispose() {
    }
}
