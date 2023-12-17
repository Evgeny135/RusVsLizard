package com.ruslizard.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenDeath implements Screen {

    private Texture heartTexture = new Texture("death.jpg");;
    private SpriteBatch batch;

    public ScreenDeath() {
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        batch.begin();
        batch.draw(heartTexture,0,0,1600,900);
        batch.end();

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
