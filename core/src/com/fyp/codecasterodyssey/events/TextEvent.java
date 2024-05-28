package com.fyp.codecasterodyssey.events;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.tommyettinger.textra.TypingLabel;

public class TextEvent extends Event {

    private TypingLabel label;
    private ArrayList<String> text;
    private boolean isSkip = false;
    private int index = 0;

    public TextEvent() {}

    @Override
    public void execute() {

        gameView.clear();
        index = 0;

        label = new TypingLabel(text.get(index), game.getSkin(), game.getFontFamily().connected[0]);
        index++;
        label.setWrap(true);
        gameView.add(label).minWidth(400).pad(3);
        
        gameView.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {                
                if(!isSkip) {
                    label.skipToTheEnd();  
                    setSkip();
                }
                else {
                    nextLine();
                    setSkip();
                }
            }
        });
    }

    private void setSkip() {
        isSkip = !isSkip;
    }

    private void nextLine() {
        if(index < text.size()) {
            label.setText(text.get(index));
            label.restart();
            index++;
        } 
        else {
            complete();
        }
    }
}
