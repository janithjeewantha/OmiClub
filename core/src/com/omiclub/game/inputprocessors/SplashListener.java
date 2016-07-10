package com.omiclub.game.inputprocessors;

import com.badlogic.gdx.InputProcessor;
import com.omiclub.common.GameData;
import com.omiclub.common.ScreenHandler;
import com.omiclub.game.NameScreen;

/**
 * Created by janith on 7/10/16.
 */
public class SplashListener implements InputProcessor{

    private static SplashListener splashListener;

    private SplashListener(){
    }

    public static SplashListener getInstance(){
        if(splashListener == null){
            splashListener = new SplashListener();
        }
        return splashListener;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        NameScreen nameScreen = (NameScreen) ScreenHandler.getNameScreen();
        GameData.getGameInstance().setScreen(nameScreen);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
