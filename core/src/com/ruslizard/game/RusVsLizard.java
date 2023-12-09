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

	private Lizard lizard;

	private AttackSystem attackSystem;

//	private Music music;

	private HeartScreen heartScreen;


	@Override
	public void create() {
//		music = Gdx.audio.newMusic(Gdx.files.internal("main.mp3"));
//		music.setLooping(true);
//
//		music.setVolume(0.1f);

		heartScreen = new HeartScreen();



		camera = new OrthographicCamera();
		cameraController = new CameraController(camera);

		tiledMap = new TmxMapLoader().load("Map\\Test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);


		spriteBatch = new SpriteBatch();

		lizard = new Lizard();
		lizard.setPosition(500,500);

		player = new Player(new CollisionController(tiledMap,lizard));
		player.setPosition(800, 500);


		shapeRenderer = new ShapeRenderer();

		attackSystem = new AttackSystem(player,lizard);
	}



	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		cameraController.controlCamera(tiledMap, player.getPositionX(),player.getPositionY());

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		heartScreen.render(Gdx.graphics.getDeltaTime());

		lizard.draw(camera);
		lizard.update();
		player.move();
		player.attack(lizard.getBounds());
		player.draw(camera);
		player.update();
		player.updateBoundsAttack();

		attackSystem.attack();


		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.rect(lizard.getBounds().x, lizard.getBounds().y, lizard.getBounds().width, lizard.getBounds().height);
		shapeRenderer.rect(player.getBoundsX(), player.getBoundsY(), player.getCurrentFrame().getRegionWidth()-20,player.getCurrentFrame().getRegionHeight()-18);
		shapeRenderer.rect(player.getBoundsAttackX(),player.getBoundsAttackY(),player.getBoundsAttack().width,player.getBoundsAttack().height);
		shapeRenderer.setProjectionMatrix(camera.combined);
		for (MapObject mapObject :
			tiledMap.getLayers().get("collision").getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
			shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.width, rectangle.height);
		}

		shapeRenderer.end();

//		music.play();
	}

	@Override
	public void dispose() {
		tiledMap.dispose();
	}
}
