package com.omiclub.singleplayer;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.omiclub.common.Card;
import com.omiclub.common.CardsLoader;
import com.omiclub.common.DimensionHandler;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by janith on 7/24/16.
 */
public class CardAnimator {

    private GameLogic logic;
    private Hand[] hands;
    private float cardWidth = CardsLoader.getCardWidth();
    private float cardHeight = CardsLoader.getCardHeight();
    private float cardCenterY = DimensionHandler.getScreenCenterY()- cardHeight /2;
    private float cardCenterX = DimensionHandler.getScreenCenterX()-cardWidth/2;
    private float scrWidth = DimensionHandler.getScreenWidth();
    private float scrHeigth = DimensionHandler.getScreenHeight();
    private float[] deckXPositions = new float[]{
            scrWidth*0.225f, scrWidth*0.375f, scrWidth*0.525f, scrWidth*0.675f};
    private float[] removeXPositions = new float[]{
            cardCenterX, scrWidth+cardWidth, cardCenterX, -(cardWidth)};
    private float[] removeYPositions = new float[]{
            -(cardHeight), cardCenterY, scrHeigth+cardHeight, cardCenterY};

    public CardAnimator(GameLogic logic) {
        this.logic = logic;
    }

    public void positionCards() {
        hands = logic.getHands();

        positionPlayerCards();
        positionP1Cards();
        positionP2Cards();
        positionP3Cards();
    }

    public Action getDrawAnimation(){
        MoveToAction action = new MoveToAction();
        action.setPosition(deckXPositions[logic.getDraw()], cardCenterY);
        action.setDuration(1);
        action.setInterpolation(Interpolation.exp5);
        return action;
    }

    public Action getRemoveAnimation(){
        MoveToAction action = new MoveToAction();
        action.setPosition(removeXPositions[logic.getCurrentHighestPlayer()],
                removeYPositions[logic.getCurrentHighestPlayer()]);
        action.setDuration(1);
        action.setInterpolation(Interpolation.exp5);
        return action;
    }

    private void positionPlayerCards(){
        ArrayList<Card> cards = hands[0].getCards();
        Collections.sort(cards, CardDistributor.SorterBySuit);
        float startPoint = (DimensionHandler.getScreenWidth()/4);
        float spacing = ((DimensionHandler.getScreenWidth()/2)-cardWidth)/7;
        float hiddenHeight = cardHeight /3;

        for (int i = 0; i < 8; i++) {
            cards.get(i).getImage().setPosition((startPoint+i*spacing), (-1*hiddenHeight));
        }
    }

    private void positionP1Cards() {
        ArrayList<Card> cards = hands[1].getCards();
        Collections.sort(cards, CardDistributor.SorterBySuit);
        float startPointX = DimensionHandler.getScreenWidth();
        float startPointY = (DimensionHandler.getScreenCenterY()- cardHeight /2);

        for (int i = 0; i < 8; i++) {
            cards.get(i).getImage().setPosition(startPointX, startPointY);
        }
    }

    private void positionP2Cards() {
        ArrayList<Card> cards = hands[2].getCards();
        Collections.sort(cards, CardDistributor.SorterBySuit);
        float startPointX = DimensionHandler.getScreenCenterX()-0.5f*(cardWidth);
        float startPointY = DimensionHandler.getScreenHeight();

        for (int i = 0; i < 8; i++) {
            cards.get(i).getImage().setPosition(startPointX, startPointY);
        }
    }

    private void positionP3Cards() {
        ArrayList<Card> cards = hands[3].getCards();
        Collections.sort(cards, CardDistributor.SorterBySuit);
        float startPointX = -1f*(cardWidth);
        float startPointY = (DimensionHandler.getScreenCenterY()- cardHeight /2);

        for (int i = 0; i < 8; i++) {
            cards.get(i).getImage().setPosition(startPointX, startPointY);
        }
    }

}
