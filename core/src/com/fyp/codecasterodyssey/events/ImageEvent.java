package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageEvent extends Event {
    private String path;
    private float duration;

    public ImageEvent() {}

    @Override
    public void execute() {

        gameView.clear();

        Texture texture = new Texture(path);
        Image image = new Image(new TextureRegionDrawable(texture));
        gameView.add(image).pad(5);

        // only for logo
        image.addAction(Actions.sequence(
            Actions.fadeOut(0f),
            Actions.fadeIn(duration - 2f),
            Actions.delay(duration),
            Actions.fadeOut(duration - 1f),
            Actions.delay(duration - 2f),
            Actions.run(() -> {
                gameView.removeActor(image);
                complete();
            })
        ));
    }
    
}
