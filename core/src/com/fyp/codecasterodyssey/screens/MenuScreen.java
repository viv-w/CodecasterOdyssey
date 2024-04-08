package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;

public class MenuScreen extends BaseScreen {
    
    private Table menuTable;
    private TextButton startButton, loadButton;

    public MenuScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        menuTable = new Table();
        menuTable.setFillParent(true);
        stage.addActor(menuTable);

        // button that redirect user to StartGame
        startButton = new TextButton(" Start Game ", game.getSkin());
        menuTable.add(startButton).pad(5);
        menuTable.row();

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.STARTGAME);
            }
        });

        // button that redirect user to LoadGame
        loadButton = new TextButton(" Load Game ", game.getSkin());
        menuTable.add(loadButton).pad(5);
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.LOADGAME);
            }
        });
    }
}
