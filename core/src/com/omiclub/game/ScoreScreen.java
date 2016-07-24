package com.omiclub.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.FontsHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.GraphicsLoader;
import com.omiclub.common.ScreenHandler;

import java.util.Map;

/**
 * Created by janith on 7/24/16.
 */
public class ScoreScreen implements Screen {

    private Sprite background;
    private Sprite logo;
    private Sprite tint;
    private GlyphLayout text;
    private float textX;
    private float textY;
    private Batch spriteBatch;
    private BitmapFont font;

    @Override
    public void show() {
        Map<String, Sprite> loadingSprites = GraphicsLoader.getCommonBackground();
        background = loadingSprites.get("background");
        logo = loadingSprites.get("logo_faded");
        tint = GraphicsLoader.getTint();
        font = FontsHandler.getDefaultBitmapFont((int) (DimensionHandler.getScreenHeight() / 10));
        if(GameData.getWinner() == 0){
            text = new GlyphLayout(font, "You Win!");
        }else {
            text = new GlyphLayout(font, "You Loose...");
        }
        alignText();
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(processor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        tint.draw(spriteBatch);
        font.draw(spriteBatch, text, textX, textY);
        spriteBatch.end();
    }

    private void alignText() {
        float halfWidth = text.width/2;
        //float halfHeight = text.height/2;
        textX = (DimensionHandler.getScreenWidth()/2) - halfWidth;
        textY = (DimensionHandler.getScreenHeight()/2);
    }

    private InputProcessor processor = new InputAdapter(){
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            MainMenu mainMenu = (MainMenu) ScreenHandler.getMainMenu();
            GameData.getGameInstance().setScreen(mainMenu);
            return super.touchUp(screenX, screenY, pointer, button);
        }
    };

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
