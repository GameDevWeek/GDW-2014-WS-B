package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionInLevelComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileType;
import de.hochschuletrier.gdw.ws1415.game.input.InputManager;
import de.hochschuletrier.gdw.ws1415.game.systems.InputSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.RenderingSystem;

public class Game {

	private final PooledEngine engine = new PooledEngine(
			GameConstants.ENTITY_POOL_INITIAL_SIZE,
			GameConstants.ENTITY_POOL_MAX_SIZE,
			GameConstants.COMPONENT_POOL_INITIAL_SIZE,
			GameConstants.COMPONENT_POOL_MAX_SIZE);
	// systems
	private final RenderingSystem renderingSystem = new RenderingSystem(
			GameConstants.PRIORITY_RENDERING);
	private final InputSystem inputSystem = new InputSystem(
			GameConstants.PRIORITY_INPUT);

	// Manager
	private final InputManager inputManager = new InputManager();

	public Game() {

	}

	public void dispose() {

	}

	public void init(AssetManagerX assetManager) {

		addSystems();

		LvlGenerator.generate(assetManager, engine);

		inputManager.init();
	}

	private void addSystems() {
		engine.addSystem(renderingSystem);
		engine.addSystem(inputSystem);
	}

	public void update(float delta) {
		Main.getInstance().screenCamera.bind();
		engine.update(delta);
	}

	public void createArrow(AssetManagerX assetManager, float x, float y,
			float rotation) {
		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		entity.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("arrow");
		entity.getComponent(PositionComponent.class).rotation = rotation;
		entity.getComponent(PositionComponent.class).x = x;
		entity.getComponent(PositionComponent.class).y = y;

		engine.addEntity(entity);

	}

}
