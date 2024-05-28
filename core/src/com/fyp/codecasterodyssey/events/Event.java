package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public abstract class Event {

    private String type;
    private Runnable sequence;
    protected CodecasterOdyssey game;
    protected Table gameView;

    public Event() {}

    public Event(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setupGame(CodecasterOdyssey game, Table gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void setNextSequence(Runnable sequence) {
        this.sequence = sequence;
    }

    protected void complete() {
        SceneManager.updateIndex();
        if(sequence != null) sequence.run();
    }

    public abstract void execute();
}
