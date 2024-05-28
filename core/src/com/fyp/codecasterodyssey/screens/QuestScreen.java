package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class QuestScreen extends BaseScreen {

    private Table root;

    public QuestScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        stage.addActor(root);
        root.setFillParent(true);

        if(game.getCurrentUser().getCurrentQuest() != null) {
            // TODO something something StringBuilder
        }
        else {
            root.add(new Label("no quest selected", game.getSkin()));
        }

        stage.addActor(new ReturnButton(game, stage, "game"));

    }
    
}
