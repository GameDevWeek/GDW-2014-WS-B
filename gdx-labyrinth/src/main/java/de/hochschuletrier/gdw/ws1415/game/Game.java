package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.components.NextTileBgRenderComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PlayerInformationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.input.InputManager;
import de.hochschuletrier.gdw.ws1415.game.systems.BackgroundRenderingSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.InputSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.NextTileBgRenderSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.PlayerInformationRenderingSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.PlayerRenderingSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.RenderingSystem;
import de.hochschuletrier.gdw.ws1415.game.utils.GameBoardInformation;

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
	private final PlayerInformationRenderingSystem playerInformationRenderingSystem = new PlayerInformationRenderingSystem(
			GameConstants.PRIORITY_RENDERING);
	private final NextTileBgRenderSystem nextTileBgRenderSystem = new NextTileBgRenderSystem(
			GameConstants.PRIORITY_RENDERING - 1);

	private final BackgroundRenderingSystem backgroundRenderingSystem = new BackgroundRenderingSystem(
			GameConstants.PRIORITY_RENDERING_BACKGROUND);
	
	private final PlayerRenderingSystem playerRenderingSystem = new PlayerRenderingSystem(
			GameConstants.PRIORITY_RENDERING + 1);
	
	// Manager
	private final InputManager inputManager = new InputManager();

	public Game() {

	}

	public void dispose() {

	}

	public void init(AssetManagerX assetManager) {

		GameBoardInformation.ARROWS_WIDTH = (int) Math
				.ceil((Gdx.graphics.getWidth()
						* GameBoardInformation.GAME_SCREEN_WIDTH - GameBoardInformation.TILE_FIELD) / 2);
		GameBoardInformation.ARROWS_HEIGHT = (int) Math.ceil((Gdx.graphics
				.getHeight() - GameBoardInformation.TILE_FIELD) / 2);
		
		GameBoardInformation.MENU_WOODPLANK = assetManager.getTexture("woodplank");

		addSystems();

		// LvlGenerator.generate(assetManager, engine);

		LvlGenerator.generate(assetManager, engine);

		inputManager.init();
	}

	private void addSystems() {
		engine.addSystem(renderingSystem);
		engine.addSystem(inputSystem);
		engine.addSystem(playerInformationRenderingSystem);
		engine.addSystem(nextTileBgRenderSystem);
		engine.addSystem(backgroundRenderingSystem);
		
		engine.addSystem(playerRenderingSystem);
	}

	public void update(float delta) {
		Main.getInstance().screenCamera.bind();
		engine.update(delta);
	}

	// TEST
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
