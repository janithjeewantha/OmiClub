package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;
import com.omiclub.common.Suit;
import com.omiclub.network.CommonData;
import java.util.Map;

/**
 * Created by janith on 7/22/16.
 */
public class TrumpSelectScreen implements Screen {

    private Sprite background;
    private Sprite logo;
    private BitmapFont labelFont;
    private GlyphLayout selectTextLayout;
    private float textX;
    private float textY;
    private Map<Integer, Image> suits;
    private Batch spriteBatch;
    private Stage stage;

    @Override
    public void show() {
        Map<String, Sprite> loadingSprites = GraphicsLoader.getCommonBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo_faded");

        labelFont = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 15));
        selectTextLayout = new GlyphLayout(labelFont, "Select Trumps");
        textX = DimensionHandler.getScreenWidth()/8;
        textY = DimensionHandler.getScreenHeight()/6*5;

        suits = GraphicsLoader.getSuits();
        prepareSuits();
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        stage.clear();
        stage.addActor(suits.get(Suit.HEARTS));
        stage.addActor(suits.get(Suit.SPADES));
        stage.addActor(suits.get(Suit.DIAMONDS));
        stage.addActor(suits.get(Suit.CLUBS));
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

        if(CommonData.isTrumpsChosen()){
            GameData.getGameInstance().setScreen(ScreenHandler.getGameScreen());
        }
    }

    private void prepareSuits() {
        float iconDimension = DimensionHandler.getScreenHeight() / 5;
        for (Image image : suits.values()) {
            image.setSize(iconDimension, iconDimension);
        }

        Image image = suits.get(Suit.HEARTS);
        image.setPosition((DimensionHandler.getScreenWidth()/5)-(iconDimension/2),
                DimensionHandler.getScreenCenterY() - (iconDimension/2));
        image.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(GameData.isHost()){
                    GameData.getCurrentGame().getCurrentMatch().setTrumps(Suit.HEARTS);
                }
                CommonData.setTrumps(Suit.HEARTS);
                CommonData.setTrumpsChosen(true);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        image = suits.get(Suit.SPADES);
        image.setPosition((DimensionHandler.getScreenWidth()/5*2)-(iconDimension/2),
                DimensionHandler.getScreenCenterY() - (iconDimension/2));
        image.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(GameData.isHost()){
                    GameData.getCurrentGame().getCurrentMatch().setTrumps(Suit.SPADES);
                }
                CommonData.setTrumps(Suit.SPADES);
                CommonData.setTrumpsChosen(true);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        image = suits.get(Suit.DIAMONDS);
        image.setPosition((DimensionHandler.getScreenWidth()/5*3)-(iconDimension/2),
                DimensionHandler.getScreenCenterY() - (iconDimension/2));
        image.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(GameData.isHost()){
                    GameData.getCurrentGame().getCurrentMatch().setTrumps(Suit.DIAMONDS);
                }
                CommonData.setTrumps(Suit.DIAMONDS);
                CommonData.setTrumpsChosen(true);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        image = suits.get(Suit.CLUBS);
        image.setPosition((DimensionHandler.getScreenWidth()/5*4)-(iconDimension/2),
                DimensionHandler.getScreenCenterY() - (iconDimension/2));
        image.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(GameData.isHost()){
                    GameData.getCurrentGame().getCurrentMatch().setTrumps(Suit.CLUBS);
                }
                CommonData.setTrumps(Suit.CLUBS);
                CommonData.setTrumpsChosen(true);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
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
