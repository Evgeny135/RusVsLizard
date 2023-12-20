package com.ruslizard.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Score {
    private BitmapFont font;
    private int scorePlayer;
    private Vector2 position;
    private SpriteBatch batch;

    public Score(float x, float y) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.1f);
        scorePlayer = 0;
        position = new Vector2(x, y);
    }

    public void update(int sc) {
        scorePlayer = sc;
    }

    public void draw(SpriteBatch batch) {
        font.setColor(Color.WHITE);
        font.draw(batch, "Score:  " +scorePlayer, position.x, position.y);
    }

    public void dispose() {
        font.dispose();
    }
}