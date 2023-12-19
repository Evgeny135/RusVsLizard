package com.ruslizard.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ruslizard.game.LizardFactory;
import com.ruslizard.game.Player;

import java.util.Stack;

public class HeartScreen implements Screen {
    private Texture heartTexture;
    private SpriteBatch batch;

    private LizardFactory lizardFactory;

    public HeartScreen(LizardFactory lizardFactory) {
        batch = new SpriteBatch();
        heartTexture = new Texture("Player/heart/Heart.png");
        this.lizardFactory = lizardFactory;
    }

    @Override
    public void show() {
        // Метод render() вызывается каждый кадр для отрисовки экрана
        batch.begin();

        // Отрисовка сердец
        float heartWidth = heartTexture.getWidth();
        float heartHeight = heartTexture.getHeight();
        float heartSpacing = 20; // Расстояние между сердцами

        float x = 20; // Позиция по X
        float y = Gdx.graphics.getHeight() - heartHeight - 20; // Позиция по Y

        for (int i = 0; i < lizardFactory.getPlayerHp(); i++) {
            batch.draw(heartTexture, x, y);
            x += heartWidth + heartSpacing; // Увеличиваем позицию по X для следующего сердца
        }

        batch.end();
    }

    @Override
    public void render(float delta) {
        // Метод render() вызывается каждый кадр для отрисовки экрана
        batch.begin();

        // Отрисовка сердец
        float heartWidth = heartTexture.getWidth();
        float heartHeight = heartTexture.getHeight();
        float heartSpacing = 10; // Расстояние между сердцами

        float x = 20; // Позиция по X
//        float y = Gdx.graphics.getHeight() - heartHeight - 20; // Позиция по Y
        float y = 850; // Позиция по Y

        for (int i = 0; i < lizardFactory.getPlayerHp(); i++) {
            batch.draw(heartTexture, x, y);
            x += heartWidth + heartSpacing; // Увеличиваем позицию по X для следующего сердца
        }


        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float heartWidth = heartTexture.getWidth();
        float heartHeight = heartTexture.getHeight();
        float heartSpacing = 10; // Расстояние между сердцами

        float x = 20; // Позиция по X
        float y = height - heartHeight - 20; // Позиция по Y

        for (int i = 0; i < 30; i++) {
            x += heartWidth + heartSpacing; // Увеличиваем позицию по X для следующего сердца
        }
    }

    @Override
    public void pause() {
        // Метод pause() вызывается при приостановке экрана (например, при сворачивании приложения)
    }

    @Override
    public void resume() {
        // Метод resume() вызывается при возобновлении экрана (например, при разворачивании приложения)
    }

    @Override
    public void hide() {
        // Метод hide() вызывается при скрытии экрана
    }

    @Override
    public void dispose() {
        // Метод dispose() вызывается при освобождении ресурсов экрана
        batch.dispose();
        heartTexture.dispose();
    }
}
