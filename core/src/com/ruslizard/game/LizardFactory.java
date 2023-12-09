package com.ruslizard.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LizardFactory {
    private List<Lizard> lizardList;

    public LizardFactory() {
        lizardList = new ArrayList<>();
    }

    public void generateLizard() {
        Lizard lizard = new Lizard();
        lizard.setPosition((float) (Math.random() * 1500), (float) (Math.random() * 900));
        Lizard lizard1 = new Lizard();
        lizard1.setPosition((float) (Math.random() * 1500), (float) (Math.random() * 900));
        Lizard lizard2 = new Lizard();
        lizard2.setPosition((float) (Math.random() * 1500), (float) (Math.random() * 900));

        lizardList.add(lizard);
        lizardList.add(lizard1);
        lizardList.add(lizard2);
    }

    public void drawLizards(Camera camera, Vector2 playerPosition, Rectangle player) {
        Iterator<Lizard> it = lizardList.iterator();
        while (it.hasNext()) {
            Lizard l = it.next();
            l.draw(camera);
            l.update();
            l.checkCollisionPlayer(player);
            l.checkHp();
            l.move(playerPosition);
            if (l.getHP() <= 0 && l.isDeathFinished()) {
                lizardList.remove(l);
            }
        }
    }

    public void regenerateLizard() {
        if (lizardList.isEmpty()) generateLizard();
    }

    public List<Lizard> getLizardList() {
        return lizardList;
    }
}
