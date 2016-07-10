package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.omiclub.common.DimensionHandler;
import com.omiclub.game.inputprocessors.SplashListener;

/**
 * Created by janith on 6/18/16.
 */
public class SplashScreen implements Screen{

    private TextureAtlas splashImage;
    private SpriteBatch spriteBatch;
    private Sprite background;
    private Sprite logo;

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        splashImage = new TextureAtlas("essentials/essentials.pack");

        background = splashImage.createSprite("loading-background");
        background.setBounds(0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());

        logo = splashImage.createSprite("Icon");
        setBoundsOfLogo(logo);

        Gdx.input.setInputProcessor(SplashListener.getInstance());

    }

    private void setBoundsOfLogo(Sprite logo) {
        logo.setSize((float) DimensionHandler.getScreenWidth()/2, (float) DimensionHandler.getScreenWidth()/2);
        float logoCenterX = DimensionHandler.getScreenCenterX();
        float logoCenterY = (float) DimensionHandler.getScreenHeight()/5f*3f;
        logo.setCenter(logoCenterX, logoCenterY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        spriteBatch.end();
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
