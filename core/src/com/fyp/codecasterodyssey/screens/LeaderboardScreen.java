package com.fyp.codecasterodyssey.screens;

// import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class LeaderboardScreen extends BaseScreen {

    // private Table root;

    public LeaderboardScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {

        stage.addActor(new ReturnButton(game, stage, "home"));
    }
    
}
