package com.ruslizard.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TimerIcon {
    private BitmapFont font;
    private float timerValue;
    private Vector2 position;
    private SpriteBatch batch;

    public TimerIcon(float x, float y) {
        batch = new SpriteBatch();
        font = new BitmapFont(); // Создаем экземпляр BitmapFont
        timerValue = 10;
        position = new Vector2(x, y);
    }

    public void update(float dashTime) {
        timerValue = dashTime;
        if (timerValue <= 0){
            timerValue = 0;
        }
    }

    public float getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(float timerValue) {
        this.timerValue = timerValue;
    }

    public void draw(SpriteBatch batch) {
        font.setColor(Color.WHITE);
        font.draw(batch, "Dash: " + String.format("%.1f", timerValue), position.x, position.y);
    }

    public void dispose() {
        font.dispose();
    }
}