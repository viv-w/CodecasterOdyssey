package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.User;

public class SceneManager {
    private CodecasterOdyssey game;
    private Table gameView;
    private Scene scene;
    private Event currentEvent;
    private int index = 0;

    public SceneManager(CodecasterOdyssey game) {
        this.game = game;
    }

    public void setGameView(Table gameView) {
        this.gameView = gameView;
    }

    public void load(String sceneId) {
        this.scene = searchScene(sceneId);

        for (Event event : scene.getEvents()) {
            event.setScene(scene);
            event.setupGame(game, gameView);
            event.setNextSequence(() -> nextSequence());
        }

        index = 0;
        nextSequence();
    }

    public void loadNext() {
        if(this.scene.isComplete()) {
            String nextScene = scene.getNextId();
            load(nextScene);
        }
    }

    public void loadNext(String sceneId) {

        if (this.scene == null)
            this.scene = searchScene(sceneId);

        String nextScene = scene.getNextId();
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
        if(index == 0) findCheckpoint();
         
        if(index < scene.getEvents().size()) { // normal
            currentEvent = scene.getEvents().get(index);
            scene.getEvents().get(index).execute();
        } else { // next scene
            index = 0;
            game.getCurrentUser().addCompletedScene(scene.getId());
            scene.setCompleted(true);

            if(scene.getNextId() != null)    
                loadNext();
        }
    }

    private void findCheckpoint() {
        User user = game.getCurrentUser();

        if (user.getCompletedQuests().contains(scene.getQuest())) { // post-quest checkpoint
            for(int i = 0; i < scene.getEvents().size(); i++) {
                if(scene.getEvents().get(i) instanceof QuestEvent) {
                    index = i + 1;
                }
            }
        } else if (user.getCurrentQuest() != null && user.getCurrentQuest().equals(scene.getQuest())) { // quest checkpoint
            for (int i = 0; i < scene.getEvents().size(); i++) {
                if (scene.getEvents().get(i) instanceof UnlockEvent
                        && !((UnlockEvent) scene.getEvents().get(i)).getIsSpell()) {
                    index = i;
                }
            }
        } else if (user.getCollectedSpells().contains(scene.getSpell())) { // spell checkpoint
            for (int i = 0; i < scene.getEvents().size(); i++) {
                if (scene.getEvents().get(i) instanceof UnlockEvent
                        && ((UnlockEvent) scene.getEvents().get(i)).getIsSpell()) {
                    index = i;
                }
            }
        }
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public Scene getScene() {
        return scene;
    }

}
