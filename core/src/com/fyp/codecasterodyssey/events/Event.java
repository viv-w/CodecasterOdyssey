package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.screens.GameScreen;

public abstract class Event {

    private String type;
    private Runnable sequence;
    protected Scene scene;
    protected CodecasterOdyssey game;
    protected Table gameView;

    public Event() {}

    public Event(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setupGame(CodecasterOdyssey game, Table gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void setNextSequence(Runnable sequence) {
        this.sequence = sequence;
    }

    protected void complete() {
        ((GameScreen) game.getScreen()).getSceneManager().updateIndex();
        
        if(sequence != null) { 
            sequence.run();
            
        } else {
            ((GameScreen) game.getScreen()).getSceneManager().loadNext();
        }
    }

    public abstract void execute();
}
