package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.omiclub.common.GameData;

/**
 * Created by janith on 6/18/16.
 */
public class NameScreen implements Screen{

    private String device;
    private String playerName;

    @Override
    public void show() {

        device = GameData.getDevice();

        Gdx.input.getTextInput(new Input.TextInputListener() {

            @Override
            public void input(String text) {
                playerName = text;
            }

            @Override
            public void canceled() {
                playerName = device;
            }
        }, "Player Name", device, "");

        Preferences prefs = Gdx.app.getPreferences("prefs");
        prefs.putString("player", playerName);
        prefs.flush();
        GameData.setPlayerName(playerName);
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
