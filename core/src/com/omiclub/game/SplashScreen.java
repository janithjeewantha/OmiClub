package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.game.inputprocessors.SplashListener;

import java.util.Map;

/**
 * Created by janith on 6/18/16.
 */
public class SplashScreen implements Screen{

    private SpriteBatch spriteBatch;
    private Sprite background;
    private Sprite logo;
    private GlyphLayout loadingLayout;
    private float textX;
    private float textY;
    private BitmapFont font;
    private boolean loaded = false;
    private long currentTimeMillis;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        Map<String, Sprite> loadingSprites = GraphicsLoader.getLoadingScreenBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo");

        font = FontsHandler.getDefaultBitmapFont((int) DimensionHandler.getScreenHeight()/10);
        loadingLayout = new GlyphLayout(font, "Loading...");
        alignLoadingText();
        currentTimeMillis = System.currentTimeMillis();
    }

    private void alignLoadingText() {
        float halfWith = loadingLayout.width/2;
        float halfHeight = loadingLayout.height/2;
        textX = (DimensionHandler.getScreenWidth()/2) - halfWith;
        textY = (DimensionHandler.getScreenHeight()/4) - halfHeight;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        font.draw(spriteBatch, loadingLayout, textX, textY);
        spriteBatch.end();

        if(!loaded & System.currentTimeMillis() > currentTimeMillis+5000){
            GraphicsLoader.loadCards();
            loadingLayout = new GlyphLayout(font, "Tap to Continue");
            alignLoadingText();
            Gdx.input.setInputProcessor(SplashListener.getInstance());
            loaded = true;
        }
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
        spriteBatch.dispose();
    }
}
