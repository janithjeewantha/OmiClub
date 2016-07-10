package com.omiclub.common;

import com.badlogic.gdx.Game;

/**
 * Created by janith on 7/10/16.
 */
public class GameData {

    private static String device;
    private static Game gameInstance;
    private static String playerName;

    public static String getDevice() {
        return device;
    }

    public static void setDevice(String dev) {
        device = dev;
    }

    public static void setGameInstance(Game gameInstance) {
        GameData.gameInstance = gameInstance;
    }

    public static Game getGameInstance() {
        return gameInstance;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        GameData.playerName = playerName;
    }

}
