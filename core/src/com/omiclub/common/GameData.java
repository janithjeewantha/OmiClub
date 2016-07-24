package com.omiclub.common;

import com.badlogic.gdx.Game;
import com.omiclub.common.players.Client;
import java.util.ArrayList;

/**
 * Created by janith on 7/10/16.
 */
public class GameData {

    private static String device;
    private static Game gameInstance;
    public static final int MAX_CLIENTS = 1;
    private static String playerName;
    private static boolean isHost;
    private static ArrayList<Client> players = new ArrayList<Client>(3);
    private static boolean playersReady = false; ///////////SET TO FALSE AFTER TESTING/////////////
    private static int friendID = -1;
    private static com.omiclub.common.game.Game currentGame;
    private static int winner;

    public static int getFriendID() {
        return friendID;
    }

    public static void setFriendID(int friendID) {
        GameData.friendID = friendID;
    }

    public static ArrayList<Client> getPlayers() {
        return players;
    }

    public static boolean isHost() {
        return isHost;
    }

    public static void setIsHost(boolean isHost) {
        GameData.isHost = isHost;
    }

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

    public static void setPlayersReady(boolean playersReady) {
        GameData.playersReady = playersReady;
    }

    public static boolean isPlayersReady() {
        return playersReady;
    }

    public static void startNewGame() {
        currentGame = new com.omiclub.common.game.Game();
    }

    public static com.omiclub.common.game.Game getCurrentGame() {
        return currentGame;
    }

    public static void setWinner(int winner) {
        GameData.winner = winner;
    }

    public static int getWinner() {
        return winner;
    }
}
