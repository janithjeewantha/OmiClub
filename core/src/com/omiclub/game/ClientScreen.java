package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;
import com.omiclub.network.Client;

import java.util.Map;

/**
 * Created by janith on 7/23/16.
 */
public class ClientScreen implements Screen {

    private Sprite background;
    private Sprite logo;
    private BitmapFont labelFont;
    private GlyphLayout selectTextLayout;
    private float textX;
    private float textY;
    private Batch spriteBatch;
    private Client client;
    private boolean clientOn = false;
    private boolean gameLoading = false;
    private long loadStart;

    @Override
    public void show() {
        Map<String, Sprite> loadingSprites = GraphicsLoader.getCommonBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo_faded");

        labelFont = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 18));
        selectTextLayout = new GlyphLayout(labelFont, "Scanning for Servers...");
        textX = DimensionHandler.getScreenCenterX()-(selectTextLayout.width/2);
        textY = DimensionHandler.getScreenCenterY()-(selectTextLayout.height/2);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        labelFont.draw(spriteBatch, selectTextLayout, textX, textY);
        spriteBatch.end();

        if(!clientOn){
            try {
                client = Client.getClient(GameData.getPlayerName());
                client.discoverHost();
                client.connectToHost();
            }catch (Exception e){
                System.out.println("Host discovery Failed");
                e.printStackTrace();
                GameData.getGameInstance().setScreen((MainMenu) ScreenHandler.getMainMenu());
            }
            clientOn = true;
        }

        if (!gameLoading && clientOn && client.isConnected()){
            selectTextLayout = new GlyphLayout(labelFont, "Server Found. Loading Game...");
            textX = DimensionHandler.getScreenCenterX()-(selectTextLayout.width/2);
            textY = DimensionHandler.getScreenCenterY()-(selectTextLayout.height/2);
            gameLoading = true;
            loadStart = System.currentTimeMillis();
        }

        if (gameLoading && System.currentTimeMillis() > loadStart + 3000){
//            if()
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

    }
}
