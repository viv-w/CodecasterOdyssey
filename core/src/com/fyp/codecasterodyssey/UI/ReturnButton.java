package com.fyp.codecasterodyssey.UI;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;

public class ReturnButton extends Table {

    private TextButton returnButton;

    public ReturnButton(final CodecasterOdyssey game, Stage stage, String returnScreen) {
        super();
        this.setFillParent(true);
        this.left().bottom();

        String returnText = "";
        if(returnScreen.equals("menu")) returnText = " Back to Menu ";
        else if(returnScreen.equals("home")) returnText = " Home ";
        else if(returnScreen.equals("game")) returnText = " Back to Game ";

        returnButton = new TextButton(returnText, game.getSkin());
        this.add(returnButton).pad(5);

        final String menu = returnScreen;
        returnButton.addListener(new ChangeListener() {
            
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (menu.equals("menu")) game.changeScreen(ScreenType.MENU);
                else if (menu.equals("home")) game.changeScreen(ScreenType.HOME);
                else if (menu.equals("game")) game.changeScreen(ScreenType.GAME);
            }
        });

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Keys.ESCAPE) {
                    if (menu.equals("menu")) game.changeScreen(ScreenType.MENU);
                    else if (menu.equals("home")) game.changeScreen(ScreenType.HOME);
                    else if (menu.equals("game")) game.changeScreen(ScreenType.GAME);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
