package com.omiclub.common.game;

import com.omiclub.common.Card;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;
import com.omiclub.common.players.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by janith on 7/22/16.
 */
public class Game {

    private ArrayList<Card> cards;
    private ArrayList<Card> dummyCards;
    private Map<Integer, Integer> playerOrder = new HashMap<Integer, Integer>(4);
    private int nextLeaderId = 0;
    private Match currentMatch;

    public Game() {
        cards = GraphicsLoader.getCards();
        for (Card c : cards) {
            dummyCards.add(c.getDummyCard());
        }
        int i = 1;
        playerOrder.put(0,0);
        for (Client c : GameData.getPlayers()) {
            if(c.getId() == GameData.getFriendID()){
                playerOrder.put(2, c.getId());
            } else if (i == 1){
                playerOrder.put(1, c.getId());
                i = 3;
            } else if (i == 3){
                playerOrder.put(3, c.getId());
            }
        }
        currentMatch = new Match(this);

        GameData.getGameInstance().setScreen(ScreenHandler.getTrumpSelectScreen());
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Map<Integer, Integer> getPlayerOrder() {
        return playerOrder;
    }

    public ArrayList<Card> getDummyCards() {
        return dummyCards;
    }

    public int getNextLeaderId() {
        return nextLeaderId;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void rotateNextLeaderId(){
        ++nextLeaderId;
        if (nextLeaderId > GameData.MAX_CLIENTS){
            nextLeaderId = 0;
        }
    }
}
