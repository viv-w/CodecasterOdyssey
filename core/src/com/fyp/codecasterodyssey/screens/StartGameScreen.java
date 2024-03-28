package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public class StartGameScreen extends ScreenAdapter {
    
    final CodecasterOdyssey codecaster;
    private Stage stage;

    public StartGameScreen(final CodecasterOdyssey game) {
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
    public void dispose() {
        stage.dispose();
    }

    private void setupUI() {
        Table table = new Table();
        table.setFillParent(true);

        Label nameLabel = new Label("Enter your username", CodecasterOdyssey.skin);
        table.add(nameLabel).space(10);
        table.row();
        TextField nameText = new TextField("", CodecasterOdyssey.skin);
        table.add(nameText).space(10);
        table.row();
        TextButton confirmButton = new TextButton("Confirm", CodecasterOdyssey.skin);
        table.add(confirmButton).space(10);

        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("confirm!");
            }
        });

        stage.addActor(table);
    }
    
}
