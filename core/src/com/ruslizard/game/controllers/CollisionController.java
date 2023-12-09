package com.ruslizard.game.controllers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.ruslizard.game.Lizard;
import com.ruslizard.game.LizardFactory;

public class CollisionController {
    private LizardFactory lizardFactory;
    private MapObjects mapObjects;

    public boolean checkCollisionMap(Rectangle object) {
        for (MapObject mapObject : mapObjects) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            if (rectangle.overlaps(object)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionLizard(Rectangle object){
        for (Lizard l:
             lizardFactory.getLizardList()) {
            if (l.getBounds().overlaps(object)){
                return true;
            }
        }
        return false;
    }

    public MapObjects getCollisionObjects() {
        return mapObjects;
    }

    public CollisionController(TiledMap tiledMap, LizardFactory lizardFactory) {
        this.mapObjects = tiledMap.getLayers().get("collision").getObjects();
        this.lizardFactory = lizardFactory;
    }
}
