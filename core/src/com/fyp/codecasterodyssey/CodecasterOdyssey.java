package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fyp.codecasterodyssey.events.Scene;
import com.fyp.codecasterodyssey.screens.*;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.KnownFonts;
import com.github.tommyettinger.textra.TypingConfig;
import com.github.tommyettinger.textra.Font.FontFamily;

public class CodecasterOdyssey extends Game {

	// screens
	private MenuScreen menuScreen;
	private NewGameScreen newGameScreen;
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
	private ArrayList<User> allUsers;
	private ArrayList<LearningPath> allPaths;
	private ArrayList<Spell> allSpells;
	private ArrayList<Scene> allScenes;

	private Skin skin;
	private Font gameFont, spellFont, codeFont;
	private FontFamily fontFamily;
	private User currentUser;	

	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/skin.json"));
		skin.getFont("codeFont").getData().setScale(0.25f);

		gameFont = new Font("RaccoonSerif-Medium.fnt").scale(0.75f, 0.75f);
		spellFont = KnownFonts.getGoNotoUniversal().scaleTo(30, 15).setBoldStrength(0.001f);
		codeFont = KnownFonts.getCascadiaMono().scaleTo(5, 10);
		fontFamily = new FontFamily(new Font[]{gameFont, spellFont, codeFont});

		allUsers = FileUtility.getAllUsers();
		allPaths = FileUtility.getAllPaths();
		allScenes = FileUtility.getAllScenes();

		allSpells = new ArrayList<>();
		for(LearningPath path : allPaths) {
			allSpells.addAll(path.getSpells());
		}

		changeScreen(Constants.DEBUG);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		disposeScreens();
	}

	public Skin getSkin() {
		return this.skin;
	}

	public FontFamily getFontFamily() {
		return fontFamily;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public ArrayList<Scene> getAllScenes() {
		return allScenes;
	}

	public ArrayList<LearningPath> getAllPaths() {
		return allPaths;
	}

	public ArrayList<Spell> getAllSpells() {
		return allSpells;
	}

	public void setCurrentUser(User user) {
		TypingConfig.GLOBAL_VARS.put("USERNAME", user.getUsername());
		currentUser = user;
	}

	// FIXME use ScreenManager.java? but it seems slightly troublesome, private screens, disposal, changeScreen(), game.getScreenManager();
	// changing screens 
	public void changeScreen(int screen) {
		switch (screen) {
			case Constants.MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				setScreen(menuScreen);
				currentUser = null; // TODO ensure if this is enough - is there 'remote' currentUser saving somewhere in screens?
				TypingConfig.GLOBAL_VARS.remove("USERNAME");
				break;
		
			case Constants.NEWGAME:
				if (newGameScreen == null) newGameScreen = new NewGameScreen(this);
				setScreen(newGameScreen);
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

			case Constants.DEBUG:
				if(debugScreen == null) debugScreen = new DebugScreen(this);
				setScreen(debugScreen);
				break;

			default:
				throw new IllegalArgumentException("Invalid Screen");
		}
	}

	private void disposeScreens() {
		if(menuScreen != null) menuScreen.dispose();
		if (newGameScreen != null) newGameScreen.dispose();
		if (loadGameScreen != null) loadGameScreen.dispose();
		if (homeScreen != null) homeScreen.dispose();
		if (learningPathScreen != null) learningPathScreen.dispose();
		if (spellProgressScreen != null) spellProgressScreen.dispose();
		if (questProgressScreen != null) questProgressScreen.dispose();
		if (leaderboardScreen != null) leaderboardScreen.dispose();
		if (gameScreen != null) gameScreen.dispose();
		if (spellScreen != null) spellScreen.dispose();
		if (questScreen != null) questScreen.dispose();
		if (debugScreen != null) debugScreen.dispose();
		skin.dispose();
	}
}
