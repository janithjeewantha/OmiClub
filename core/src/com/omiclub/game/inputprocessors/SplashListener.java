package com.omiclub.game.inputprocessors;

import com.badlogic.gdx.InputAdapter;
import com.omiclub.common.GameData;
import com.omiclub.common.ScreenHandler;
import com.omiclub.game.NameScreen;

/**
 * Created by janith on 7/10/16.
 */
public class SplashListener extends InputAdapter{

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        NameScreen nameScreen = (NameScreen) ScreenHandler.getNameScreen();
        GameData.getGameInstance().setScreen(nameScreen);
        ScreenHandler.getSplashScreen().dispose();
        return true;
    }

}
