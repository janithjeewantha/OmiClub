package com.omiclub.common;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by janith on 7/9/16.
 */
public class DimensionHandler {

    private static float screenHeight;
    private static float screenWidth;
    private static Vector3 center;
    private static float screenCenterX;
    private static float screenCenterY;

    public static void initGeometry() {
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        screenCenterX = (float) screenWidth/2f;
        screenCenterY = (float) screenHeight/2f;
        center = new Vector3(screenCenterX, screenCenterY, 0f);
    }

    public static float getScreenHeight() {
        return screenHeight;
    }

    public static float getScreenWidth() {
        return screenWidth;
    }

    public static Vector3 getCenter() {
        return center;
    }

    public static float getScreenCenterX() {
        return screenCenterX;
    }

    public static float getScreenCenterY() {
        return screenCenterY;
    }
}
