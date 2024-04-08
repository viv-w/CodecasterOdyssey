package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameScreen extends BaseScreen {

    private Table table, botTable;
    private Label testLabel;
    private TextButton returnButton;

    public GameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        testLabel = new Label("Hello " + game.getCurrentUser().getUsername(), game.getSkin());
        table.add(testLabel).pad(5);
        table.row();

        // bot nav bar for home button
        botTable = new Table();
        botTable.setFillParent(true);
        botTable.left().bottom();
        returnButton = new TextButton("Back to Menu", game.getSkin());
        botTable.add(returnButton).minWidth(105).pad(5);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.MENU);
            }
        });
        stage.addActor(botTable);
    }
}
