package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fyp.codecasterodyssey.screens.*;

public class CodecasterOdyssey extends Game {

	// screens
	private MenuScreen menuScreen;
	private StartGameScreen startGameScreen;
	private LoadGameScreen loadGameScreen;
	private HomeScreen homeScreen;
	private LearningPathScreen learningPathScreen;
	private SpellProgressScreen spellProgressScreen;
	private QuestProgressScreen questProgressScreen;
	private LeaderboardScreen leaderboardScreen;
	private GameScreen gameScreen;
	private SpellScreen spellScreen;
	private QuestScreen questScreen;
	private DebugScreen debugScreen;
	
	// data
	private ArrayList<LearningPath> allPaths;

	private Skin skin;
	private User currentUser;	

	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/skin.json"));

		allPaths = FileUtility.loadPaths();

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

	public User getCurrentUser() {
		return currentUser;
	}

	public ArrayList<LearningPath> getAllPaths() {
		return allPaths;
	}

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
				
			case Constants.LEARNING_PATH:
				if (learningPathScreen == null)
					learningPathScreen = new LearningPathScreen(this);
				setScreen(learningPathScreen);
				break;

			case Constants.SPELL_PROGRESS:
				if (spellProgressScreen == null)
					spellProgressScreen = new SpellProgressScreen(this);
				setScreen(spellProgressScreen);
				break;

			case Constants.QUEST_PROGRESS:
				if (questProgressScreen == null)
					questProgressScreen = new QuestProgressScreen(this);
				setScreen(questProgressScreen);
				break;

			case Constants.LEADERBOARD:
				if (leaderboardScreen == null)
					leaderboardScreen = new LeaderboardScreen(this);
				setScreen(leaderboardScreen);
				break;

			case Constants.GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;

			case Constants.SPELL:
				if (spellScreen == null)
					spellScreen = new SpellScreen(this);
				setScreen(spellScreen);
				break;

			case Constants.QUEST:
				if (questScreen == null)
					questScreen = new QuestScreen(this);
				setScreen(questScreen);
				break;

			case Constants.DEBUG: // FIXME remove debug
				if(debugScreen == null) debugScreen = new DebugScreen(this);
				setScreen(debugScreen);
				break;

			default:
				throw new IllegalArgumentException("Invalid Screen");
		}
	}

	private void disposeScreens() {
		if(menuScreen != null) menuScreen.dispose();
		if (startGameScreen != null) startGameScreen.dispose();
		if (loadGameScreen != null) loadGameScreen.dispose();
		if (homeScreen != null) homeScreen.dispose();
		if (learningPathScreen != null) learningPathScreen.dispose();
		if (spellProgressScreen != null) spellProgressScreen.dispose();
		if (questProgressScreen != null) questProgressScreen.dispose();
		if (leaderboardScreen != null) leaderboardScreen.dispose();
		if (gameScreen != null) gameScreen.dispose();
		if (spellScreen != null) spellScreen.dispose();
		if (questScreen != null) questScreen.dispose();
		if (debugScreen != null) debugScreen.dispose(); // FIXME remove debug
		skin.dispose();
	}
}
