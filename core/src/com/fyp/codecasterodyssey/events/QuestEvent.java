package com.fyp.codecasterodyssey.events;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.FileUtility;
import com.fyp.codecasterodyssey.QuestData;
import com.fyp.codecasterodyssey.QuestLog;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.screens.GameScreen;

public class QuestEvent extends Event {

    private QuestData questData;
    private TextButton testButton, resetButton, hintButton; // settingsButton
    private Table output, buttonTable;
    private Label outputLabel;
    private TextArea input;
    private StringBuilder templateBuilder;
    private int hintIndex;

    private Dialog dialog;
    private Label hintLabel;
    private TextButton prevButton, nextButton;

    private int attempts = 0;
    private boolean hintUsed = false;
    private long startTime;
    private User user;

    public QuestEvent() {}

    @Override
    public void execute() {
        gameView.clear();
        gameView.setColor(0.38f, 0.60f, 0.70f, 1);
        gameView.pad(0);

        user = game.getCurrentUser();

        startTime = System.currentTimeMillis();
        questData = game.getPersonalisedQuest(scene.getQuest());

        templateBuilder = new StringBuilder();
        for (String str : questData.getTemplate()) {
            templateBuilder.append(str).append("\n");
        }
        templateBuilder.deleteCharAt(templateBuilder.length() - 1);

        buttonTable = new Table();
        gameView.add(buttonTable).fillX().colspan(2);
        gameView.row();
        buttonTable.right();

        hintButton = new TextButton(" Hints ", game.getSkin());
        hintButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hintUsed = true;
                hintDialog();
            }
        });
        buttonTable.add(hintButton).pad(5);

        resetButton = new TextButton(" Reset ", game.getSkin());
        resetButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                input.setText(templateBuilder.toString());
            }
        });
        buttonTable.add(resetButton).pad(5);

        testButton = new TextButton(" Run Code ", game.getSkin());
        testButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkCode();
            }
        });
        buttonTable.add(testButton).pad(5);

        output = new Table();
        output.top();
        output.setBackground(game.getSkin().getDrawable("field"));
        output.setColor(Color.GRAY);
        outputLabel = new Label("", game.getSkin().get("black", Label.LabelStyle.class));
        output.add(outputLabel).pad(2).expand().fill();
        gameView.add(output).left().fill().expand().width(Value.percentWidth(0.4f, gameView));

        String inputText = FileUtility.loadSolution(user.getUsername(), user.getCurrentQuest());
        if(inputText == null) inputText = templateBuilder.toString();

        input = new TextArea(inputText, game.getSkin().get("codeArea", TextField.TextFieldStyle.class)); 
        gameView.add(input).fill().expand().width(Value.percentWidth(0.6f, gameView));

    }

    public void checkCode() 
    {
        attempts++;

        String inputCode = input.getText();
        String[] inputLines = inputCode.split("\n");
        ArrayList<String> solutionLines = questData.getSolution();

        boolean isCorrect = true;
        StringBuilder errorMessage = new StringBuilder("ERROR in line(s): ");

        for(int i = 0; i < Math.max(inputLines.length, solutionLines.size()); i++) {
            if(i >= inputLines.length) {
                errorMessage.append(i + 1).append(": missing line in input");
                isCorrect = false;
            } else if (i >= solutionLines.size()) {
                errorMessage.append(i + 1).append(": extra line in input");
                isCorrect = false;
            } else if (!inputLines[i].trim().equals(solutionLines.get(i).trim())) {
                errorMessage.append(i + 1).append(", "); // TODO can add new data in QuestData
                isCorrect = false;
            }
        }

        if(isCorrect) { 
            outputLabel.setText(questData.getOutput());
            successDialog(questData.getSuccessMessage());
        } else {
            // if(enableHint && )
            if(errorMessage.length() > 0)
                
            //else
            outputLabel.setText("ERROR");
        }
    }

    public void saveCode() {
        QuestLog questLog = game.getQuestlogById(user.getCurrentQuest());

        long timeTaken = System.currentTimeMillis() - startTime;

        if (questLog != null) {
            timeTaken += questLog.getTimeTaken();
            game.updateQuestLog(questLog);
        } else {
            QuestLog newLog = new QuestLog(user.getCurrentQuest(), timeTaken, hintUsed, false);
            game.addQuestLog(newLog);
        }

        FileUtility.saveSolution(user.getUsername(), user.getCurrentQuest(), input.getText());
    }

    private void successDialog(String message) {
        Dialog dialog = new Dialog(" Quest Completed ", game.getSkin());
        dialog.text(message);
        dialog.button(" OK ", true);
        dialog.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                User user = game.getCurrentUser();
                user.addQuest(user.getCurrentQuest());

                QuestLog questLog = game.getQuestlogById(user.getCurrentQuest());

                long timeTaken = System.currentTimeMillis() - startTime;
                
                if(questLog != null) {
                    timeTaken += questLog.getTimeTaken();
                    game.updateQuestLog(questLog);
                } else {
                    QuestLog newLog = new QuestLog(user.getCurrentQuest(), timeTaken, hintUsed, true);
                    game.addQuestLog(newLog);
                }

                user.getLearningMetrics().log(attempts, hintIndex, timeTaken, user.getCompletedQuests().size());

                FileUtility.saveSolution(user.getUsername(), user.getCurrentQuest(), input.getText());

                user.setCurrentQuest(null);
                gameView.setColor(Color.BLACK);
                complete();

            }
        });
        dialog.show(((GameScreen) game.getScreen()).getStage());
    }

    private void hintDialog() {
        dialog = new Dialog("", game.getSkin());

        hintLabel = new Label("", game.getSkin());
        hintLabel.setWrap(true);
        dialog.getContentTable().add(hintLabel).width(300).pad(5);

        prevButton = new TextButton(" Previous ", game.getSkin());
        dialog.getButtonTable().add(prevButton);

        nextButton = new TextButton(" Next ", game.getSkin());
        dialog.getButtonTable().add(nextButton);

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(hintIndex > 0) 
                    hintIndex--;
                updateHintDialog();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(hintIndex < questData.getHints().size() - 1 && hintIndex < attempts) 
                    hintIndex++;
                updateHintDialog();
            }
        });

        updateHintDialog();

        dialog.button(" OK ", true);
        dialog.show(((GameScreen) game.getScreen()).getStage());
    }

    private void updateHintDialog() {
        hintLabel.setText(questData.getHints().get(hintIndex));
        dialog.getTitleLabel().setText(" Hint " + (hintIndex + 1));
        prevButton.setDisabled(hintIndex == 0);
        nextButton.setDisabled(hintIndex == questData.getHints().size() - 1 || hintIndex >= attempts);
        dialog.setHeight(hintLabel.getPrefHeight() + 75);
    }
    
}
