package com.fyp.codecasterodyssey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fyp.codecasterodyssey.screens.MenuScreen;

public class CodecasterOdyssey extends Game {
	
	static public Skin skin; // when to dispose?
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/skin.json"));
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
