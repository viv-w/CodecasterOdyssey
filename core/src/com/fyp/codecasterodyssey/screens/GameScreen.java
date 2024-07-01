package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.events.QuestEvent;
import com.fyp.codecasterodyssey.events.Scene;
import com.fyp.codecasterodyssey.events.SceneManager;
import com.fyp.codecasterodyssey.events.UnlockEvent;

public class GameScreen extends BaseScreen {

    private SceneManager sceneManager;
    private Table root, gameView, buttonTable, homeButton;
    private TextButton spellButton, questButton;
    private boolean spellTutorial, questTutorial;

    public GameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
        sceneManager = new SceneManager(codecasterOdyssey);

        spellTutorial = game.getCurrentUser().getCollectedSpells().isEmpty();
        questTutorial = game.getCurrentUser().getCurrentQuest() == null;
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // game view goes here
        gameView = new Table();
        gameView.setTouchable(Touchable.enabled);
        sceneManager.setGameView(gameView);

        gameView.setBackground(game.getSkin().getDrawable("base"));
        gameView.setColor(Color.BLACK);
        root.add(gameView).expand().fill();
        root.row();

        buttonTable = new Table();
        root.add(buttonTable).pad(5);

        homeButton = new TextButton(" Home ", game.getSkin());
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.HOME);
                saveCode();
            }
        });
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    game.changeScreen(ScreenType.HOME);
                    saveCode();

                    return true;
                } else {
                    return false;
                }
            }
        });

        buttonTable.add(homeButton).pad(5);
        questButton = new TextButton(" View Quest ", game.getSkin());
        questButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.QUEST);
                saveCode();

                if (questTutorial && sceneManager.getCurrentEvent() instanceof UnlockEvent) {
                    questTutorial = false;
                }
            }
        });
        if(game.getCurrentUser().getCurrentQuest() == null) questButton.setDisabled(true);
        buttonTable.add(questButton).pad(5);

        spellButton = new TextButton(" View Spells ", game.getSkin());
        spellButton.addListener(new ChangeListener() {    
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.SPELL);
                saveCode();

                if(spellTutorial && sceneManager.getCurrentEvent() instanceof UnlockEvent) {
                    spellTutorial = false;
                }
            }
        });
        if(game.getCurrentUser().getCollectedSpells().isEmpty()) spellButton.setDisabled(true);
        buttonTable.add(spellButton).pad(5);
        
        startGame();
    }

    private void saveCode() {
        if(sceneManager.getCurrentEvent() instanceof QuestEvent) {
            ((QuestEvent) sceneManager.getCurrentEvent()).saveCode();
        }
    }

    private void startGame() {
        User user = game.getCurrentUser();
        ArrayList<String> completedScenes = user.getCompletedScenes();

        if(completedScenes.isEmpty()) // for first time
            sceneManager.load(game.getAllScenes().get(0).getId());
        else {
            LearningPath currentPath = game.getPathbyId(user.getCurrentPath());
            for(Scene scene : currentPath.getScenes()) {
                if(!user.getCompletedScenes().contains(scene.getId())) {
                    sceneManager.load(scene.getId());
                    break;
                }
            }
        }
        // else if(sceneManager.getScene() != null && !sceneManager.getScene().isComplete()) { // for ongoing
        //     sceneManager.load(sceneManager.getScene().getId());
        // }
        // else { // for returning
        //     String latestScene = completedScenes.get(completedScenes.size() - 1);
        //     sceneManager.loadNext(latestScene);
        // }
    }

    public void resetButtons() {
        questButton.setDisabled(game.getCurrentUser().getCurrentQuest() == null);
        spellButton.setDisabled(game.getCurrentUser().getCollectedSpells().isEmpty());
    }

    public boolean getSpellTutorial() {
        return spellTutorial;
    }

    public boolean getQuestTutorial() {
        return questTutorial;
    }

    public Table getGameView() {
        return gameView;
    }

    public Stage getStage() {
        return stage;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}