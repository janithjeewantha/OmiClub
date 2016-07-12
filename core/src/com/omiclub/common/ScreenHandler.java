package com.omiclub.common;

import com.badlogic.gdx.Screen;
import com.omiclub.game.MainMenu;
import com.omiclub.game.NameScreen;
import com.omiclub.game.SplashScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by janith on 7/10/16.
 */
public class ScreenHandler {

    private static Map<String, Screen> screens = new HashMap<String, Screen>(5);

    public static Screen getSplashScreen(){
        if(!screens.containsKey("splash")){
            screens.put("splash", new SplashScreen());
        }
        return screens.get("splash");
    }

    public static Screen getNameScreen(){
        if(!screens.containsKey("name")){
            screens.put("name", new NameScreen());
        }
        return screens.get("name");
    }

    public static Screen getMainMenu(){
        if(!screens.containsKey("mainmenu")){
            screens.put("mainmenu", new MainMenu());
        }
        return screens.get("mainmenu");
    }
}
