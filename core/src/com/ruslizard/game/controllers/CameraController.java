package com.ruslizard.game.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;

public class CameraController {
    private OrthographicCamera camera;


    public void controlCamera(TiledMap tiledMap, float x, float y ){
        int mapWidth = tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class);
        int mapHeight = tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class);

        float cameraMinX = camera.viewportWidth / 2;
        float cameraMaxX = mapWidth - camera.viewportWidth / 2;
        float cameraMinY = camera.viewportHeight / 2;
        float cameraMaxY = mapHeight - camera.viewportHeight / 2;


        camera.position.set(x,y,0);
        camera.position.x = MathUtils.clamp(camera.position.x, cameraMinX, cameraMaxX);
        camera.position.y = MathUtils.clamp(camera.position.y, cameraMinY, cameraMaxY);
        camera.update();
    }

    public CameraController(OrthographicCamera camera) {
        this.camera = camera;
        camera.setToOrtho(false,960, 540);
//        camera.setToOrtho(false,1800,1200);
    }

}
