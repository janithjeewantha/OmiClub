package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;

import java.util.Map;

/**
 * Created by janith on 6/18/16.
 */
public class NameScreen implements Screen{

    private String device;
    private String playerName;
    private SpriteBatch spriteBatch;
    private Sprite background;
    private Sprite logo;

    @Override
    public void show() {

        device = GameData.getDevice();
        spriteBatch = new SpriteBatch();
        Map<String, Sprite> loadingSprites = GraphicsLoader.getLoadingScreenBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo");

        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) {
                playerName = text;
                setPlayerName();
                showMenuScreen();
            }

            @Override
            public void canceled() {
                playerName = device;
                setPlayerName();
                showMenuScreen();
            }
        }, "Enter Player Name", device, "");
    }

    private void setPlayerName(){
        Preferences prefs = Gdx.app.getPreferences("prefs");
        prefs.putString("player", playerName);
        prefs.flush();
        GameData.setPlayerName(playerName);
    }

    private void showMenuScreen(){
        MainMenu mainMenu = (MainMenu) ScreenHandler.getMainMenu();
        GameData.getGameInstance().setScreen(mainMenu);

        //this.dispose();
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
        spriteBatch.dispose();
    }
}
