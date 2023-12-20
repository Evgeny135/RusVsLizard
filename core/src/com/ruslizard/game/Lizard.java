package com.ruslizard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

import static com.ruslizard.game.Direction.*;

public class Lizard {
    private Animation<TextureRegion> playerAnimation;

    private final SpriteBatch spriteBatch;

    private final Vector2 position;

    private float speed;
    private float stateTime;
    Vector2 direction;
    private int HP = 10;
    private Rectangle bounds;
    private boolean isDeath = false;

    private Rectangle boundsAttack;

    private boolean isFirst = false;

    private boolean isAttack = false;
    private Direction dir;

    private float deathTimer = 2.9f;

    private float attackTimer = 0.5f;
    private TextureRegion currentFrame;

    private boolean isRemove = false;

    private int playerHp;

    public Lizard(float x, float y) {
        playerAnimation = new Animation<>(0.15f, LizardAnimation.lizardStayDown);

        speed = 50;

        this.position = new Vector2();
        stateTime = 0;
        this.spriteBatch = new SpriteBatch();

        bounds = new Rectangle(position.x, position.y, playerAnimation.getKeyFrame(stateTime).getRegionWidth(), playerAnimation.getKeyFrame(stateTime).getRegionHeight());
        boundsAttack = new Rectangle(position.x, position.y, playerAnimation.getKeyFrame(stateTime).getRegionWidth(), playerAnimation.getKeyFrame(stateTime).getRegionHeight());

        setPosition(x,y);
    }

    public boolean checkCollisionPlayer(Rectangle player) {
        if (bounds.overlaps(player)) {
            speed = 0;
            return true;
        }
        return false;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void update() {
        bounds.set(position.x + 7, position.y + 4, playerAnimation.getKeyFrame(stateTime).getRegionWidth() - 10, playerAnimation.getKeyFrame(stateTime).getRegionHeight() - 17);
        if (isDeath) {
            deathTimer -= Gdx.graphics.getDeltaTime();
            if (deathTimer <= 0) {
                isRemove = true;
            }
        } else {
            stateTime += Gdx.graphics.getDeltaTime();
        }
    }

    public void setHp(int hp) {
        HP = hp;
    }

    public void checkHp() {
        if (HP <= 0) {
            isDeath = true;
            speed = 0;
            stateTime += Gdx.graphics.getDeltaTime();
            setDeathLizardAnimation(LizardAnimation.lizardDeath);
        }
    }

    public void move(Vector2 playerPosition, Rectangle player, boolean isCollision) {
        direction = playerPosition.cpy().sub(position).nor();
        if (!checkCollisionPlayer(player) && !isCollision) {
            position.add(direction.scl(speed).scl(Gdx.graphics.getDeltaTime()));
        }
        if (Math.abs(direction.x) > Math.abs(direction.y)) {
            if (direction.x > 0) {
                dir = RIGHT;
                setLizardAnimation(LizardAnimation.lizardRunRight);
                boundsAttack.set(position.x + 41, position.y + 4, 5, bounds.height);
            } else {
                dir = LEFT;
                setLizardAnimation(LizardAnimation.lizardRunLeft);
                boundsAttack.set(position.x + 3, position.y + 4, 5, bounds.height);
            }
        } else {
            if (direction.y > 0) {
                dir = UP;
                boundsAttack.set(position.x + 7, position.y + 32, bounds.width, 5);
                setLizardAnimation(LizardAnimation.lizardRunUp);
            } else {
                dir = DOWN;
                setLizardAnimation(LizardAnimation.lizardRunDown);
                boundsAttack.set(position.x + 7, position.y, bounds.width, 5);
            }
        }
    }

    public void attack(Rectangle player) {
        if ((boundsAttack.overlaps(player) || checkCollisionPlayer(player)) && !isDeath) {
            speed = 0;
            isAttack = true;
            if (dir == RIGHT) setLizardAnimation(LizardAnimation.lizardAttackRight);
            else if (dir == LEFT) setLizardAnimation(LizardAnimation.lizardAttackLeft);
            else if (dir == UP) setLizardAnimation(LizardAnimation.lizardAttackUp);
            else if (dir == DOWN) setLizardAnimation(LizardAnimation.lizardAttackDown);
        } else {
            isAttack = false;
            speed = 50;
        }
    }

    void setLizardAnimation(TextureRegion[] playerFrames) {
        playerAnimation = new Animation<>(0.15f, playerFrames);
    }

    void setDeathLizardAnimation(TextureRegion[] playerFrames) {
        if (!isFirst) {
            stateTime = 0;
            isFirst = true;
        }
        playerAnimation = new Animation<>(3f, playerFrames);
    }

    public void draw(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        if (isDeath) {
            bounds.width=0;
            bounds.height=0;
            currentFrame = playerAnimation.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), false);
        } else {
            currentFrame = playerAnimation.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true);
        }
        spriteBatch.draw(currentFrame, position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        spriteBatch.end();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lizard lizard = (Lizard) o;
        return Float.compare(speed, lizard.speed) == 0 && Float.compare(stateTime, lizard.stateTime) == 0 && HP == lizard.HP && isDeath == lizard.isDeath && isFirst == lizard.isFirst && isAttack == lizard.isAttack && Float.compare(deathTimer, lizard.deathTimer) == 0 && Float.compare(attackTimer, lizard.attackTimer) == 0 && isRemove == lizard.isRemove && playerHp == lizard.playerHp && Objects.equals(playerAnimation, lizard.playerAnimation) && Objects.equals(spriteBatch, lizard.spriteBatch) && Objects.equals(position, lizard.position) && Objects.equals(direction, lizard.direction) && Objects.equals(bounds, lizard.bounds) && Objects.equals(boundsAttack, lizard.boundsAttack) && dir == lizard.dir && Objects.equals(currentFrame, lizard.currentFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerAnimation, spriteBatch, position, speed, stateTime, direction, HP, bounds, isDeath, boundsAttack, isFirst, isAttack, dir, deathTimer, attackTimer, currentFrame, isRemove, playerHp);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getAttackTimer() {
        return attackTimer;
    }

    public void setAttackTimer(float attackTimer) {
        this.attackTimer = attackTimer;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public int getHP() {
        return HP;
    }

    public Rectangle getBoundsAttack() {
        return boundsAttack;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setBoundsAttack(Rectangle boundsAttack) {
        this.boundsAttack = boundsAttack;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
