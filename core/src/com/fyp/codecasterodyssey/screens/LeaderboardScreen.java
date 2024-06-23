package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class LeaderboardScreen extends BaseScreen {

    private Table root, titleTable, scrollTable;
    private Label rankLabel, nameLabel, progressLabel;
    private ScrollPane scrollPane;

    public LeaderboardScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        root.top();
        stage.addActor(root);

        titleTable = new Table();
        rankLabel = new Label("Rank", game.getSkin());
        nameLabel = new Label("Player Name", game.getSkin());
        progressLabel = new Label("Overall Progress", game.getSkin());
        titleTable.add(rankLabel).pad(5).left().padRight(15);
        titleTable.add(nameLabel).pad(5).expandX().left();
        titleTable.add(progressLabel).pad(5).padRight(30);
        root.add(titleTable).minWidth(400).pad(5);
        root.row();

        scrollTable = new Table();
        scrollTable.top();

        ArrayList<User> rankedUsers = new ArrayList<>();
        rankedUsers.addAll(game.getAllUsers());
        Collections.sort(rankedUsers, game.getProgressComparator());

        for(int i = 0; i < rankedUsers.size(); i++) {

            BackgroundTable rankTable = new BackgroundTable();
            rankTable.setBackgroundColour(1, 1, 1, 0.2f);
            Label rankLabel = new Label(String.format(" #%d ", i+1), game.getSkin());
            Label usernameLabel = new Label(rankedUsers.get(i).getUsername(), game.getSkin());

            int percentage = rankedUsers.get(i).getProgressPercentage(game.getAllSpells().size(), game.getAllQuests().size());
            ProgressBar progressBar = new ProgressBar(0, 100, 1, false, game.getSkin());
            progressBar.setValue(percentage);
            Label percentageLabel = new Label(percentage + "% ", game.getSkin());

            rankTable.add(rankLabel).pad(5).left();
            rankTable.add(usernameLabel).pad(5).expandX().left();
            rankTable.add(percentageLabel).pad(5).right();
            rankTable.add(progressBar).pad(5).right();
            rankTable.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // TODO expand or remove - might remove this or implement it another way e.g. just display at bottom
                    }
                });

            scrollTable.add(rankTable).minWidth(400).pad(5);
            scrollTable.row();
        }

        scrollPane = new ScrollPane(scrollTable, game.getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.validate();
        root.add(scrollPane).prefHeight(200).minWidth(300).pad(5);
        root.row();

        stage.addActor(new ReturnButton(game, stage, "home"));
    }
    
}
