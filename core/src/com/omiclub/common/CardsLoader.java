package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by janith on 7/12/16.
 */
public class CardsLoader {

    private static Card[][] cards = new Card[4][8];
    private static String[] cardNames = new String[]{
            "7_of_hearts.png",
            "8_of_hearts.png",
            "9_of_hearts.png",
            "10_of_hearts.png",
            "jack_of_hearts.png",
            "queen_of_hearts.png",
            "king_of_hearts.png",
            "ace_of_hearts.png",

            "7_of_spades.png",
            "8_of_spades.png",
            "9_of_spades.png",
            "10_of_spades.png",
            "jack_of_spades.png",
            "queen_of_spades.png",
            "king_of_spades.png",
            "ace_of_spades.png",

            "7_of_diamonds.png",
            "8_of_diamonds.png",
            "9_of_diamonds.png",
            "10_of_diamonds.png",
            "jack_of_diamonds.png",
            "queen_of_diamonds.png",
            "king_of_diamonds.png",
            "ace_of_diamonds.png",

            "7_of_clubs.png",
            "8_of_clubs.png",
            "9_of_clubs.png",
            "10_of_clubs.png",
            "jack_of_clubs.png",
            "queen_of_clubs.png",
            "king_of_clubs.png",
            "ace_of_clubs.png"};


    public static Card[][] loadCards(TextureAtlas cardsAtlas) {

        int nameIndex = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int i = 0; i < 8; i++) {
                Sprite sprite = cardsAtlas.createSprite(cardNames[nameIndex++]);
                cards[suit][i] = new Card(suit, 7+i, sprite);
            }
        }

        return cards;
    }
}
