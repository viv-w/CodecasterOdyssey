package com.fyp.codecasterodyssey;

import java.util.ArrayList;
import java.util.Comparator;

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
	private SelectPathScreen selectPathScreen;
	private SpellProgressScreen spellProgressScreen;
	private QuestProgressScreen questProgressScreen;
	private LeaderboardScreen leaderboardScreen;
	private GameScreen gameScreen;
	private SpellScreen spellScreen;
	private QuestScreen questScreen;
	
	// data
	private ArrayList<LearningPath> allPaths;
	private ArrayList<Spell> allSpells;
	private ArrayList<Quest> allQuests;
	private ArrayList<Scene> allScenes;

	private Comparator<User> progressComparator;
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

		allPaths = FileUtility.getAllPaths();
		allScenes = FileUtility.getAllScenes();

		allSpells = new ArrayList<>();
		for(LearningPath path : allPaths) {
			allSpells.addAll(path.getSpells());
		}

		allQuests = new ArrayList<>();
		for (LearningPath path : allPaths) {
			allQuests.addAll(path.getQuests());
		}

		progressComparator = new Comparator<User>() {
			@Override
			public int compare(User u1, User u2) {
				float progress1 = u1.getProgressPercentage(allSpells.size(), allQuests.size());
				float progress2 = u2.getProgressPercentage(allSpells.size(), allQuests.size());

				return Double.compare(progress2, progress1);
			}
		};

		changeScreen(ScreenType.MENU);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		disposeScreens();
		skin.dispose();
	}

	public Comparator<User> getProgressComparator() {
		return progressComparator;
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
		return FileUtility.getAllUsers();
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

	public ArrayList<Quest> getAllQuests() {
		return allQuests;
	}

	public void setCurrentUser(User user) {
		TypingConfig.GLOBAL_VARS.put("USERNAME", user.getUsername());
		currentUser = user;
	}

	public enum ScreenType {
		MENU,
		NEWGAME,
		LOADGAME,
		HOME,
		SELECT_PATH,
		SPELL_PROGRESS,
		QUEST_PROGRESS,
		LEADERBOARD,
		GAME,
		SPELL,
		QUEST
	}

	// changing screens 
	public void changeScreen(ScreenType screen) {
		switch (screen) {
			case MENU:
				disposeScreens();
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				setScreen(menuScreen);
				currentUser = null;
				TypingConfig.GLOBAL_VARS.remove("USERNAME");
				break;
		
			case NEWGAME:
				if (newGameScreen == null) newGameScreen = new NewGameScreen(this);
				setScreen(newGameScreen);
				break;

			case LOADGAME:
				if (loadGameScreen == null) loadGameScreen = new LoadGameScreen(this);
				setScreen(loadGameScreen);
				break;

			case HOME:
				if(homeScreen == null) homeScreen = new HomeScreen(this);
				setScreen(homeScreen);
				break;
				
			case SELECT_PATH:
				if (selectPathScreen == null)
					selectPathScreen = new SelectPathScreen(this);
				setScreen(selectPathScreen);
				break;

			case SPELL_PROGRESS:
				if (spellProgressScreen == null)
					spellProgressScreen = new SpellProgressScreen(this);
				setScreen(spellProgressScreen);
				break;

			case QUEST_PROGRESS:
				if (questProgressScreen == null)
					questProgressScreen = new QuestProgressScreen(this);
				setScreen(questProgressScreen);
				break;

			case LEADERBOARD:
				if (leaderboardScreen == null)
					leaderboardScreen = new LeaderboardScreen(this);
				setScreen(leaderboardScreen);
				break;

			case GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;

			case SPELL:
				if (spellScreen == null)
					spellScreen = new SpellScreen(this);
				setScreen(spellScreen);
				break;

			case QUEST:
				if (questScreen == null)
					questScreen = new QuestScreen(this);
				setScreen(questScreen);
				break;

			default:
				throw new IllegalArgumentException("Invalid Screen");
		}
	}

	private void disposeScreens() {
		if (menuScreen != null) {
			menuScreen.dispose();
			menuScreen = null;
		}
		if (newGameScreen != null) {
			newGameScreen.dispose();
			newGameScreen = null;
		}
		if (loadGameScreen != null) {
			loadGameScreen.dispose();
			loadGameScreen = null;
		}
		if (homeScreen != null) {
			homeScreen.dispose();
			homeScreen = null;
		}
		if (selectPathScreen != null) {
			selectPathScreen.dispose();
			selectPathScreen = null;
		}
		if (spellProgressScreen != null) {
			spellProgressScreen.dispose();
			spellProgressScreen = null;
		}
		if (questProgressScreen != null) {
			questProgressScreen.dispose();
			questProgressScreen = null;
		}
		if (leaderboardScreen != null) {
			leaderboardScreen.dispose();
			leaderboardScreen = null;
		}
		if (gameScreen != null) {
			gameScreen.dispose();
			gameScreen = null;
		}
		if (spellScreen != null) {
			spellScreen.dispose();
			spellScreen = null;
		}
		if (questScreen != null) {
			questScreen.dispose();
			questScreen = null;
		}
	}
}
