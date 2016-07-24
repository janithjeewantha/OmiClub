package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;
import com.omiclub.common.game.Game;
import com.omiclub.common.players.Client;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by janith on 7/21/16.
 */
public class PlayerSelector implements Screen{

    private Sprite background;
    private Sprite logo;
    private BitmapFont labelFont;
    private BitmapFont playerFont;
    private Label.LabelStyle style;
    private Label p1Label;
    private Label p2Label;
    private Label p3Label;
    private ArrayList<Client> players;
    private GlyphLayout selectTextLayout;
    private float textX;
    private float textY;
    private Batch spriteBatch;
    private Stage stage;
    private boolean friendSelected = false;

    @Override
    public void show() {
        Map<String, Sprite> loadingSprites = GraphicsLoader.getCommonBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo_faded");

        labelFont = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 15));
        selectTextLayout = new GlyphLayout(labelFont, "Select Friendly Player");
        textX = DimensionHandler.getScreenWidth()/8;
        textY = DimensionHandler.getScreenHeight()/6*5;

        playerFont = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 20));
        style = new Label.LabelStyle(playerFont, Color.WHITE);
        setLabels();

        spriteBatch = new SpriteBatch();
        stage = new Stage();
        stage.clear();
        stage.addActor(p1Label);
        stage.addActor(p2Label);
        stage.addActor(p3Label);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        labelFont.draw(spriteBatch, selectTextLayout, textX, textY);
        spriteBatch.end();

        stage.draw();

        if(friendSelected){
            goToTrumpsSelector();
        }
    }

    private void goToTrumpsSelector() {
        GameData.startNewGame();
    }

    private void setLabels() {
        players = GameData.getPlayers();
        final Client client;
        float labelX;
        float labelY;
        if((client = players.get(0)) != null){
            p1Label = new Label(client.getName(), style);
//            p1Label = new Label("Player 1", style);
        labelX = DimensionHandler.getScreenCenterX()-(p1Label.getWidth()/2);
        labelY = DimensionHandler.getScreenHeight()*0.6f;
        p1Label.setPosition(labelX, labelY);
        p1Label.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GameData.setFriendID(client.getId());
                friendSelected = true;
//                GameData.setFriendID(0);
                super.touchUp(event, x, y, pointer, button);
            }
        });
//        }
//        if((client = players.get(1)) != null){
//            p2Label = new Label(client.getName(), style);
            p2Label = new Label("Player 2", style);
            labelX = DimensionHandler.getScreenCenterX()-(p2Label.getWidth()/2);
            labelY = (DimensionHandler.getScreenHeight()*0.5f) -(p2Label.getHeight()/2);
            p2Label.setPosition(labelX, labelY);
            p2Label.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                GameData.setFriendID(client.getId());
                    GameData.setFriendID(1);
                    friendSelected = true;
                    super.touchUp(event, x, y, pointer, button);
                }
            });
//        }
//        if((client = players.get(2)) != null){
//            p3Label = new Label(client.getName(), style);
            p3Label = new Label("Player 3", style);
            labelX = DimensionHandler.getScreenCenterX()-(p3Label.getWidth()/2);
            labelY = (DimensionHandler.getScreenHeight()*0.4f)-(p3Label.getHeight());
            p3Label.setPosition(labelX, labelY);
            p3Label.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                GameData.setFriendID(client.getId());
                    GameData.setFriendID(2);
                    friendSelected = true;
                    super.touchUp(event, x, y, pointer, button);
                }
            });
        }
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
