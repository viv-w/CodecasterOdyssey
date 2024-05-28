package com.fyp.codecasterodyssey.events;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.github.tommyettinger.textra.TypingLabel;

public class DialogueEvent extends Event {

    private String speaker;
    private String path;
    private ArrayList<String> text;
    private boolean isSkip = false;
    private int index = 0;
    TypingLabel label;

    public DialogueEvent() {}


    @Override
    public void execute() {

        gameView.clear();
        index = 0;

        Table imageTable = new Table();
        Texture texture = new Texture(path);
        Image image = new Image(new TextureRegionDrawable(texture));
        image.setScaling(Scaling.fill);
        imageTable.add(image).minWidth(50).minHeight(50);
        imageTable.row();
        TypingLabel imageLabel = new TypingLabel(speaker, game.getSkin(), game.getFontFamily().connected[0]); 
        imageLabel.skipToTheEnd();
        imageTable.add(imageLabel).pad(2);
        gameView.add(imageTable).left().pad(5);

        label = new TypingLabel(text.get(index), game.getSkin(), game.getFontFamily().connected[0]);
        index++;
        label.setWrap(true);
        gameView.add(label).minWidth(300);

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
        if (index < text.size()) {
            label.setText(text.get(index));
            label.restart();
            index++;
        } else {
            complete();
        }

    }
}
