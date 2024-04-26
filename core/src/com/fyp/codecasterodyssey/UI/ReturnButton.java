package com.fyp.codecasterodyssey.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;

public class ReturnButton extends Table {

    private TextButton returnButton;

    public ReturnButton(final CodecasterOdyssey game, boolean isMenu) {
        super();
        this.setFillParent(true);
        this.left().bottom();

        returnButton = new TextButton(" Back to Menu ", game.getSkin());
        if(!isMenu) returnButton.getLabel().setText(" Home ");
        this.add(returnButton).pad(5);

        final boolean menu = isMenu;
        returnButton.addListener(new ChangeListener() {
            
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(menu)
                    game.changeScreen(Constants.MENU);
                else
                    game.changeScreen(Constants.HOME);
            }
        });

        // FIXME based on report, home button are on the right of Game Screen w Code Editor so...
    }
        
}
