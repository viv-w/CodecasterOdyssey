package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;

public class SceneManager {
    private CodecasterOdyssey game;
    private Table gameView;
    private Scene currentScene;
    private Event currentEvent;
    private int index = 0;

    public SceneManager(CodecasterOdyssey game) {
        this.game = game;
    }

    public void setGameView(Table gameView) {
        this.gameView = gameView;
    }

    public void load(String sceneId) {
        this.currentScene = searchScene(sceneId);

        for (Event event : currentScene.getEvents()) {
            event.setScene(currentScene);
            event.setupGame(game, gameView);
            event.setNextSequence(() -> nextSequence());
        }

        nextSequence();
    }

    public void loadNext() {
        if(this.currentScene.isComplete()) {
            String nextScene = currentScene.getNextId();
            load(nextScene);
        }
    }

    public void loadNext(String sceneId) {

        if (this.currentScene == null)
            this.currentScene = searchScene(sceneId);

        String nextScene = currentScene.getNextId();
        load(nextScene);
    }

    private Scene searchScene(String sceneId) {

        for (Scene s : game.getAllScenes()) {
            if (s.getId().equals(sceneId)) {
                return s;
            }
        }

        return null;
    }

    public void updateIndex() {
        index++;
    }

    private void nextSequence() {
        if(index < currentScene.getEvents().size()) {
            currentEvent = currentScene.getEvents().get(index);
            currentScene.getEvents().get(index).execute();
        } else {
            if(currentScene.getNextId() != null) {
                index = 0;
                game.getCurrentUser().addCompletedScene(currentScene.getId());
                currentScene.setCompleted(true);
                loadNext();
            }
        }
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
