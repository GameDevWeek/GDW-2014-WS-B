package de.hochschuletrier.gdw.ws1415.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.hochschuletrier.gdw.commons.devcon.cvar.CVarBool;
import de.hochschuletrier.gdw.commons.gdx.assets.AnimationExtended;
import de.hochschuletrier.gdw.commons.gdx.assets.AssetManagerX;
import de.hochschuletrier.gdw.commons.gdx.input.hotkey.Hotkey;
import de.hochschuletrier.gdw.commons.gdx.input.hotkey.HotkeyModifier;
import de.hochschuletrier.gdw.commons.gdx.physix.PhysixBodyDef;
import de.hochschuletrier.gdw.commons.gdx.physix.PhysixComponentAwareContactListener;
import de.hochschuletrier.gdw.commons.gdx.physix.PhysixFixtureDef;
import de.hochschuletrier.gdw.commons.gdx.physix.components.PhysixBodyComponent;
import de.hochschuletrier.gdw.commons.gdx.physix.components.PhysixModifierComponent;
import de.hochschuletrier.gdw.commons.gdx.physix.systems.PhysixDebugRenderSystem;
import de.hochschuletrier.gdw.commons.gdx.physix.systems.PhysixSystem;
import de.hochschuletrier.gdw.ws1415.Main;
import de.hochschuletrier.gdw.ws1415.game.components.AnimationComponent;
import de.hochschuletrier.gdw.ws1415.game.components.ImpactSoundComponent;
import de.hochschuletrier.gdw.ws1415.game.components.PositionComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TextureComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TileComponent;
import de.hochschuletrier.gdw.ws1415.game.components.TriggerComponent;
import de.hochschuletrier.gdw.ws1415.game.contactlisteners.ImpactSoundListener;
import de.hochschuletrier.gdw.ws1415.game.contactlisteners.TriggerListener;
import de.hochschuletrier.gdw.ws1415.game.systems.AnimationRenderSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.RenderingSystem;
import de.hochschuletrier.gdw.ws1415.game.systems.UpdatePositionSystem;
import de.hochschuletrier.gdw.ws1415.game.utils.PhysixUtil;

import java.util.function.Consumer;

public class Game {

	private final PooledEngine engine = new PooledEngine(
			GameConstants.ENTITY_POOL_INITIAL_SIZE,
			GameConstants.ENTITY_POOL_MAX_SIZE,
			GameConstants.COMPONENT_POOL_INITIAL_SIZE,
			GameConstants.COMPONENT_POOL_MAX_SIZE);
	// systems
	private final RenderingSystem renderingSystem = new RenderingSystem(
			GameConstants.PRIORITY_RENDERING);


	public Game() {

	}

	public void dispose() {

	}

	public void init(AssetManagerX assetManager) {
		
		addSystems();
		
		createTile(assetManager);
	}

	private void addSystems() {
		engine.addSystem(renderingSystem);
	}

	public void update(float delta) {
		Main.getInstance().screenCamera.bind();
		engine.update(delta);
	}

	public void createTile(AssetManagerX assetManager) {
		Entity entity = engine.createEntity();
		entity.add(engine.createComponent(PositionComponent.class));
		entity.add(engine.createComponent(TileComponent.class));
		entity.add(engine.createComponent(TextureComponent.class));

		entity.getComponent(TextureComponent.class).texture = assetManager
				.getTexture("cross");

		entity.getComponent(PositionComponent.class).x = 500f;
		entity.getComponent(PositionComponent.class).y = 500f;
		
		engine.addEntity(entity);
	}
}
