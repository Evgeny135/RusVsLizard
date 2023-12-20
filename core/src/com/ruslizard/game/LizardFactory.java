package com.ruslizard.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LizardFactory {
    private List<Lizard> lizardList;

    private int count = 4;

    private int playerHp = 10;

    private boolean isCollision = false;

    private List<Lizard> lizardBounds;

    private int playerScore = 0;

    private Camera camera;

    private Player player;

    public LizardFactory(Camera camera, Player player) {
        lizardList = new ArrayList<>();
        this.camera = camera;
        this.player = player;
    }

    private Water water;

    private boolean waterIsCreated = false;

    public void generateLizard() {
        for (int i = 0; i < count; i++) {
            lizardList.add(new Lizard((float) (Math.random() * 1500), (float) (Math.random() * 900)));
        }
        lizardBounds = lizardList;
    }

    public void drawLizards(Camera camera, Vector2 playerPosition, Rectangle player) {
        lizardBounds = lizardList;
        Iterator<Lizard> it = lizardList.iterator();
        while (it.hasNext()) {
            Lizard l = it.next();
            l.checkHp();
            l.draw(camera);
            l.update();
            for (Lizard l1 :
                    lizardBounds) {
                if ((l.getBounds().overlaps(l1.getBounds()) && !l1.equals(l))) {
                    isCollision = true;
                }else{
                    isCollision=false;
                }
            }
            l.move(playerPosition, player,isCollision);
            l.attack(player);
            if (l.isAttack()) {
                l.setAttackTimer(l.getAttackTimer() - Gdx.graphics.getDeltaTime());
                if (l.getAttackTimer() <= 0) {
                    playerHp--;
                    l.setAttackTimer(0.5f);
                }
            }
            if (l.getHP() <= 0 && l.isRemove()) {
                playerScore += 10;
                it.remove();
                if (!waterIsCreated){
                    if ((Math.random()*10) <= 2) {
                        waterIsCreated = true;
                        water = new Water(l.getPosition().x, l.getPosition().y);
                        water.render(camera);
                    }
                }
            }
            if (water != null){
                water.render(camera);
            }
        }
    }

    public void regenHp(){
        if (player.getBound().overlaps(water.getBounds())){
            playerHp = 10;
            water = null;
            waterIsCreated = false;
        }
    }

    public void regenerateLizard() {
        if (lizardList.isEmpty()){
            count+=4;
            generateLizard();
        }
    }

    public List<Lizard> getLizardList() {
        return lizardList;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public boolean isWaterIsCreated() {
        return waterIsCreated;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
