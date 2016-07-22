package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by janith on 7/12/16.
 */
public class Card {

    public int suit;
    public int value;
    public Image card;

    private Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Card(int suit, int value, Image card) {
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

    public Image getCard() {
        return card;
    }

    public void setCard(Image card) {
        this.card = card;
    }

    public Card getDummyCard(){
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
