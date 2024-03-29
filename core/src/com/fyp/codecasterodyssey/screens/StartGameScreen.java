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
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setupUI() {
        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        Label nameLabel = new Label("Enter your username", CodecasterOdyssey.skin);
        table.add(nameLabel).pad(5);
        table.row();

        // textfield to input username
        final TextField nameText = new TextField("", CodecasterOdyssey.skin);
        table.add(nameText).pad(5);
        table.row();

        // FIXME DEBUG
        final TextField testText = new TextField("meow?", CodecasterOdyssey.skin);
        table.add(testText).pad(5);
        table.row();

        // button to confirm username
        TextButton confirmButton = new TextButton("Confirm", CodecasterOdyssey.skin);
        table.add(confirmButton).minWidth(60).pad(5);
        table.row();

        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO confirmation dialog and error handling!!
                // DB create new User ofc!
                String playerName = nameText.getText();
                String test = testText.getText();
                System.out.println("your username is " + playerName);
                System.out.println("you like... " + test);
            }
        });

        // bot nav bar for home button  
        Table botTable = new Table();
        botTable.setFillParent(true);
        botTable.left().bottom();
        TextButton returnButton = new TextButton("Back to Menu", CodecasterOdyssey.skin);
        botTable.add(returnButton).minWidth(105).pad(5);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                codecaster.setScreen(new MenuScreen(codecaster));
            }
        });
        stage.addActor(botTable);
    }
    
}
