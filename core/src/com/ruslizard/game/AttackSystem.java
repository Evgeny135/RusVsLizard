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
