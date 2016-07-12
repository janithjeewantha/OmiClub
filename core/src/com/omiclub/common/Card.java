package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by janith on 7/12/16.
 */
public class Card {

    public int suit;
    public int value;
    public Sprite card;

    private Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Card(int suit, int value, Sprite card) {
        this.suit = suit;
        this.value = value;
        this.card = card;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Sprite getCard() {
        return card;
    }

    public void setCard(Sprite card) {
        this.card = card;
    }

    public static Card getDummyCard(int suit, int value){
        return new Card(suit, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return value == card.value;

    }

    @Override
    public int hashCode() {
        int result = suit;
        result = 31 * result + value;
        return result;
    }
}
