package com.omiclub.network;

import com.omiclub.common.game.Match;

/**
 * Created by janith on 7/22/16.
 */
public class CommonData {
    private static int[] ids;
    private static String[] names;
    private static int leaderID;
    private static boolean playersReceived = false;
    private static boolean cardsReceived = false;
    private static boolean trumpsChosen = false;
    private static int trumps;
    private static Match.Hand hand;
    private static boolean playersAquired;

    public static void setPlayers(int[] ids, String[] names){
        CommonData.ids = ids;
        CommonData.names = names;
    }

    public static String getPlayerName(int id){
        for (int i = 0; i < ids.length; i++) {
            if(ids[i] == id){
                return names[i];
            }
        }
        return "";
    }

    public static int getLeaderID() {
        return leaderID;
    }

    public static void setLeaderID(int leaderID) {
        CommonData.leaderID = leaderID;
    }

    public static boolean isTrumpsChosen() {
        return trumpsChosen;
    }

    public static void setTrumpsChosen(boolean trumpsChosen) {
        CommonData.trumpsChosen = trumpsChosen;
    }

    public static int getTrumps() {
        return trumps;
    }

    public static void setTrumps(int trumps) {
        CommonData.trumps = trumps;
    }

    public static Match.Hand getHand() {
        return hand;
    }

    public static void setHand(Match.Hand hand) {
        CommonData.hand = hand;
    }

    public static void setPlayersAquired(boolean playersAquired) {
        CommonData.playersAquired = playersAquired;
    }

    public static boolean isPlayersAquired() {
        return playersAquired;
    }
}
