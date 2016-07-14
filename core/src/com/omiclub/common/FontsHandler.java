package com.omiclub.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Created by janith on 7/12/16.
 */
public class FontsHandler {

    private static FreeTypeFontGenerator fontGenerator;
    private static FreeTypeFontParameter defaultFontParameter;

    private static void loadFonts(){
        if(fontGenerator == null){
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/comic_serif.ttf"));
        }
    }

    private static FreeTypeFontParameter getDefaultFontParameter(){
        loadFonts();
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        float color = (float) 210/255f;
        parameter.color = new Color(color, color, color, 0.9f);
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 5;
        return parameter;
    }

    public static BitmapFont getDefaultBitmapFont(int size){
        if (defaultFontParameter == null) {
            defaultFontParameter = getDefaultFontParameter();
        }
        defaultFontParameter.size = size;
        return fontGenerator.generateFont(defaultFontParameter);
    }

    public static void disposeAll(){
        fontGenerator.dispose();
    }

}
