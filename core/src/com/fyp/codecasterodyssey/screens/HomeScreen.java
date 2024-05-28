package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;

public class HomeScreen extends BaseScreen {
    
    private Table root, leftTable, rightTable;
    private BackgroundTable spellTable, questTable, leaderboardTable;
    private TextButton startButton, pathButton, exitButton;
    private ProgressBar spellProgress, questProgress;
    private Label spellLabel, questLabel, leaderboardLabel;
    private User currentUser = game.getCurrentUser(); // TODO progress data 

    public HomeScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        stage.addActor(root);
        root.setFillParent(true);
        
        // LEFT SIDE OF UI
        leftTable = new Table();
        root.add(leftTable).left();

        startButton = new TextButton(" Start Game ", game.getSkin());
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.GAME);
            }
        });
        leftTable.add(startButton).pad(5);
        leftTable.row();

        pathButton = new TextButton(" Select Path ", game.getSkin());
        pathButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.LEARNING_PATH);
           } 
        });
        if(game.getCurrentUser().getCompletedScenes().isEmpty()) pathButton.setDisabled(true);
        leftTable.add(pathButton).pad(5);
        leftTable.row();

        exitButton = new TextButton(" Exit to Menu ", game.getSkin());
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.MENU);
            }
        });
        leftTable.add(exitButton).pad(5);
        leftTable.row();
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    game.changeScreen(Constants.MENU);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // RIGHT SIDE OF UI
        rightTable = new Table();
        root.add(rightTable).right();

        // Spell Progress
        spellTable = new BackgroundTable();
        spellTable.setTouchable(Touchable.enabled);
        spellTable.setBackgroundColour(1, 1, 1, 0.2f);
        spellLabel = new Label("Spell Collection", game.getSkin());
        spellTable.add(spellLabel).pad(2);
        spellTable.row();
        spellProgress = new ProgressBar(0, 100, 1, false, game.getSkin());

        float totalSpells = 0;
        float collectedSpells = 0;
        for(LearningPath path: game.getAllPaths()) {
            if(!path.getSpells().isEmpty()) {
                totalSpells += path.getSpells().size(); // FIXME doing this can implies quest & spell != 1:1
            }
        }

        if(!currentUser.getCollectedSpells().isEmpty())
            collectedSpells = (currentUser.getCollectedSpells().size() / totalSpells) * 100;

        spellProgress.setValue(collectedSpells);
        spellTable.add(spellProgress).pad(5);
        spellTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Constants.SPELL_PROGRESS);
            }
        });
        rightTable.add(spellTable).pad(5).minWidth(230);
        rightTable.row();

        // Quest Progress
        // TODO progress bar is repeated. make a class instead
        questTable = new BackgroundTable();
        questTable.setTouchable(Touchable.enabled);
        questTable.setBackgroundColour(1, 1, 1, 0.2f);
        questLabel = new Label("Quest Collection", game.getSkin());
        questTable.add(questLabel).pad(2);
        questTable.row();
        questProgress = new ProgressBar(0, 100, 1, false, game.getSkin());

        float totalQuests = 0;
        float completedQuests = 0;
        for(LearningPath path : game.getAllPaths()) {
            if(!path.getQuests().isEmpty())
                totalQuests += path.getQuests().size();
        }
        
        if(!currentUser.getCompletedQuests().isEmpty())
            completedQuests = (currentUser.getCompletedQuests().size() / totalQuests) * 100;

        questProgress.setValue(completedQuests);
        questTable.add(questProgress).pad(5);
        questTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Constants.QUEST_PROGRESS);
            }
        });
        rightTable.add(questTable).pad(5).minWidth(230);
        rightTable.row();

        // Leaderboard
        leaderboardTable = new BackgroundTable();
        leaderboardTable.setTouchable(Touchable.enabled);
        leaderboardTable.setBackgroundColour(1, 1, 1, 0.2f);
        leaderboardLabel = new Label("Leaderboard", game.getSkin());
        leaderboardTable.add(leaderboardLabel).colspan(3).pad(2);
        leaderboardTable.row();

        // TODO ranking
        ArrayList<User> users = game.getAllUsers();
        for (int i = 0; i < users.size() && i < 5; i++) {
            float processPrecentage = ((completedQuests + collectedSpells) / (totalQuests + totalSpells)) * 100;

            Label rankLabel = new Label(String.format(" #%d ", i+1), game.getSkin());
            Label usernameLabel = new Label(users.get(i).getUsername(), game.getSkin());
            Label percentageLabel = new Label(String.format("%5.2f%% ", processPrecentage), game.getSkin());

            leaderboardTable.add(rankLabel).left().pad(3);
            leaderboardTable.add(usernameLabel).left().expandX().pad(3);
            leaderboardTable.add(percentageLabel).right().pad(3);
            leaderboardTable.row();

        }
        leaderboardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Constants.LEADERBOARD);
            }
        });
        rightTable.add(leaderboardTable).minWidth(230).pad(5);

    }
}
