package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.omiclub.common.Card;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;
import com.omiclub.common.game.Game;
import com.omiclub.singleplayer.CardAnimator;
import com.omiclub.singleplayer.GameLogic;

import java.util.Map;

/**
 * Created by janith on 7/22/16.
 */
public class GameScreen extends InputListener implements Screen {

    private GameLogic gameLogic = new GameLogic();
    private CardAnimator animator = new CardAnimator(gameLogic);
    private SpriteBatch spriteBatch;
    private Sprite background;
    private Sprite logo_faded;
    private Sprite left_bar;
    private Sprite right_bar;
    private Sprite top_bar;
    private Sprite bottom_bar;

    private BitmapFont labelFont;
    private Label.LabelStyle style;
    private Label p1Label;
    private Label p2Label;
    private Label p3Label;
    private Stage stage;
    private long lastDrawTime;
    private long gameOverTime;
    private boolean gameOver = false;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        Map<String, Sprite> boardBackground = GraphicsLoader.getBoardBackground();
        background = boardBackground.get("background");
        logo_faded = boardBackground.get("logo_faded");
        left_bar = boardBackground.get("left_bar");
        right_bar = boardBackground.get("right_bar");
        top_bar = boardBackground.get("top_bar");
        bottom_bar = boardBackground.get("bottom_bar");

        setLables();
        animator.positionCards();
//        float scrByFour = DimensionHandler.getScreenWidth()/4;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 8; j++) {
//                gameLogic.getHands()[i].getCards().get(j).getImage().setPosition((scrByFour*i)+(j*10), -20);
//            }
//        }
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        stage.clear();
        stage.addActor(p1Label);
        stage.addActor(p2Label);
        stage.addActor(p3Label);

        for (int i = 0; i < 8; i++) {
            stage.addActor(gameLogic.getHands()[0].getCards().get(i).getImage());
        }
        for (int i = 0; i < 8; i++) {
            stage.addActor(gameLogic.getHands()[1].getCards().get(i).getImage());
        }
        for (int i = 0; i < 8; i++) {
            stage.addActor(gameLogic.getHands()[2].getCards().get(i).getImage());
        }
        for (int i = 0; i < 8; i++) {
            stage.addActor(gameLogic.getHands()[3].getCards().get(i).getImage());
        }
//        gameLogic.getHands()[0].getCards().get(0).getImage().addAction(animator.getDrawAnimation());
//        gameLogic.setCurrentPlayer(1);
//        gameLogic.getHands()[1].getCards().get(0).getImage().addAction(animator.getDrawAnimation());
//        gameLogic.setCurrentPlayer(2);
//        gameLogic.getHands()[2].getCards().get(0).getImage().addAction(animator.getDrawAnimation());
//        gameLogic.setCurrentPlayer(3);
//        gameLogic.getHands()[3].getCards().get(0).getImage().addAction(animator.getDrawAnimation());

        addListeners();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo_faded.draw(spriteBatch);
        left_bar.draw(spriteBatch);
        right_bar.draw(spriteBatch);
        top_bar.draw(spriteBatch);
        bottom_bar.draw(spriteBatch);
        spriteBatch.end();

        stage.draw();

        boolean timeUp = System.currentTimeMillis() > lastDrawTime + 2000;

        if(!gameOver & !gameLogic.isMatchOver() & !gameLogic.isMyTurn() & timeUp){
            Card card = gameLogic.getHands()[gameLogic.getCurrentPlayer()].
                    getLargerOneTo(gameLogic.getCurrentSuit(), gameLogic.getCurrentHighestValue());
            card.getImage().addAction(animator.getDrawAnimation());
            gameLogic.cardDrawn(card);
            lastDrawTime = System.currentTimeMillis();
        }

        if (!gameOver & gameLogic.isMatchOver() & System.currentTimeMillis() > lastDrawTime + 3000){
            Card[] currentDraw = gameLogic.getCurrentDraw();
            for (int i = 0; i < 4; i++) {
                currentDraw[i].getImage().addAction(animator.getRemoveAnimation());
            }
            System.out.println(gameLogic.getPointsTeamA());
            System.out.println(gameLogic.getPointsTeamB());
            gameLogic.setDraw(0);
            gameLogic.setCurrentPlayer(gameLogic.getCurrentHighestPlayer());
            if (gameLogic.getCurrentPlayer() == 0){
                gameLogic.setMyTurn(true);
            }
            gameLogic.setCurrentHighestValue(0);
            gameLogic.setCurrentDraw(new Card[4]);
            gameLogic.setMatchOver(false);
            lastDrawTime = System.currentTimeMillis();
        }

        if(!gameOver & (gameLogic.getPointsTeamA() + gameLogic.getPointsTeamB()) == 16){
            gameOver = true;
            gameOverTime = System.currentTimeMillis();
        }

        if (gameOver && System.currentTimeMillis() >gameOverTime + 4000){
            if (gameLogic.getPointsTeamA()>gameLogic.getPointsTeamB()){
                GameData.setWinner(0);
            }else {
                GameData.setWinner(1);
            }
            GameData.getGameInstance().setScreen(ScreenHandler.getScoreScreen());
        }
    }

    private void addListeners() {
        for (Card c : gameLogic.getMyHand().getCards()) {
            c.getImage().addListener(this);
        }
    }

    private void setLables() {
        labelFont = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 20));
        style = new Label.LabelStyle(labelFont, Color.WHITE);

        float fullWidth = DimensionHandler.getScreenWidth();
        float halfWidth = DimensionHandler.getScreenCenterX();
        float fullHeight = DimensionHandler.getScreenHeight();
        float halfHeight = DimensionHandler.getScreenCenterY();
        float padding = fullHeight/30;

        p1Label = new Label("Player 1", style);
        p1Label.setPosition(fullWidth-(p1Label.getWidth()+padding), (halfHeight-p1Label.getHeight()/2));
        p2Label = new Label("Player 2", style);
        p2Label.setPosition((halfWidth-p2Label.getWidth()/2), fullHeight-(p2Label.getHeight()+padding));
        p3Label = new Label("Player 3", style);
        p3Label.setPosition(padding, (halfHeight-p3Label.getHeight()/2));
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        gameLogic.setMyTurn(false);
        Card card = gameLogic.getMyHand().getCardOfImage((Image) event.getTarget());
        card.getImage().addAction(animator.getDrawAnimation());
        gameLogic.cardDrawn(card);
        lastDrawTime = System.currentTimeMillis();
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (!gameLogic.isMyTurn()){
            return false;
        }
        return true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
