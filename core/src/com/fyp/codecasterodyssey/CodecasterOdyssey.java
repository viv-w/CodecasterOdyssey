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
	private HomeScreen homeScreen;
	private GameScreen gameScreen;
	private DebugScreen debugScreen;
	
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
	
	@Override
	public void dispose () {
		disposeScreens();
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
	public void setCurrentUser(User user) {
		currentUser = user;
	}

	// FIXME use ScreenManager.java? but it seems slightly troublesome, private screens, disposal, changeScreen(), game.getScreenManager();
	// changing screens 
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

			case Constants.HOME:
				if(homeScreen == null) homeScreen = new HomeScreen(this);
				setScreen(homeScreen);
				break;
				
			case Constants.GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;

			case Constants.DEBUG:
				if(debugScreen == null) debugScreen = new DebugScreen(this);
				setScreen(debugScreen);
				break;

			default:
				throw new IllegalArgumentException("Invalid Screen");
		}
	}

	// FIXME don't forget to dispose the remaining screens
	private void disposeScreens() {
		if(menuScreen != null) menuScreen.dispose();
		if (startGameScreen != null) startGameScreen.dispose();
		if (loadGameScreen != null) loadGameScreen.dispose();
		if (homeScreen != null) homeScreen.dispose();
		if (gameScreen != null) gameScreen.dispose();
		if (debugScreen != null) debugScreen.dispose();
		skin.dispose();
	}
}
