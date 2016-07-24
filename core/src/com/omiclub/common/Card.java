package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by janith on 7/12/16.
 */
public class Card implements Comparable<Card>{

    private int suit;
    private int value;
    private Image card;

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

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return card;
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

    @Override
    public int compareTo(Card card) {
        return this.value - card.getValue();
    }
}
