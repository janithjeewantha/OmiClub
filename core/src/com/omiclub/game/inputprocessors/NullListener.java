package com.omiclub.game.inputprocessors;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by janith on 7/11/16.
 */
public class NullListener implements InputProcessor {

    private static NullListener nullListener;

    private NullListener(){
    }

    public static NullListener getInstance(){
        if(nullListener == null){
            nullListener = new NullListener();
        }
        return nullListener;
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
