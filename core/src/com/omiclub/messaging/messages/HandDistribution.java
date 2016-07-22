package com.omiclub.messaging.messages;

/**
 * Created by janith on 7/22/16.
 */
public class HandDistribution implements Message {

    private int trumpSelector;
    private int[] suit = new int[8];
    private int[] value = new int[8];

    public HandDistribution(int trumpSelector, int[] suit, int[] value) {
        this.trumpSelector = trumpSelector;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public int getMessageCode() {
        return MessageCodes.HAND_DISTRIBUTION;
    }

    public int getTrumpSelector() {
        return trumpSelector;
    }

    public int[] getSuit() {
        return suit;
    }

    public int[] getValue() {
        return value;
    }
}
