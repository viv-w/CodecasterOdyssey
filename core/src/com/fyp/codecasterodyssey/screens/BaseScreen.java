package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public abstract class BaseScreen extends ScreenAdapter {
    protected final CodecasterOdyssey game;
    protected final Stage stage;

    public BaseScreen(final CodecasterOdyssey codecasterOdyssey) {
        game = codecasterOdyssey;
        stage = new Stage(new ExtendViewport(240, 320));
    }

    @Override
    public void show() {
        stage.clear();
        setupUI();
        Gdx.input.setInputProcessor(stage);
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

    protected abstract void setupUI();

}