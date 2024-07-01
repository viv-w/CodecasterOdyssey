package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.Spell;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.LearningMetrics.Level;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class SelectPathScreen extends BaseScreen {

    private Table root;
    private Label currentPathLabel, instructionLabel, descLabel, questsLabel, recommendLabel;
    private SelectBox<String> pathSelect;
    private TextButton confirmButton;

    public SelectPathScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }
    
    @Override
    protected void setupUI() {
        root = new Table();
        stage.addActor(root);
        root.setFillParent(true);

        currentPathLabel = new Label("", game.getSkin());
        root.add(currentPathLabel).pad(5);
        root.row();  

        instructionLabel = new Label("Select a path:", game.getSkin());
        root.add(instructionLabel).pad(2);
        root.row();
        
        pathSelect = new SelectBox<>(game.getSkin());
        ArrayList<LearningPath> paths = game.getAllPaths();
        String[] pathNames = new String[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            pathNames[i] = " Path " + paths.get(i).getNum() + " ";
        }
        pathSelect.setItems(pathNames);
        pathSelect.addListener(new ChangeListener() {
            
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateUI(game.getAllPaths().get(pathSelect.getSelectedIndex()));
            }
        });
        root.add(pathSelect).pad(2);
        root.row();

        descLabel = new Label("", game.getSkin());
        descLabel.setWrap(true);
        root.add(descLabel).minWidth(400);
        root.row();

        questsLabel = new Label("", game.getSkin());
        questsLabel.setWrap(true);
        root.add(questsLabel).minWidth(400);
        root.row();

        recommendLabel = new Label("", game.getSkin());
        root.add(recommendLabel).pad(5).minWidth(400);
        root.row();

        confirmButton = new TextButton(" Select Path ", game.getSkin());
        confirmButton.addListener(new ChangeListener() {
            
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getCurrentUser().setCurrentPath(game.getAllPaths().get(pathSelect.getSelectedIndex()).getId());
                updateUI(game.getAllPaths().get(pathSelect.getSelectedIndex()));
            }
        });
        root.add(confirmButton).pad(3);

        updateUI(paths.get(0));

        stage.addActor(new ReturnButton(game, stage, "home"));
    }

    private void updateUI(LearningPath path) {
        currentPathLabel.setText("current path: path " + Integer.parseInt(game.getCurrentUser().getCurrentPath().substring(4)));

        descLabel.setText(path.getDesc());

        StringBuilder spellsBuilder = new StringBuilder();
        for(Spell spell: path.getSpells())
            spellsBuilder.append(spell.getName()).append("\n");
        questsLabel.setText(spellsBuilder.toString());

        confirmButton.setDisabled(game.getCurrentUser().getCompletedPaths().contains(path.getId()));
        checkIfRecommended(path);
    }

    private void checkIfRecommended(LearningPath selectedPath) {
        LearningPath recommendedPath = null;
        String reason = "";

        User user = game.getCurrentUser();
        int currentPathIndex = 0;
        ArrayList<LearningPath> paths = game.getAllPaths();

        for(int i = 0; i < paths.size(); i++) {
            if (user.getCurrentPath().equals(paths.get(i).getId())) {
                currentPathIndex = i;
                break;
            }
        }

        if(user.getLearningMetrics().getLevel() == Level.EASY && !user.getCurrentPath().equals(paths.get(0).getId())) {
            for(int i = 0; i < paths.size(); i++) {

                if(user.getCurrentPath().contains(paths.get(i).getId())) {
                    recommendedPath = paths.get(i);
                    reason = "** You are recommended to proceed forward with this path";
                    break;
                }
                else if(!user.getCompletedPaths().contains(paths.get(i).getId())) {
                    recommendedPath = paths.get(i);
                    reason = "** You are recommended to complete this prerequisite path.";
                    break;
                }

                if (i == currentPathIndex)
                    break;
            }
        } else {
            for (int i = currentPathIndex; i < paths.size(); i++) {
                if(!user.getCompletedPaths().contains(paths.get(i).getId())) {
                    recommendedPath = paths.get(i);
                    reason = "** You are recommended to proceed forward with this path";
                    break;
                }
            }
        }

        if (recommendedPath == selectedPath)
            recommendLabel.setText(reason);
        else
            recommendLabel.setText("");

    }
}
