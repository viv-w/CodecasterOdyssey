package com.fyp.codecasterodyssey.screens;

import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class SpellProgressScreen extends BaseScreen {

    public SpellProgressScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {

        stage.addActor(new ReturnButton(game, "home"));

    }
    
}
