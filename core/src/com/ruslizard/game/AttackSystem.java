package com.ruslizard.game;

import com.badlogic.gdx.math.Rectangle;

public class AttackSystem {
    private Player player;
    private Lizard lizard;

    private Rectangle playerRectangle;
    private Rectangle lizardRectangle;

    public AttackSystem(Player player, Lizard lizard) {
        this.player = player;
        this.lizard = lizard;

        playerRectangle = player.getBoundsAttack();
        lizardRectangle = lizard.getBounds();
    }

    public void setLizardHP(){
        lizard.setHP(lizard.getHP()-2);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void attack(){
        if (player.isAttack()) {
            if (playerRectangle.overlaps(lizardRectangle)) {
                setLizardHP();
            }
        }
        if (lizard.getHP()<=0){
            System.out.println("PIDOR");
        }
    }
}
