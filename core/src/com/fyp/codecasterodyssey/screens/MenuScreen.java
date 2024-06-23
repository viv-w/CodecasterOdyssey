package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;

public class MenuScreen extends BaseScreen {
    
    private Table root;
    private TextButton newButton, loadButton;

    public MenuScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // button that redirect user to NewGame
        newButton = new TextButton(" New Game ", game.getSkin());
        root.add(newButton).pad(5);
        root.row();

        newButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.NEWGAME);
            }
        });

        // button that redirect user to LoadGame
        loadButton = new TextButton(" Load Game ", game.getSkin());
        root.add(loadButton).pad(5);
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(ScreenType.LOADGAME);
            }
        });

        // DEBUG: button that redirect user to code editor screen
        // menuTable.row();
        // testButton = new TextButton(" codeEditor debug ", game.getSkin());
        // menuTable.add(testButton).pad(5);
        // testButton.addListener(new ChangeListener() {
        //     @Override
        //     public void changed(ChangeEvent event, Actor actor) {
        //         game.changeScreen(ScreenType.DEBUG);
        //     }
        // });
    }
}
