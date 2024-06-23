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
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.events.QuestEvent;
import com.fyp.codecasterodyssey.events.SceneManager;
import com.fyp.codecasterodyssey.events.UnlockEvent;

public class GameScreen extends BaseScreen {

    private SceneManager sceneManager;
    private Table root, gameView, buttonTable, homeButton;
    private TextButton spellButton, questButton, testButton;
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

        // TODO add listener to save code each time user return
        homeButton = new TextButton(" Home ", game.getSkin());
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.HOME);
            }
        });
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    game.changeScreen(ScreenType.HOME);
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

                if(spellTutorial && sceneManager.getCurrentEvent() instanceof UnlockEvent) {
                    spellTutorial = false;
                }
            }
        });
        if(game.getCurrentUser().getCollectedSpells().isEmpty()) spellButton.setDisabled(true);
        buttonTable.add(spellButton).pad(5);
        
        startGame();
    }

    // FIXME this thing may still be incorrect
    private void startGame() {
        ArrayList<String> completedScenes = game.getCurrentUser().getCompletedScenes();

        if(completedScenes.isEmpty()) // for first time
            sceneManager.load(game.getAllScenes().get(0).getId());
        else if(sceneManager.getCurrentScene() != null && !sceneManager.getCurrentScene().isComplete()) { // for ongoing
            sceneManager.load(sceneManager.getCurrentScene().getId());
        }
        else { // for returning
            String latestScene = completedScenes.get(completedScenes.size() - 1);
            sceneManager.loadNext(latestScene);
        }

    }

    public void resetButtons() {
        questButton.setDisabled(game.getCurrentUser().getCurrentQuest() == null);
        spellButton.setDisabled(game.getCurrentUser().getCollectedSpells().isEmpty());
    }

    public void enableTestButton(boolean isQuestEvent) {
        if(isQuestEvent) {
            testButton = new TextButton(" Test Code ", game.getSkin());
            testButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (sceneManager.getCurrentEvent() instanceof QuestEvent)
                       ((QuestEvent) sceneManager.getCurrentEvent()).checkCode();
                }
            });

            buttonTable.add(testButton).pad(5);
        } else {
            testButton.remove();
        }
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
