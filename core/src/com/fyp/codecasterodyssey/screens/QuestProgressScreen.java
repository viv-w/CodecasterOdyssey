package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.Quest;
import com.fyp.codecasterodyssey.QuestLog;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ProgressBarTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class QuestProgressScreen extends BaseScreen {

    private Table root, scrollTable;
    private ScrollPane scrollPane;
    private BackgroundTable overallQuestTable;

    private ArrayList<QuestLog> questLogs;

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

            questLogs = game.getQuestLogs();

            for (LearningPath path : game.getAllPaths()) {
                for(String questId : allQuests) {
                    
                    if(path.getQuestbyId(questId) != null) {
                        Quest quest = path.getQuestbyId(questId);
                        QuestLog questLog = getQuestLogById(questId);
                        
                        BackgroundTable questTable = new BackgroundTable();
                        questTable.setBackgroundColour(1, 1, 1, 0.2f);

                        String questStr = quest.getId().toUpperCase() + ": " + quest.getName();
                        Label questLabel = new Label(questStr, game.getSkin());
                        questTable.add(questLabel).pad(2).left();
                        questTable.row();

                        String timeStr = "Time taken: " + getFormattedTimeTaken(questLog.getTimeTaken());
                        Label timeLabel = new Label(timeStr, game.getSkin());
                        questTable.add(timeLabel).pad(2).left();
                        questTable.row();

                        String hintUsed = questLog.isHintUsed() ? "Yes" : "No";
                        Label hintLabel = new Label("Hint Used? " + hintUsed, game.getSkin());
                        questTable.add(hintLabel).pad(2).left();
                        questTable.row();

                        String isComplete = questLog.isCompleted() ? "Completed" : "Ongoing";
                        Label completeLabel = new Label("Status: " + isComplete, game.getSkin());
                        questTable.add(completeLabel).pad(2).left();
                        questTable.row();

                        scrollTable.add(questTable).minWidth(400).minHeight(75).pad(5);
                        scrollTable.row();
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

    private QuestLog getQuestLogById(String questId) {
        for (QuestLog questLog : questLogs) {
            if (questLog.getQuestId().equals(questId))
                return questLog;
        }

        return null;
    }

    private String getFormattedTimeTaken(long timeTaken) {
        long seconds = timeTaken / 1000;
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
    
}
