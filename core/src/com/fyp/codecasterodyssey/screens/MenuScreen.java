package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public class MenuScreen extends ScreenAdapter {

    final CodecasterOdyssey codecaster;
    private Stage stage;

    public MenuScreen(final CodecasterOdyssey game) {
        codecaster = game;
        stage = new Stage(new ExtendViewport(240, 320));
        Gdx.input.setInputProcessor(stage);

        setupUI();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.38f, 0.60f, 0.70f, 1);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setupUI() {
        Table menuTable = new Table();
        menuTable.setFillParent(true);
        stage.addActor(menuTable);

        // button that redirect user to StartGame
        TextButton startButton = new TextButton("Start Game", CodecasterOdyssey.skin);
        menuTable.add(startButton).minWidth(90).pad(5);
        menuTable.row();

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                codecaster.setScreen(new StartGameScreen(codecaster));
            }
        });

        // button that redirect user to LoadGame
        TextButton loadButton = new TextButton("Load Game", CodecasterOdyssey.skin);
        menuTable.add(loadButton).minWidth(90).pad(5);
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Load Game!!");
                // game.setScreen(new LoadGameScreen(game));
            }
        });
    }
}
