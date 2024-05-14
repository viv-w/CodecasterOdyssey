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
import com.fyp.codecasterodyssey.Quest;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class LearningPathScreen extends BaseScreen {

    private Table root;
    private Label currentPathLabel, instructionLabel, descLabel, questsLabel;
    private SelectBox<String> pathSelect;
    private TextButton confirmButton;

    public LearningPathScreen(final CodecasterOdyssey codecasterOdyssey) {
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
        for (int i = 0; i < paths.size(); i++)
            pathNames[i] = " Path " + paths.get(i).getNum() + " ";
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

        stage.addActor(new ReturnButton(game, "home"));

        // TODO getRecommendedPath() - either > their current or first path

    }

    private void updateUI(LearningPath path) {
        currentPathLabel.setText("current path: path " + Integer.parseInt(game.getCurrentUser().getCurrentPath().substring(4)));

        descLabel.setText(path.getDesc());

        StringBuilder questsBuilder = new StringBuilder();
        for(Quest quest: path.getQuests())
            questsBuilder.append(quest.getName()).append("\n");
        questsLabel.setText(questsBuilder.toString());
    }
}
