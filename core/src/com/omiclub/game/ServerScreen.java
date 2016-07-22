package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.omiclub.network.Server;

import java.util.Map;

/**
 * Created by janith on 7/21/16.
 */
public class ServerScreen implements Screen {

    private Sprite background;
    private Sprite logo;
    private Sprite tint;
    private GlyphLayout text;
    private float textX;
    private float textY;
    private Batch spriteBatch;
    private BitmapFont font;
    private Server server;
    private final long time = System.currentTimeMillis();

    @Override
    public void show() {
        Map<String, Sprite> loadingSprites = GraphicsLoader.getCommonBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo_faded");
        tint = GraphicsLoader.getTint();
        font = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 20));
        text = new GlyphLayout(font, "Waiting for Players...");
        alignText();
        spriteBatch = new SpriteBatch();
    }

    private void alignText() {
        float halfWidth = text.width/2;
        float halfHeight = text.height/2;
        textX = (DimensionHandler.getScreenWidth()/2) - halfWidth;
        textY = (DimensionHandler.getScreenHeight()/2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        tint.draw(spriteBatch);
        font.draw(spriteBatch, text, textX, textY);
        spriteBatch.end();

        if(!GameData.isHost()){
            server = Server.getServer(GameData.getPlayerName());
            server.startServer();
            GameData.setIsHost(true);
        }

        if (GameData.isPlayersReady() & System.currentTimeMillis() > time+3000){
            GameData.getGameInstance().setScreen(ScreenHandler.getPlayerSelector());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            if (server != null){
                server.stopServer();
                GameData.setIsHost(false);
            }
            if (GameData.getPlayers() != null){
                GameData.getPlayers().clear();
            }
            GameData.setPlayersReady(false);
            GameData.getGameInstance().setScreen((MainMenu) ScreenHandler.getMainMenu());
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
