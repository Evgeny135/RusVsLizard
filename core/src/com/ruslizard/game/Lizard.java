package com.ruslizard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Lizard {
    private Animation<TextureRegion> playerAnimation;

    private final SpriteBatch spriteBatch;

    private final Vector2 position;

    private float speed;
    private float stateTime;
    Direction direction = Direction.DOWN;
    private int HP = 10;
    private final Rectangle bounds;
//    private final CollisionController collisionController;

    private final TextureRegion[] lizardStayDown = {new TextureRegion(new Texture("Lizard/lizardMoveDown/0.png"))};
    private final TextureRegion[] lizardStayDeath = {new TextureRegion(new Texture("Lizard/lizardDeath/7.png"))};

    private final TextureRegion[] lizardDeath = {new TextureRegion(new Texture("Lizard/lizardDeath/0.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/1.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/2.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/3.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/4.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/5.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/6.png")),
            new TextureRegion(new Texture("Lizard/lizardDeath/7.png"))};

    private final TextureRegion[] lizardRunDown = {new TextureRegion(new Texture("Lizard/lizardRunDown/0.png")),
            new TextureRegion(new Texture("Lizard/lizardRunDown/1.png")),
            new TextureRegion(new Texture("Lizard/lizardRunDown/2.png")),
            new TextureRegion(new Texture("Lizard/lizardRunDown/3.png")),
            new TextureRegion(new Texture("Lizard/lizardRunDown/4.png")),
            new TextureRegion(new Texture("Lizard/lizardRunDown/5.png"))};

    private final TextureRegion[] lizardRunRight = {new TextureRegion(new Texture("Lizard/lizardRunRight/0.png")),
            new TextureRegion(new Texture("Lizard/lizardRunRight/1.png")),
            new TextureRegion(new Texture("Lizard/lizardRunRight/2.png")),
            new TextureRegion(new Texture("Lizard/lizardRunRight/3.png")),
            new TextureRegion(new Texture("Lizard/lizardRunRight/4.png")),
            new TextureRegion(new Texture("Lizard/lizardRunRight/5.png"))};

    private final TextureRegion[] lizardRunLeft = {new TextureRegion(new Texture("Lizard/lizardRunLeft/0.png")),
            new TextureRegion(new Texture("Lizard/lizardRunLeft/1.png")),
            new TextureRegion(new Texture("Lizard/lizardRunLeft/2.png")),
            new TextureRegion(new Texture("Lizard/lizardRunLeft/3.png")),
            new TextureRegion(new Texture("Lizard/lizardRunLeft/4.png")),
            new TextureRegion(new Texture("Lizard/lizardRunLeft/5.png"))};

    private final TextureRegion[] lizardRunUp = {new TextureRegion(new Texture("Lizard/lizardRunUp/0.png")),
            new TextureRegion(new Texture("Lizard/lizardRunUp/1.png")),
            new TextureRegion(new Texture("Lizard/lizardRunUp/2.png")),
            new TextureRegion(new Texture("Lizard/lizardRunUp/3.png")),
            new TextureRegion(new Texture("Lizard/lizardRunUp/4.png")),
            new TextureRegion(new Texture("Lizard/lizardRunUp/5.png"))};

    private TextureRegion currentFrame;

    private boolean deathFinished = false;

    public Lizard() {
        playerAnimation = new Animation<>(0.15f, lizardStayDown);

        speed = 50;

        this.position = new Vector2();
        stateTime = 0;
        this.spriteBatch = new SpriteBatch();

        bounds = new Rectangle(position.x, position.y, playerAnimation.getKeyFrame(stateTime).getRegionWidth(), playerAnimation.getKeyFrame(stateTime).getRegionHeight());
    }

    public void checkCollisionPlayer(Rectangle player){
        if (bounds.overlaps(player)){
            speed =0;
            position.x-=1;
        }
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void update(){
        bounds.set(position.x+7, position.y+4, playerAnimation.getKeyFrame(stateTime).getRegionWidth()-10, playerAnimation.getKeyFrame(stateTime).getRegionHeight()-17);
    }

    public void setHp(int hp){
        HP = hp;
    }

    public void checkHp(){
        if (HP <= 0){
            setLizardAnimation(lizardDeath);
            speed = 0;
        }
    }

    public void move(Vector2 playerPosition){
        Vector2 direction = playerPosition.cpy().sub(position).nor();
        position.add(direction.scl(speed).scl(Gdx.graphics.getDeltaTime()));
        if (direction.y < 0){
            setLizardAnimation(lizardRunDown);
        } if (direction.y>0){
            setLizardAnimation(lizardRunUp);
        }
         if (direction.x >0){
            setLizardAnimation(lizardRunRight);
        }
         if (direction.x <0){
            setLizardAnimation(lizardRunLeft);
        }
    }

    void setLizardAnimation(TextureRegion[] playerFrames){
        playerAnimation = new Animation<>(0.15f, playerFrames);
    }

    public void draw(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        currentFrame = playerAnimation.getKeyFrame(stateTime+= Gdx.graphics.getDeltaTime(), true);
        if (HP <= 0 && playerAnimation.isAnimationFinished(stateTime)){
            setLizardAnimation(lizardStayDeath);
            deathFinished = true;
        }
        spriteBatch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        spriteBatch.end();

    }

    public boolean isDeathFinished() {
        return deathFinished;
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
