package com.omiclub.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.omiclub.common.DimensionHandler;
import com.omiclub.common.GameData;
import com.omiclub.common.ScreenHandler;

public class OmiClub extends Game {

    @Override
	public void create () {

		Preferences prefs = Gdx.app.getPreferences("prefs");

		DimensionHandler.initGeometry();
		GameData.setGameInstance(this);

		Gdx.input.setCatchBackKey(true);

		SplashScreen splashScreen = (SplashScreen) ScreenHandler.getSplashScreen();
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
