package com.omiclub.singleplayer;

import com.omiclub.common.Card;
import com.omiclub.common.GraphicsLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by janith on 7/24/16.
 */
public class CardDistributor {

    public static Hand[] getHands(){
        ArrayList<Card> cards = GraphicsLoader.getCards();
        Collections.shuffle(cards);

        ArrayList<Card>[] cardList = new ArrayList[4];
        for (int i = 0; i < cardList.length; i++) {
            cardList[i] = new ArrayList<Card>(8);
        }

        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = count; j < count+8; j++) {
                cardList[i].add(cards.get(j));
            }
            count+=8;
        }

        Hand[] hands = new Hand[4];

        for (int i = 0; i < hands.length; i++) {
            Collections.sort(cardList[i]);
            hands[i] = new Hand(i, cardList[i]);
        }

        return hands;
    }

    public static Comparator<Card> SorterBySuit = new Comparator<Card>(){

        @Override
        public int compare(Card card, Card t1) {
            return card.getSuit() - t1.getSuit();
        }
    };
}
