package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;

/**
 * Created by janith on 7/12/16.
 */
public class CardsLoader {

    private static float cardWidth = DimensionHandler.getScreenWidth()/10;
    private static float cardHeight = cardWidth*1.452f;
    private static ArrayList<Card> cards = new ArrayList<Card>(32);
    private static String[] cardNames = new String[]{
            "7_of_hearts",
            "8_of_hearts",
            "9_of_hearts",
            "10_of_hearts",
            "jack_of_hearts",
            "queen_of_hearts",
            "king_of_hearts",
            "ace_of_hearts",

            "7_of_spades",
            "8_of_spades",
            "9_of_spades",
            "10_of_spades",
            "jack_of_spades",
            "queen_of_spades",
            "king_of_spades",
            "ace_of_spades",

            "7_of_diamonds",
            "8_of_diamonds",
            "9_of_diamonds",
            "10_of_diamonds",
            "jack_of_diamonds",
            "queen_of_diamonds",
            "king_of_diamonds",
            "ace_of_diamonds",

            "7_of_clubs",
            "8_of_clubs",
            "9_of_clubs",
            "10_of_clubs",
            "jack_of_clubs",
            "queen_of_clubs",
            "king_of_clubs",
            "ace_of_clubs"};


    public static ArrayList<Card> loadCards(TextureAtlas cardsAtlas) {
        int nameIndex = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int i = 0; i < 8; i++) {
                Image image = new Image(cardsAtlas.findRegion(cardNames[nameIndex++]));
                image.setSize(cardWidth, cardHeight);
                cards.add(new Card(suit, 7+i, image));
            }
        }
        return cards;
    }

    public static float getCardWidth() {
        return cardWidth;
    }

    public static float getCardHeight() {
        return cardHeight;
    }
}
