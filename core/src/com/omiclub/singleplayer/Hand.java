package com.omiclub.singleplayer;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.omiclub.common.Card;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by janith on 7/24/16.
 */
public class Hand {

    private int playerID;
    private ArrayList<Card> cards;

    public Hand(int playerID, ArrayList<Card> cards) {
        this.playerID = playerID;
        this.cards = cards;
    }

    private ArrayList<Card> getAllOfSuit(int suit){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : this.cards) {
            if (card.getSuit() == suit){
                cards.add(card);
            }
        }
        Collections.sort(cards);
        return cards;
    }

    private Card getSmallest(){
        Collections.sort(cards);
        Card card = cards.get(0);
        return card;
    }

    public Card getLargerOneTo(int suit, int value){
        ArrayList<Card> allOfSuit = getAllOfSuit(suit);
        Collections.reverse(allOfSuit);
        Card chosenOne = new Card(-1, -1, null);
        boolean chosen = false;
        for (Card card : allOfSuit) {
            if (card.getValue() > value){
                chosenOne = card;
                chosen = true;
                break;
            }
        }
        if (!chosen){
            chosenOne = getSmallest();
        }

        if(chosenOne.getValue() == -1){
            return null;
        }

        int index = cards.indexOf(chosenOne);
        return cards.remove(index);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCardOfImage(Image target) {
        for (Card card : cards) {
            if (card.getImage() == target){
                return card;
            }
        }
        return null;
    }
}
