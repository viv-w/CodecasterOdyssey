package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.fyp.codecasterodyssey.user.UserManager;

public class HomeScreen extends BaseScreen {
    
    private Table table, spellTable, questTable, leaderboardTable;
    private TextButton startButton, PathButton;
    private ProgressBar spellProgress, questProgress;
    private Label greeting, spellLabel, questLabel, leaderboardLabel;

    public HomeScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        //table.setDebug(true);

        greeting = new Label("Hello " + game.getCurrentUser().getUsername(), game.getSkin());
        table.add(greeting).pad(5);
        table.row();
        
        // Spell Progress
        spellTable = new Table();
        spellLabel = new Label("Spell Collection", game.getSkin());
        spellTable.add(spellLabel);
        spellTable.row();
        spellProgress = new ProgressBar(0, 100, 1, false, game.getSkin());
        spellProgress.setValue(50);
        spellTable.add(spellProgress).pad(5);
        spellTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // game.changeScreen(Constants.SPELL_PROGRESS);
                System.out.println("SPELL");
            }
        });
        table.add(spellTable).pad(5);
        table.row();

        // Quest Progress
        questTable = new Table();
        questLabel = new Label("Quest Collection", game.getSkin());
        questTable.add(questLabel);
        questTable.row();
        questProgress = new ProgressBar(0, 100, 1, false, game.getSkin());
        questProgress.setValue(76);
        questTable.add(questProgress).pad(5);
        questTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // game.changeScreen(Constants.QUEST_PROGRESS);
                System.out.println("QUEST");
            }
        });
        table.add(questTable).pad(5);
        table.row();

        // Leaderboard
        leaderboardTable = new Table();
        leaderboardLabel = new Label("Leaderboard", game.getSkin());
        leaderboardTable.add(leaderboardLabel).pad(1);
        leaderboardTable.row();
        //leaderboardTable.setDebug(true);
        ArrayList<String> userNames = UserManager.getAllUsers();
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
                // game.changeScreen(Constants.QUEST_PROGRESS);
                System.out.println("LEADERBOARD");
            }
        });
        table.add(leaderboardTable).pad(5);

        stage.addActor(new ReturnButton(game, true));
    }
}
