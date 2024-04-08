package com.fyp.codecasterodyssey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fyp.codecasterodyssey.screens.*;
import com.fyp.codecasterodyssey.user.User;

public class CodecasterOdyssey extends Game {

	// screens
	private MenuScreen menuScreen;
	private StartGameScreen startGameScreen;
	private LoadGameScreen loadGameScreen;
	private GameScreen gameScreen;
	
	private Skin skin;
	private User currentUser;	

	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/skin.json"));
		changeScreen(Constants.MENU);
	}

	@Override
	public void render () {
		super.render();
	}
	
	// FIXME don't forget to dispose
	@Override
	public void dispose () {
		menuScreen.dispose();
		startGameScreen.dispose();
		loadGameScreen.dispose();
		gameScreen.dispose();
		skin.dispose();
	}

	// changing screens FIXME use ScreenManager.java?
	public void changeScreen(int screen) {
		switch (screen) {
			case Constants.MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				setScreen(menuScreen);
				currentUser = null; // FIXME ensure if this is enough
				break;
		
			case Constants.STARTGAME:
				if (startGameScreen == null) startGameScreen = new StartGameScreen(this);
				setScreen(startGameScreen);
				break;

			case Constants.LOADGAME:
				if (loadGameScreen == null) loadGameScreen = new LoadGameScreen(this);
				setScreen(loadGameScreen);
				break;

			case Constants.GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;
		}
	}

	// get Skin
	public Skin getSkin() {
		return this.skin;
	}

	// get ProfileManager
	public User getCurrentUser() {
		return currentUser;
	}

	// set ProfileManager
	public void setCurrentUser(String username) {
		currentUser = new User(username);
	}
}
