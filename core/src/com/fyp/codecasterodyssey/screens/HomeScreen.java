package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ProgressBarTable;

public class HomeScreen extends BaseScreen {
    
    private Table root, leftTable, rightTable;
    private BackgroundTable spellTable, questTable, leaderboardTable;
    private TextButton startButton, pathButton, exitButton;
    private Label leaderboardLabel;

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
                game.changeScreen(ScreenType.GAME);
            }
        });
        leftTable.add(startButton).pad(5);
        leftTable.row();

        pathButton = new TextButton(" Select Path ", game.getSkin());
        pathButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.SELECT_PATH);
           } 
        });
        updatePathButton();
        leftTable.add(pathButton).pad(5);
        leftTable.row();

        exitButton = new TextButton(" Exit to Menu ", game.getSkin());
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.MENU);
            }
        });
        leftTable.add(exitButton).pad(5);
        leftTable.row();
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    game.changeScreen(ScreenType.MENU);
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
        spellTable = new ProgressBarTable(game, true, true);
        rightTable.add(spellTable).pad(5).minWidth(230);
        rightTable.row();

        // Quest Progress
        questTable = new ProgressBarTable(game, true, false);
        rightTable.add(questTable).pad(5).minWidth(230);
        rightTable.row();

        // Leaderboard
        leaderboardTable = new BackgroundTable();
        leaderboardTable.setTouchable(Touchable.enabled);
        leaderboardTable.setBackgroundColour(1, 1, 1, 0.2f);
        leaderboardLabel = new Label("Leaderboard", game.getSkin());
        leaderboardTable.add(leaderboardLabel).colspan(3).pad(2);
        leaderboardTable.row();

        // ranking
        ArrayList<User> rankedUsers = new ArrayList<>();
        rankedUsers.addAll(game.getAllUsers());
        Collections.sort(rankedUsers, game.getProgressComparator());
        for (int i = 0; i < rankedUsers.size() && i < 5; i++) {
            Label rankLabel = new Label(String.format(" #%d ", i+1), game.getSkin());
            Label usernameLabel = new Label(rankedUsers.get(i).getUsername(), game.getSkin());
            Label percentageLabel = new Label(rankedUsers.get(i).getProgressPercentage(game.getAllSpells().size(), game.getAllQuests().size()) + "% ", game.getSkin());

            leaderboardTable.add(rankLabel).left().pad(3);
            leaderboardTable.add(usernameLabel).left().expandX().pad(3);
            leaderboardTable.add(percentageLabel).right().pad(3);
            leaderboardTable.row();

        }
        leaderboardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(ScreenType.LEADERBOARD);
            }
        });
        rightTable.add(leaderboardTable).minWidth(230).pad(5);
    }

    public void updatePathButton() {
        pathButton.setDisabled(game.getCurrentUser().getCompletedScenes().isEmpty());
    }

}
