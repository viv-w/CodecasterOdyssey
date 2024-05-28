package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.events.SceneManager;

public class GameScreen extends BaseScreen {

    private SceneManager sceneManager;
    private Table root, gameTable, buttonTable, homeButton;
    private TextButton spellButton, questButton;

    public GameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
        sceneManager = new SceneManager(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // game view goes here
        gameTable = new Table();
        gameTable.setTouchable(Touchable.enabled);
        sceneManager.setGameView(gameTable);

        gameTable.setBackground(game.getSkin().getDrawable("base"));
        gameTable.setColor(Color.BLACK);
        root.add(gameTable).expand().fill();
        root.row();

        buttonTable = new Table();
        root.add(buttonTable).pad(5);

        homeButton = new TextButton(" Home ", game.getSkin());
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.HOME);
            }
        });
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    game.changeScreen(Constants.HOME);
                    return true;
                } else {
                    return false;
                }
            }
        });

        buttonTable.add(homeButton).pad(5);
        questButton = new TextButton(" View Quest ", game.getSkin());
        questButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.QUEST);
            }
        });
        buttonTable.add(questButton).pad(5);

        spellButton = new TextButton(" View Spells ", game.getSkin());
        spellButton.addListener(new ChangeListener() {    
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.SPELL);
            }
        });
        buttonTable.add(spellButton).pad(5);

        //REMOVE
        TextButton test = new TextButton(" ? ", game.getSkin());
        test.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.DEBUG);
            }
        });
        buttonTable.add(test).pad(5);

        startGame();
    }

    // TODO determine wht scene shud be playing for user
    private void startGame() {
        if(game.getCurrentUser().getCompletedScenes().isEmpty())
            sceneManager.load("scene01");
    }
}
