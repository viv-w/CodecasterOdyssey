package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.github.tommyettinger.textra.TypingLabel;

public class QuestProgressScreen extends BaseScreen {

    private Table root;
    private BackgroundTable questTable;
    private Label questLabel;
    private ProgressBar questProgress;

    public QuestProgressScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        questTable = new BackgroundTable();
        questTable.setBackgroundColour(1, 1, 1, 0.2f);
        questLabel = new Label("Quest Completion", game.getSkin());
        questTable.add(questLabel).pad(2);
        questTable.row();
        questProgress = new ProgressBar(0, 100, 1, false, game.getSkin());
        questProgress.setValue(0);
        questTable.add(questProgress).pad(5);
        root.add(questTable).top();
        root.row();

        if(!game.getCurrentUser().getCollectedSpells().isEmpty()) {
            float totalQuests = 0;
            float completedQuests = 0;
            for (LearningPath path : game.getAllPaths()) {
                if (!path.getQuests().isEmpty())
                    totalQuests += path.getQuests().size();
            }

            if (!game.getCurrentUser().getCompletedQuests().isEmpty())
                completedQuests = (game.getCurrentUser().getCompletedQuests().size() / totalQuests) * 100;
            
            questProgress.setValue(completedQuests);
        } else {
            TypingLabel noQuest = new TypingLabel("no quests completed", game.getSkin());
            noQuest.skipToTheEnd();
            root.add(noQuest).minHeight(250);
        }

        stage.addActor(new ReturnButton(game, stage, "home"));

    }
    
}
