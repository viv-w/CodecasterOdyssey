package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.FileUtility;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.User;

public class HomeScreen extends BaseScreen {
    
    private Table root, leftTable, rightTable, spellTable, questTable, leaderboardTable;
    private TextButton startButton, pathButton, exitButton;
    private ProgressBar spellProgress, questProgress;
    private Label greeting, spellLabel, questLabel, leaderboardLabel;
    private User currentUser = game.getCurrentUser();

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

        greeting = new Label("Welcome, " + currentUser.getUsername(), game.getSkin());
        leftTable.add(greeting).pad(5);
        leftTable.row();
        
        startButton = new TextButton("Start Coding", game.getSkin());
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.GAME);
            }
        });
        leftTable.add(startButton).pad(5);
        leftTable.row();

        pathButton = new TextButton("Select Path", game.getSkin());
        pathButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.LEARNING_PATH);
           } 
        });
        leftTable.add(pathButton).pad(5);
        leftTable.row();

        exitButton = new TextButton("Exit to Menu", game.getSkin());
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.MENU);
            }
        });
        leftTable.add(exitButton).pad(5);
        leftTable.row();

        // RIGHT SIDE OF UI
        rightTable = new Table();
        root.add(rightTable).right();

        // Spell Progress
        spellTable = new Table();
        spellLabel = new Label("Spell Collection", game.getSkin());
        spellTable.add(spellLabel);
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
        rightTable.add(spellTable).pad(5);
        rightTable.row();

        // Quest Progress
        questTable = new Table();
        questLabel = new Label("Quest Collection", game.getSkin());
        questTable.add(questLabel);
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
        rightTable.add(questTable).pad(5);
        rightTable.row();

        // Leaderboard
        leaderboardTable = new Table();
        leaderboardLabel = new Label("Leaderboard", game.getSkin());
        leaderboardTable.add(leaderboardLabel).pad(1);
        leaderboardTable.row();
        ArrayList<String> userNames = FileUtility.getAllUsers();
        for (int i = 0; i < userNames.size() && i < 5; i++) {
            CharSequence leaderboard = String.format("#%-5d %-20s %5s", i+1, userNames.get(i), "0%");
            Label label = new Label(leaderboard, game.getSkin());
            leaderboardTable.add(label).pad(1);
            leaderboardTable.row();
            // FIXME alignment problem

        }
        leaderboardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Constants.LEADERBOARD);
            }
        });
        rightTable.add(leaderboardTable).pad(5);

    }
}
