package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by janith on 7/11/16.
 */
public class GraphicsLoader {

    private static TextureAtlas essentialsAtlas;
    private static TextureAtlas cardsAtlas;
    private static TextureAtlas buttonAtlas;
    private static Sprite background;
    private static Sprite logo;
    private static Sprite logo_faded;
    private static Sprite tint;
    private static Map<String, Sprite> loadingScreenSprites = new HashMap<String, Sprite>();
    private static Map<String, Sprite> boardSprites = new HashMap<String, Sprite>();
    private static Map<String, ImageButton> buttonMap = new HashMap<String, ImageButton>();
    private static Map<Integer, Image> suitsMap = new HashMap<Integer, Image>();
    private static ArrayList<Card> cards;
    private static Sprite backCard;

    private static void loadEssentialsPack(){
        if(essentialsAtlas == null) {
            essentialsAtlas = new TextureAtlas("essentials/essentials.pack");
            buttonAtlas = new TextureAtlas("essentials/buttons.pack");
        }
    }

    private static void loadCardsPack(){
        if(cardsAtlas == null) {
            cardsAtlas = new TextureAtlas("cards/cards.pack");
        }
    }

    private static void loadSplashImages(){
        loadEssentialsPack();
        if(background == null) {
            background = essentialsAtlas.createSprite("loading-background");
        }
        if(logo == null){
            logo = essentialsAtlas.createSprite("Icon");
        }
        if(logo_faded == null){
            logo_faded = essentialsAtlas.createSprite("Icon-shaded");
        }
    }

    public static Map<String, Sprite> getLoadingScreenBackground(){
        loadSplashImages();
        setLoadingScreen();
        loadingScreenSprites.clear();
        loadingScreenSprites.put("background", background);
        loadingScreenSprites.put("logo", logo);
        return loadingScreenSprites;
    }

    public static Map<String, Sprite> getCommonBackground(){
        loadSplashImages();
        setCommonScreen();
        loadingScreenSprites.clear();
        loadingScreenSprites.put("background", background);
        loadingScreenSprites.put("logo_faded", logo_faded);
        return loadingScreenSprites;
    }

    public static Map<Integer, Image> getSuits(){
        if(!suitsMap.isEmpty()){
            return suitsMap;
        }
        suitsMap.put(Suit.HEARTS, new Image(essentialsAtlas.findRegion("hearts")));
        suitsMap.put(Suit.SPADES, new Image(essentialsAtlas.findRegion("spades")));
        suitsMap.put(Suit.DIAMONDS, new Image(essentialsAtlas.findRegion("diamonds")));
        suitsMap.put(Suit.CLUBS, new Image(essentialsAtlas.findRegion("clubs")));
        return suitsMap;
    }

    private static void setCommonScreen() {
        background.setBounds(0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        logo_faded.setSize(DimensionHandler.getScreenWidth()/2, DimensionHandler.getScreenWidth()/2);
        logo_faded.setCenter(DimensionHandler.getScreenCenterX(), DimensionHandler.getScreenCenterY());
    }

    public static Sprite getTint(){
        tint = essentialsAtlas.createSprite("tint");
        tint.setSize(DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        return tint;
    }

    private static void setLoadingScreen(){
        background.setBounds(0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        logo.setSize(DimensionHandler.getScreenWidth()/2, DimensionHandler.getScreenWidth()/2);
        float logoCenterX = DimensionHandler.getScreenCenterX();
        float logoCenterY = DimensionHandler.getScreenHeight()/5f*3f;
        logo.setCenter(logoCenterX, logoCenterY);
    }

    public static Map<String, Sprite> getBoardBackground() {
        Sprite background = essentialsAtlas.createSprite("green-back");
        Sprite logo_faded = essentialsAtlas.createSprite("Icon-shaded");
        Sprite left_bar = essentialsAtlas.createSprite("left-bar");
        Sprite right_bar = essentialsAtlas.createSprite("right-bar");
        Sprite top_bar = essentialsAtlas.createSprite("top-bar");
        Sprite bottom_bar = essentialsAtlas.createSprite("bottom-bar");

        boardSprites.clear();
        boardSprites.put("background", background);
        boardSprites.put("logo_faded", logo_faded);
        boardSprites.put("left_bar", left_bar);
        boardSprites.put("right_bar", right_bar);
        boardSprites.put("top_bar", top_bar);
        boardSprites.put("bottom_bar", bottom_bar);

        alignBoard(boardSprites);
        return boardSprites;
    }

    private static void alignBoard(Map<String, Sprite> boardSprites) {
        boardSprites.get("background").setBounds(
                0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        boardSprites.get("logo_faded").setSize(DimensionHandler.getScreenWidth()/2, DimensionHandler.getScreenWidth()/2);
        boardSprites.get("logo_faded").setCenter(DimensionHandler.getScreenCenterX(), DimensionHandler.getScreenCenterY());

        float barThickness = DimensionHandler.getScreenWidth()/20;
        float barHeight = DimensionHandler.getScreenHeight();
        float barWidth = DimensionHandler.getScreenWidth();

        boardSprites.get("left_bar").setSize(barThickness, barHeight);
        boardSprites.get("right_bar").setSize(barThickness, barHeight);
        boardSprites.get("top_bar").setSize(barWidth, barThickness);
        boardSprites.get("bottom_bar").setSize(barWidth, barThickness);

        boardSprites.get("left_bar").setPosition(0, 0);
        boardSprites.get("right_bar").setPosition((barWidth-barThickness), 0);
        boardSprites.get("top_bar").setPosition(0, (barHeight-barThickness));
        boardSprites.get("bottom_bar").setPosition(0, 0);
    }

    public static void loadCards(){
        loadCardsPack();
        cards = CardsLoader.loadCards(cardsAtlas);
        backCard = cardsAtlas.createSprite("back");
    }

    public static ArrayList<Card> getCards(){
        return cards;
    }

    public static Sprite getBackCard(){
        return backCard;
    }

    public static Map<String, ImageButton> getButtons(){
        if(!buttonMap.isEmpty()){
            return buttonMap;
        }
        buttonMap.put("singleBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("Single_N")),
                new SpriteDrawable(buttonAtlas.createSprite("Single_P"))));
        buttonMap.put("hostBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("Host_N")),
                new SpriteDrawable(buttonAtlas.createSprite("Host_P"))));
        buttonMap.put("joinBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("Join_N")),
                new SpriteDrawable(buttonAtlas.createSprite("Join_P"))));
        buttonMap.put("exitBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("Exit_N")),
                new SpriteDrawable(buttonAtlas.createSprite("Exit_P"))));
        buttonMap.put("aboutBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("About_N")),
                new SpriteDrawable(buttonAtlas.createSprite("About_P"))));
        buttonMap.put("settingsBtn", new ImageButton(
                new SpriteDrawable(buttonAtlas.createSprite("Sett_N")),
                new SpriteDrawable(buttonAtlas.createSprite("Sett_P"))));

        return buttonMap;
    }
}
