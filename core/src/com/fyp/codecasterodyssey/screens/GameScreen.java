package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class GameScreen extends BaseScreen {

    private Table root;
    private TextButton spellButton, questButton;
    private Label testLabel1;

    public GameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);

        // TODO ...so it will take note of user progress (path, quest, spell) then run the corresponding scene
        // meaning scene.Java......?

    }

    @Override
    protected void setupUI() {
        root = new Table();
        stage.addActor(root);
        root.setFillParent(true);

        testLabel1 = new Label(" GAME SCREEN ", game.getSkin());
        root.add(testLabel1).pad(5);
        root.row();

        questButton = new TextButton("View Quest", game.getSkin());
        questButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.QUEST);
            }
        });
        root.add(questButton).pad(5);
        root.row();

        spellButton = new TextButton("View Spells", game.getSkin());
        spellButton.addListener(new ChangeListener() {    
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.SPELL);
            }
        });
        root.add(spellButton).pad(5);

        stage.addActor(new ReturnButton(game, "home"));
    }
}
