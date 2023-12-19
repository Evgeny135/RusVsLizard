package com.ruslizard.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Water {
    private final Texture texture;
    private final Vector2 position;
    private final Rectangle bounds;
    private boolean isCollected;

    private SpriteBatch spriteBatch;

    public Water(float x, float y) {
        texture = new Texture("water.png");
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        isCollected = false;
        spriteBatch = new SpriteBatch();
    }

    public void render(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(texture, position.x, position.y);
        spriteBatch.end();
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}