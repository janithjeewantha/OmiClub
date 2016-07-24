package com.omiclub.singleplayer;

import com.omiclub.common.Card;

/**
 * Created by janith on 7/24/16.
 */
public class GameLogic {

    private Hand[] hands;
    private Hand myHand;
    private int currentPlayer = 0;
    private boolean isMyTurn = true;
    private int draw = 0;
    private int currentSuit = 0;
    private int currentHighestPlayer = 0;
    private int currentHighestValue = 0;
    private Card[] currentDraw = new Card[4];
    private boolean matchOver = false;
    private int pointsTeamA = 0;
    private int pointsTeamB = 0;

    public GameLogic() {
        hands = CardDistributor.getHands();
        myHand = hands[0];
    }

    public Hand[] getHands() {
        return hands;
    }

    public Hand getMyHand() {
        return myHand;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentDraw(Card[] currentDraw) {
        this.currentDraw = currentDraw;
    }

    public void setCurrentHighestPlayer(int currentHighestPlayer) {
        this.currentHighestPlayer = currentHighestPlayer;
    }

    public void setCurrentHighestValue(int currentHighestValue) {
        this.currentHighestValue = currentHighestValue;
    }

    public int getCurrentSuit() {
        return currentSuit;
    }

    public int getCurrentHighestPlayer() {
        return currentHighestPlayer;
    }

    public int getCurrentHighestValue() {
        return currentHighestValue;
    }

    public int getDraw() {
        return draw;
    }

    public boolean isMatchOver() {
        return matchOver;
    }

    public void setMatchOver(boolean matchOver) {
        this.matchOver = matchOver;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public Card[] getCurrentDraw() {
        return currentDraw;
    }

    public int getPointsTeamA() {
        return pointsTeamA;
    }

    public void increaseAby1() {
        this.pointsTeamA += 1;
    }

    public void increaseAby2() {
        this.pointsTeamA += 2;
    }

    public int getPointsTeamB() {
        return pointsTeamB;
    }

    public void increaseBby1() {
        this.pointsTeamB += 1;
    }

    public void increaseBby2() {
        this.pointsTeamB += 2;
    }

    public void cardDrawn(Card card) {
        if (draw == 3){
            currentDraw[draw] = card;
            if (currentSuit == card.getSuit() && card.getValue() > currentHighestPlayer){
                currentHighestPlayer = currentPlayer;
                currentHighestValue = card.getValue();
            }
            if (currentHighestPlayer == 0 || currentHighestPlayer == 2){
                increaseAby2();
            }else {
                increaseBby2();
            }
            matchOver = true;
            return;
        }

        if (draw == 0){
            currentSuit = card.getSuit();
            currentHighestValue = card.getValue();
            currentHighestPlayer = currentPlayer;
            currentDraw[draw] = card;
            draw++;
            if(++currentPlayer > 3){
                currentPlayer = 0;
                isMyTurn = true;
            }
        }else if (draw < 3){
            if (currentSuit == card.getSuit() && card.getValue() > currentHighestPlayer){
                currentHighestPlayer = currentPlayer;
                currentHighestValue = card.getValue();
            }
            currentDraw[draw] = card;
            draw++;
            if(++currentPlayer > 3){
                currentPlayer = 0;
                isMyTurn = true;
            }
        }
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
