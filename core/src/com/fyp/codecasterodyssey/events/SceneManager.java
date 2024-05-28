package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public class SceneManager {
    private CodecasterOdyssey game;
    private Table gameView;
    private Scene scene;
    private static int index = 0;

    public SceneManager(CodecasterOdyssey game) {
        this.game = game;
    }

    public void setGameView(Table gameView) {
        this.gameView = gameView;
    }

    public void load(String sceneId) {
        for(Scene tScene : game.getAllScenes()) {
            if(tScene.getId().equals(sceneId))
                scene = tScene;
        }

        for (Event event : scene.getEvents()) {
            event.setupGame(game, gameView);
            event.setNextSequence(() -> nextSequence());
        }

        nextSequence();
    }

    protected static void updateIndex() {
        index++;
    }

    private void nextSequence() {
        if(index < scene.getEvents().size()) { // ensure manager does not go over index
            scene.getEvents().get(index).execute();
        }
    }
}
