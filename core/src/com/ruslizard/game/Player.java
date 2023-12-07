package com.ruslizard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ruslizard.game.controllers.CollisionController;

public class Player {

    private Animation<TextureRegion> playerAnimation;

    private final SpriteBatch spriteBatch;

    private final Vector2 position;
    private float stateTime;
    Direction direction = Direction.DOWN;

    private final Rectangle bounds;
    private final CollisionController collisionController;
    private static final float SPEED = 1.5f;

    private TextureRegion currentFrame;
    private boolean isAttack = false;
    private final Rectangle boundsAttack;

//    private final Sound sound;

    private final TextureRegion[] playerMoveRight = {new TextureRegion(new Texture("Player/playerMoveRight/0.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/1.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/2.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/3.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/4.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/5.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/6.png")),
            new TextureRegion(new Texture("Player/playerMoveRight/7.png"))};

    private final TextureRegion[] playerMoveLeft = {new TextureRegion(new Texture("Player/playerMoveLeft/0.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/1.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/2.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/3.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/4.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/5.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/6.png")),
            new TextureRegion(new Texture("Player/playerMoveLeft/7.png"))};

    private final TextureRegion[] playerMoveDown = {new TextureRegion(new Texture("Player/playerMoveDown/0.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/1.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/2.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/3.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/4.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/5.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/6.png")),
            new TextureRegion(new Texture("Player/playerMoveDown/7.png"))};

    private final TextureRegion[] playerMoveUp = {new TextureRegion(new Texture("Player/playerMoveUp/0.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/1.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/2.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/3.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/4.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/5.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/6.png")),
            new TextureRegion(new Texture("Player/playerMoveUp/7.png"))};

    private final TextureRegion[] playerAttackRight = {new TextureRegion(new Texture("Player/playerAttackRight/0.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/1.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/2.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/3.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/4.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/5.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/6.png")),
            new TextureRegion(new Texture("Player/playerAttackRight/7.png"))};

    private final TextureRegion[] playerAttackUp = {new TextureRegion(new Texture("Player/playerAttackUp/0.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/1.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/2.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/3.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/4.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/5.png")),
            new TextureRegion(new Texture("Player/playerAttackUp/6.png"))};


    private final TextureRegion[] playerAttackDown = {new TextureRegion(new Texture("Player/playerAttackDown/0.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/1.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/2.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/3.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/4.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/5.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/6.png")),
            new TextureRegion(new Texture("Player/playerAttackDown/7.png"))};

    private final TextureRegion[] playerAttackLeft = {new TextureRegion(new Texture("Player/playerAttackLeft/0.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/1.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/2.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/3.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/4.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/5.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/6.png")),
            new TextureRegion(new Texture("Player/playerAttackLeft/7.png"))};


    private final TextureRegion[] playerStayRight = {new TextureRegion(new Texture("Player/playerMoveRight/0.png"))};
    private final TextureRegion[] playerStayLeft = {new TextureRegion(new Texture("Player/playerMoveLeft/0.png"))};
    private final TextureRegion[] playerStayUp = {new TextureRegion(new Texture("Player/playerMoveUp/0.png"))};
    private final TextureRegion[] playerStayDown = {new TextureRegion(new Texture("Player/playerMoveDown/0.png"))};


    public Player(CollisionController collisionController) {
        this.collisionController = collisionController;
        playerAnimation = new Animation<>(0.15f, playerStayDown);

        position = new Vector2();
        stateTime = 0;
        spriteBatch = new SpriteBatch();

        bounds = new Rectangle(position.x+10, position.y+8, playerAnimation.getKeyFrame(stateTime).getRegionWidth()-20, playerAnimation.getKeyFrame(stateTime).getRegionHeight()-18);
        boundsAttack = new Rectangle(position.x,position.y,37, 39);

//        sound = Gdx.audio.newSound(Gdx.files.internal("GOYDA.mp3"));
    }

    public void updateBoundsAttack(){
        boundsAttack.setPosition(position.x+10,position.y+playerAnimation.getKeyFrame(stateTime).getRegionWidth()-20);
        if (direction == Direction.UP || direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT){
            boundsAttack.height = 12;
            boundsAttack.width = 37;
            boundsAttack.setPosition(position.x+10,position.y+playerAnimation.getKeyFrame(stateTime).getRegionHeight()-10);
        }else if (direction == Direction.DOWN || direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT){
            boundsAttack.height = 12;
            boundsAttack.width = 37;
            boundsAttack.setPosition(position.x+10,position.y-(playerAnimation.getKeyFrame(stateTime).getRegionHeight())+53);
        }else if (direction == Direction.RIGHT){
            boundsAttack.height = 39;
            boundsAttack.width = 12;
            boundsAttack.setPosition(position.x+10+playerAnimation.getKeyFrame(stateTime).getRegionWidth()-20,position.y+8);
        }else if (direction == Direction.LEFT){
            boundsAttack.height = 39;
            boundsAttack.width = 12;
            boundsAttack.setPosition(position.x+10-playerAnimation.getKeyFrame(stateTime).getRegionWidth()+45,position.y+8);
        }

    }

    public void update() {
        bounds.setPosition(position.x+10,position.y+8);
        if (direction == Direction.UP){
            if (collisionController.checkCollision(bounds)){
                position.y = position.y - SPEED;
            }
        }else if (direction == Direction.DOWN){
            if (collisionController.checkCollision(bounds)) {
                position.y = position.y + SPEED;
            }
        }else if (direction == Direction.RIGHT){
            if (collisionController.checkCollision(bounds)){
                position.x = position.x - SPEED;
            }
        }else if (direction == Direction.LEFT){
            if (collisionController.checkCollision(bounds)){
                position.x = position.x + SPEED;
            }
        }else if (direction == Direction.UP_RIGHT){
            if (collisionController.checkCollision(bounds)){
                position.x = position.x - SPEED;
                position.y = position.y - SPEED;
            }
        }else if (direction == Direction.UP_LEFT){
            if (collisionController.checkCollision(bounds)){
                position.x = position.x + SPEED;
                position.y = position.y - SPEED;
            }
        } else if (direction == Direction.DOWN_LEFT) {
            if (collisionController.checkCollision(bounds)){
                position.x = position.x + SPEED;
                position.y = position.y + SPEED;
            }
        }else if (direction == Direction.DOWN_RIGHT){
            if (collisionController.checkCollision(bounds)){
                position.x = position.x - SPEED;
                position.y = position.y + SPEED;
            }
        }
    }

    public float getBoundsX() {
        return bounds.getX();
    }

    public float getBoundsY() {
        return bounds.getY();
    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && !isAttack) {
            position.x += SPEED;
            setPlayerAnimation(playerMoveRight);
            direction = Direction.RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.x += SPEED;
            position.y +=SPEED;
            setPlayerAnimation(playerMoveUp);
            direction = Direction.UP_RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.x -= SPEED;
            position.y +=SPEED;
            setPlayerAnimation(playerMoveUp);
            direction = Direction.UP_LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.x -= SPEED;
            position.y -=SPEED;
            setPlayerAnimation(playerMoveDown);
            direction = Direction.DOWN_LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.x += SPEED;
            position.y -=SPEED;
            setPlayerAnimation(playerMoveDown);
            direction = Direction.DOWN_RIGHT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && !isAttack) {
            position.x -= SPEED;
            setPlayerAnimation(playerMoveLeft);
            direction = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !isAttack) {
            position.y += SPEED;
            setPlayerAnimation(playerMoveUp);
            direction = Direction.UP;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !isAttack) {
            position.y -= SPEED;
            direction = Direction.DOWN;
            setPlayerAnimation(playerMoveDown);
        }else if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)
                && !Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN) && !isAttack) {
            if (direction == Direction.UP) {
                setPlayerAnimation(playerStayUp);
            } else if (direction == Direction.LEFT) {
                setPlayerAnimation(playerStayLeft);
            } else if (direction == Direction.RIGHT) {
                setPlayerAnimation(playerStayRight);
            } else {
                setPlayerAnimation(playerStayDown);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z) && !isAttack && (direction!=Direction.UP_LEFT && direction!=Direction.UP_RIGHT
        && direction!=Direction.DOWN_LEFT && direction!=Direction.DOWN_RIGHT)) {
//            sound.play();
//            sound.setVolume(1);
            isAttack = true;
            stateTime = 0;
            switch (direction){
                case RIGHT:
                    setPlayerAttackAnimation(playerAttackRight);
                    break;
                case UP:
                    setPlayerAttackAnimation(playerAttackUp);
                    break;
                case DOWN:
                    setPlayerAttackAnimation(playerAttackDown);
                    break;
                case LEFT:
                    setPlayerAttackAnimation(playerAttackLeft);
                    break;
            }
        }
    }

    void setPlayerAnimation(TextureRegion[] playerFrames) {
        playerAnimation = new Animation<>(0.12f, playerFrames);
    }

    void setPlayerAttackAnimation(TextureRegion[] playerFrames){
        playerAnimation = new Animation<>(0.07f, playerFrames);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void draw(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        if (isAttack) {
            currentFrame = playerAnimation.getKeyFrame(stateTime, false); // Play animation only once
            if (playerAnimation.isAnimationFinished(stateTime)) {
                isAttack = false;
                setPlayerAnimation(playerStayDown); // Replace with the desired idle animation
            }
            stateTime += Gdx.graphics.getDeltaTime();
        } else {
            currentFrame = playerAnimation.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true);
        }


//        currentFrame = playerAnimation.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime(), true);
        spriteBatch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        spriteBatch.end();

    }

    public float getPositionX() {
        return position.x;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public float getPositionY() {
        return position.y;
    }

    public float getBoundsAttackX() {
        return boundsAttack.x;
    }

    public float getBoundsAttackY() {
        return boundsAttack.y;
    }

    public Rectangle getBoundsAttack() {
        return boundsAttack;
    }
}
