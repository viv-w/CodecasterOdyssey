package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.Quest;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ProgressBarTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class QuestProgressScreen extends BaseScreen {

    private Table root, scrollTable;
    private ScrollPane scrollPane;
    private BackgroundTable overallQuestTable;

    public QuestProgressScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.top();
        root.setFillParent(true);
        stage.addActor(root);

        overallQuestTable = new ProgressBarTable(game, false, false);
        root.add(overallQuestTable).pad(10);
        root.row();

        User user = game.getCurrentUser();
        if(!user.getCompletedQuests().isEmpty()) {
            scrollTable = new Table();
            scrollTable.top();

            ArrayList<String> allQuests = new ArrayList<>();
            allQuests.addAll(user.getCompletedQuests());
            if(user.getCurrentQuest() != null) allQuests.add(user.getCurrentQuest());

            for (LearningPath path : game.getAllPaths()) {
                for(String questId : allQuests) {
                    for(Quest quest : path.getQuests()) {
                        if(questId.contains(quest.getId())) {

                            BackgroundTable questTable = new BackgroundTable();
                            questTable.setBackgroundColour(1, 1, 1, 0.2f);

                            String questStr = quest.getId().toUpperCase() + ": " + quest.getName();
                            Label questLabel = new Label(questStr , game.getSkin());
                            
                            // TODO timetaken, status (just check if last of allQuests), hint used
                            
                            questTable.add(questLabel).pad(2).left();
                            scrollTable.add(questTable).minWidth(400).minHeight(75).pad(5);
                            scrollTable.row();

                        }
                    }
                }
            }

            scrollPane = new ScrollPane(scrollTable, game.getSkin());
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);
            scrollPane.setScrollingDisabled(true, false);
            scrollPane.validate();
            root.add(scrollPane).prefHeight(200).minWidth(300).pad(5);
            root.row();
                    
        } else {
            Label noQuest = new Label("no quests completed", game.getSkin());
            root.add(noQuest).minHeight(250);
        }

        stage.addActor(new ReturnButton(game, stage, "home"));

    }
    
}
