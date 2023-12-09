package com.ruslizard.game;

import com.badlogic.gdx.math.Rectangle;

public class AttackSystem {
    private Player player;
    private LizardFactory lizardFactory;

    private Rectangle playerRectangle;
    private Rectangle lizardRectangle;

    public AttackSystem(Player player, LizardFactory lizardFactory) {
        this.player = player;
        this.lizardFactory = lizardFactory;

        playerRectangle = player.getBoundsAttack();
    }

//    public void setLizardHP(){
//        lizardFactory.setHP(lizardFactory.getHP()-2);
//        try {
//            Thread.sleep(3);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void attack(){
        for (Lizard l :
                lizardFactory.getLizardList()) {
            if (player.isAttack()) {
                if (playerRectangle.overlaps(l.getBounds())) {
                    l.setHp(0);
                }
            }
        }
    }
}
