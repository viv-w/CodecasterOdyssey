package com.fyp.codecasterodyssey.events;

import com.github.tommyettinger.textra.TypingLabel;

public class EndEvent extends Event {
    
    public EndEvent() {}

    @Override
    public void execute() {
        gameView.clear();
        
        String str = "Congratulations, you have completed " + game.getCurrentUser().getCurrentPath() + ", please select another path.";
        TypingLabel label = new TypingLabel(str, game.getSkin(), game.getFontFamily().connected[0]);
        label.setWrap(true);
        gameView.add(label).minWidth(400).pad(3);
    }
}
