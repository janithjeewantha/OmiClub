package com.omiclub.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by janith on 6/18/16.
 */
public class SplashScreen implements Screen{

    private Game game;

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        NameScreen nameScreen = new NameScreen(game);
        game.setScreen(nameScreen);
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
