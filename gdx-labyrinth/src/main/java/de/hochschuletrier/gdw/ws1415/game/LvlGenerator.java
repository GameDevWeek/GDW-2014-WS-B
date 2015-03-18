package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileType;

public class LvlGenerator {
    public static void generate(AssetManagerX assetMannager, Engine engin) {
     // LevelGeneration
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {

                createTile(TileType.cross, assetManager, x, y);

            }
        }
        
        
        
        
    }
    
    public void createTile(TileType tileType, AssetManagerX assetManager,
            float x, float y) {
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(PositionComponent.class));
        entity.add(engine.createComponent(TileComponent.class));
        entity.add(engine.createComponent(TextureComponent.class));

        // int[] rotationValArray = {0, 90, 180, 270};
        // TileType[] tileTypeArray = {TileType.cross, TileType.lShape,};

        // randomTile
        double random = Math.random();
        double random2 = Math.random();
        if (random < 0.25) {
            System.out.println("cross");
            entity.getComponent(TileComponent.class).tileType = TileType.cross;
            entity.getComponent(TextureComponent.class).texture = assetManager
                    .getTexture("cross");
        } else if (random < 0.5) {

            System.out.println("straight");
            entity.getComponent(TileComponent.class).tileType = TileType.straight;
            if (random2 < 0.5) {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("straightRed");
            } else {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("straightWhite");
            }
        } else if (random < 0.75) {

            System.out.println("lShape");
            entity.getComponent(TileComponent.class).tileType = TileType.lShape;
            if (random2 < 0.5) {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("lShapeGreen");
            } else {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("lShapeBrown");
            }
        } else {

            System.out.println("tShape");
            entity.getComponent(TileComponent.class).tileType = TileType.tShape;
            if (random2 < 0.5) {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("tShapePurple");
            } else {
                entity.getComponent(TextureComponent.class).texture = assetManager
                        .getTexture("tShapeYellow");
            }
        }

        entity.getComponent(TextureComponent.class).background = assetManager
                .getTexture("background");

        entity.getComponent(PositionComponent.class).x = x * 50 + 350f;
        entity.getComponent(PositionComponent.class).y = y * 50 + 100f;

        random = Math.random();
        random2 = Math.random();

        if (random < 0.25) {
            entity.getComponent(PositionComponent.class).rotation = 0f;
        } else if (random < 0.5f) {
            entity.getComponent(PositionComponent.class).rotation = 90f;
            entity.getComponent(PositionComponent.class).x += 50;
        } else if (random < 0.75) {
            entity.getComponent(PositionComponent.class).rotation = 180;
            entity.getComponent(PositionComponent.class).x += 50;
            entity.getComponent(PositionComponent.class).y += 50;
        } else {
            entity.getComponent(PositionComponent.class).rotation = 270f;

            entity.getComponent(PositionComponent.class).y += 50;
        }

        entity.add(engine.createComponent(PositionInLevelComponent.class));
        entity.getComponent(PositionInLevelComponent.class).x = (int) x;
        entity.getComponent(PositionInLevelComponent.class).y = (int) y;
        System.out.println(random + "    " + random2);
        engine.addEntity(entity);
    }
}
