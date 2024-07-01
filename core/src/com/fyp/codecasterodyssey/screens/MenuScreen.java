package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.github.tommyettinger.textra.TypingLabel;

public class MenuScreen extends BaseScreen {
    
    private Table root;
    private TypingLabel title1, title2;
    private TextButton newButton, loadButton;

    public MenuScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        title1 = new TypingLabel("{SIZE=%300}{GRADIENT=#002d4c;#0062a5}{STYLE=BOLD}Codecaster", game.getSkin(), game.getFontFamily().connected[0]);
        root.add(title1);
        root.row();

        title2 = new TypingLabel("{SIZE=%300}{GRADIENT=#002d4c;#0062a5}{STYLE=BOLD}Odyssey", game.getSkin(), game.getFontFamily().connected[0]);
        root.add(title2).padBottom(20);
        root.row();

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

    }
}
