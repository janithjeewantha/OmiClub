package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;

import java.util.Map;

/**
 * Created by janith on 7/10/16.
 */
public class MainMenu implements Screen {

    private Map<String, Sprite> loadingScreenBackground;
    private Map<String, ImageButton> buttons;
    private SpriteBatch spriteBatch;
    private Sprite background;
    private Sprite logo;
    private Stage stage;

    @Override
    public void show() {
        loadingScreenBackground = GraphicsLoader.getLoadingScreenBackground();
        buttons = GraphicsLoader.getButtons();
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        stage.clear();
        setupBackground();
        setupButtons();
        Gdx.input.setInputProcessor(stage);
    }

    private void setupBackground() {
        background = loadingScreenBackground.get("background");
        logo = loadingScreenBackground.get("logo");
        background.setBounds(
                0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        logo.setSize(DimensionHandler.getScreenWidth()/2, DimensionHandler.getScreenWidth()/2);
        float logoCenterX = DimensionHandler.getScreenWidth()*0.3f;
        float logoCenterY = DimensionHandler.getScreenHeight()*0.6f;
        logo.setCenter(logoCenterX, logoCenterY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        spriteBatch.end();

        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            //
        }
    }

    private void setupButtons() {
        buttons = GraphicsLoader.getButtons();
        ImageButton hostButton;
        ImageButton joinButton;
        ImageButton exitButton;
        ImageButton aboutButton;
        ImageButton settButton;

        //Host Button
        float hostBtnHeight = DimensionHandler.getScreenWidth()*0.06f;
        float hostBtnWidth = hostBtnHeight*20f/3;
        float hostBtnX = DimensionHandler.getScreenWidth()*0.75f;
        float hostBtnY = DimensionHandler.getScreenHeight()*0.5f;
        hostButton = buttons.get("hostBtn");
        hostButton.setSize(hostBtnWidth, hostBtnHeight);
        hostButton.setPosition(hostBtnX-(hostBtnWidth/2), hostBtnY-(hostBtnHeight/2));
        hostButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ServerScreen serverScreen = (ServerScreen) ScreenHandler.getServerScreen();
                GameData.getGameInstance().setScreen(serverScreen);
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //Join Button
        float joinBtnHeight = DimensionHandler.getScreenWidth()*0.06f;
        float joinBtnWidth = joinBtnHeight*20f/3;
        float joinBtnX = DimensionHandler.getScreenWidth()*0.75f;
        float joinBtnY = DimensionHandler.getScreenHeight()*0.35f;
        joinButton = buttons.get("joinBtn");
        joinButton.setSize(joinBtnWidth, joinBtnHeight);
        joinButton.setPosition(joinBtnX-(joinBtnWidth/2), joinBtnY-(joinBtnHeight/2));
        joinButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Join Touch Up");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //Exit Button
        float exitBtnHeight = DimensionHandler.getScreenWidth()*0.06f;
        float exitBtnWidth = exitBtnHeight*20f/6;
        float exitBtnX = DimensionHandler.getScreenWidth()*0.75f;
        float exitBtnY = DimensionHandler.getScreenHeight()*0.20f;
        exitButton = buttons.get("exitBtn");
        exitButton.setSize(exitBtnWidth, exitBtnHeight);
        exitButton.setPosition(exitBtnX-(exitBtnWidth/2), exitBtnY-(exitBtnHeight/2));
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Exit Touch Up");
                Gdx.app.exit();
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //About Button
        float aboutBtnHeight = DimensionHandler.getScreenWidth()*0.05f;
        float aboutBtnWidth = aboutBtnHeight*25f/7;
        float aboutBtnX = DimensionHandler.getScreenWidth()*0.18f;
        float aboutBtnY = DimensionHandler.getScreenHeight()*0.20f;
        aboutButton = buttons.get("aboutBtn");
        aboutButton.setSize(aboutBtnWidth, aboutBtnHeight);
        aboutButton.setPosition(aboutBtnX-(aboutBtnWidth/2), aboutBtnY-(aboutBtnHeight/2));
        aboutButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("About Touch Up");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //Settings Button
        float settBtnHeight = DimensionHandler.getScreenWidth()*0.05f;
        float settBtnWidth = settBtnHeight*25f/7;
        float settBtnX = DimensionHandler.getScreenWidth()*0.42f;
        float settBtnY = DimensionHandler.getScreenHeight()*0.20f;
        settButton = buttons.get("settingsBtn");
        settButton.setSize(settBtnWidth, settBtnHeight);
        settButton.setPosition(settBtnX-(settBtnWidth/2), settBtnY-(settBtnHeight/2));
        settButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Settings Touch Up");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(hostButton);
        stage.addActor(joinButton);
        stage.addActor(exitButton);
        stage.addActor(aboutButton);
        stage.addActor(settButton);
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
