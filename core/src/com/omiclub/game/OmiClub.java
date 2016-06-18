package com.omiclub.game;

import com.badlogic.gdx.Game;

public class OmiClub extends Game {
	
	@Override
	public void create () {
		SplashScreen splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
