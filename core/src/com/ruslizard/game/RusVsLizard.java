package com.ruslizard.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ruslizard.game.controllers.CameraController;
import com.ruslizard.game.controllers.CollisionController;
import com.ruslizard.game.screen.HeartScreen;

public class RusVsLizard extends Game{
	private OrthographicCamera camera;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private Player player;
	private CameraController cameraController;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch spriteBatch;

	private TimerIcon timerIcon;
	private AttackSystem attackSystem;

	private Music music;

	private HeartScreen heartScreen;

	private LizardFactory lizardFactory;

	private ScreenDeath screenDeath;

	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("main.mp3"));
		music.setLooping(true);

		music.setVolume(0.1f);


		camera = new OrthographicCamera();
		cameraController = new CameraController(camera);

		tiledMap = new TmxMapLoader().load("Map\\Test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		player = new Player(new CollisionController(tiledMap,lizardFactory));

		spriteBatch = new SpriteBatch();

		lizardFactory = new LizardFactory();
		heartScreen = new HeartScreen(lizardFactory);
		lizardFactory.generateLizard();

		player.setPosition(800, 500);

		shapeRenderer = new ShapeRenderer();

		attackSystem = new AttackSystem(player,lizardFactory);

		timerIcon = new TimerIcon(1520,880);

		screenDeath = new ScreenDeath();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cameraController.controlCamera(tiledMap, player.getPositionX(),player.getPositionY());

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		player.move();
		for (Lizard l :
				lizardFactory.getLizardList()) {

		player.attack(l.getBounds());
		}
		player.draw(camera);
		player.update();
		player.updateBoundsAttack();


		spriteBatch.begin();
		timerIcon.draw(spriteBatch);
		timerIcon.update(player.getDashTimer());
		spriteBatch.end();
		heartScreen.render(Gdx.graphics.getDeltaTime());

		attackSystem.attack();

		lizardFactory.regenerateLizard();

		lizardFactory.drawLizards(camera,player.getPosition(),player.getBound());


		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.setProjectionMatrix(camera.combined);
		for (Lizard l :
				lizardFactory.getLizardList()) {
			shapeRenderer.rect(l.getBounds().x, l.getBounds().y, l.getBounds().width, l.getBounds().height);
		}
		for (Lizard l :
				lizardFactory.getLizardList()) {
			shapeRenderer.rect(l.getBoundsAttack().x, l.getBoundsAttack().y, l.getBoundsAttack().width, l.getBoundsAttack().height);
		}
		shapeRenderer.rect(player.getBoundsX(), player.getBoundsY(), player.getCurrentFrame().getRegionWidth()-20,player.getCurrentFrame().getRegionHeight()-18);
		shapeRenderer.rect(player.getBoundsAttackX(),player.getBoundsAttackY(),player.getBoundsAttack().width,player.getBoundsAttack().height);
		shapeRenderer.setProjectionMatrix(camera.combined);
		for (MapObject mapObject :
			tiledMap.getLayers().get("collision").getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
			shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.width, rectangle.height);
		}

		shapeRenderer.end();

		if (lizardFactory.getPlayerHp()<=0){
			setScreen(screenDeath);
		}

		music.play();
	}

	@Override
	public void dispose() {
		tiledMap.dispose();
	}
}
