package com.fyp.codecasterodyssey.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BackgroundTable extends Table {
    
    public BackgroundTable() {
        super();
    }

    public void setBackgroundColour(float r, float g, float b, float a) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(r, g, b, a); 
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        Drawable background = new TextureRegionDrawable(new TextureRegion(texture));
        this.setBackground(background);
    }

    public void setBackgroundColour(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        Drawable background = new TextureRegionDrawable(new TextureRegion(texture));
        this.setBackground(background);
    }
}
