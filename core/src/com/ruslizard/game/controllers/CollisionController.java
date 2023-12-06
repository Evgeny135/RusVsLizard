package com.ruslizard.game.controllers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class CollisionController {
    private MapObjects mapObjects;

    public boolean checkCollision(Rectangle object) {
        for (MapObject mapObject : mapObjects) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            if (rectangle.overlaps(object)) {
                return true;
            }
        }
        return false;
    }


    public MapObjects getCollisionObjects() {
        return mapObjects;
    }

    public CollisionController(TiledMap tiledMap) {
        this.mapObjects = tiledMap.getLayers().get("collision").getObjects();
    }
}
