package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class GameScreen extends BaseScreen {

    private Table table;
    private Label testLabel1, testLabel2;

    public GameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        testLabel1 = new Label("Hello " + game.getCurrentUser().getUsername() , game.getSkin());
        table.add(testLabel1).pad(5);
        table.row();
        testLabel2 = new Label("This is the game screen.", game.getSkin());
        table.add(testLabel2).pad(5);
        table.row();

        stage.addActor(new ReturnButton(game, false));
    }
}
