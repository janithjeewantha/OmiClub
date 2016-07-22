package com.omiclub.common.game;

import com.omiclub.common.Card;
import com.omiclub.common.GameData;
import com.omiclub.network.Server;

import java.util.Collections;

/**
 * Created by janith on 7/22/16.
 */
public class Match {
    private Game game;
    private int trumps;
    private Card myCards[] = new Card[8];

    public Match(Game game) {
        this.game = game;
        Collections.shuffle(game.getDummyCards());

        distributeCards();


    }

    public void distributeCards(){
        for (int i = 0; i < 8; i++) {
            myCards[i] = game.getDummyCards().get(i);
        }

        int[] suit = new int[8];
        int[] value = new int[8];
        int playerID;

        for (int i = 8; i < 16; i++) {
            suit[i-8] = game.getDummyCards().get(i).getSuit();
            value[i-8] = game.getDummyCards().get(i).getValue();
        }
        playerID = game.getPlayerOrder().get(1);
        Server.getServerInstance().sendCards(playerID, suit, value, game.getNextLeaderId());

        if(GameData.MAX_CLIENTS < 2){       ///////For Testing Only
            return;
        }
        for (int i = 16; i < 24; i++) {
            suit[i-16] = game.getDummyCards().get(i).getSuit();
            value[i-16] = game.getDummyCards().get(i).getValue();
        }
        playerID = game.getPlayerOrder().get(2);
        Server.getServerInstance().sendCards(playerID, suit, value, game.getNextLeaderId());

        if(GameData.MAX_CLIENTS < 3){       ///////For Testing Only
            return;
        }
        for (int i = 24; i < 32; i++) {
            suit[i-24] = game.getDummyCards().get(i).getSuit();
            value[i-24] = game.getDummyCards().get(i).getValue();
        }
        playerID = game.getPlayerOrder().get(3);
        Server.getServerInstance().sendCards(playerID, suit, value, game.getNextLeaderId());
    }

    public class Hand{
        private int[] suit = new int[8];
        private int[] value = new int[8];

        public int[] getSuit() {
            return suit;
        }

        public int[] getValue() {
            return value;
        }
    }

    public int getTrumps() {
        return trumps;
    }

    public void setTrumps(int trumps) {
        this.trumps = trumps;
    }
}
