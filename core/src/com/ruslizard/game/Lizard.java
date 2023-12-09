package com.ruslizard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ruslizard.game.Direction;
import com.ruslizard.game.controllers.CollisionController;

public class Lizard {
    private Animation<TextureRegion> playerAnimation;

    private final SpriteBatch spriteBatch;

    private final Vector2 position;
    private float stateTime;
    Direction direction = Direction.DOWN;
    private int HP = 10;
    private final Rectangle bounds;
//    private final CollisionController collisionController;
    private static final float SPEED = 1.5f;

    private final TextureRegion[] lizardStayDown = {new TextureRegion(new Texture("lizardMoveDown/0.png"))};

    private TextureRegion currentFrame;



    public Lizard() {
        playerAnimation = new Animation<>(0.15f, lizardStayDown);

        this.position = new Vector2();
        stateTime = 0;
        this.spriteBatch = new SpriteBatch();

        bounds = new Rectangle(position.x, position.y, playerAnimation.getKeyFrame(stateTime).getRegionWidth(), playerAnimation.getKeyFrame(stateTime).getRegionHeight());
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void update(){
        setPosition(500,500);
        bounds.set(position.x+7, position.y+4, playerAnimation.getKeyFrame(stateTime).getRegionWidth()-10, playerAnimation.getKeyFrame(stateTime).getRegionHeight()-17);
    }

    public void draw(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        currentFrame = playerAnimation.getKeyFrame(stateTime+= Gdx.graphics.getDeltaTime(), true);
        spriteBatch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        spriteBatch.end();

    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
