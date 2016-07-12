package com.omiclub.common;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by janith on 7/11/16.
 */
public class GraphicsLoader {

    private static TextureAtlas essentialsAtlas;
    private static TextureAtlas cardsAtlas;
    private static Sprite background;
    private static Sprite logo;
    private static Map<String, Sprite> loadingScreenSprites = new HashMap<String, Sprite>();
    private static Card[][] cards;
    private static Sprite backCard;

    private static void loadEssentialsPack(){
        if(essentialsAtlas == null) {
            essentialsAtlas = new TextureAtlas("essentials/essentials.pack");
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
    }

    public static Map<String, Sprite> getLoadingScreenBackground(){
        loadSplashImages();
        setLoadingScreen();
        loadingScreenSprites.put("background", background);
        loadingScreenSprites.put("logo", logo);
        return loadingScreenSprites;
    }

    private static void setLoadingScreen(){
        background.setBounds(0f, 0f, DimensionHandler.getScreenWidth(), DimensionHandler.getScreenHeight());
        logo.setSize((float) DimensionHandler.getScreenWidth()/2, (float) DimensionHandler.getScreenWidth()/2);
        float logoCenterX = DimensionHandler.getScreenCenterX();
        float logoCenterY = (float) DimensionHandler.getScreenHeight()/5f*3f;
        logo.setCenter(logoCenterX, logoCenterY);
    }

    public static void loadCards(){
        loadCardsPack();
        cards = CardsLoader.loadCards(cardsAtlas);
        backCard = cardsAtlas.createSprite("back");
    }

    public static Card[][] getCards(){
        return cards;
    }

    public static Sprite getBackCard(){
        return backCard;
    }

}
