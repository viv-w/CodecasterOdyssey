package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.fyp.codecasterodyssey.events.Scene;

public class LearningPath {
    private String id;
    private String description;
    private ArrayList<Scene> scenes = new ArrayList<>();
    private ArrayList<Quest> quests = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();

    public LearningPath() {}

    public LearningPath(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getNum() {
        return Integer.parseInt(id.substring(4));
    }

    public String getDesc() {
        return description;
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public Scene getScenebyId(String sceneId) {
        for (Scene scene : scenes) {
            if (scene.getId().equals(sceneId))
                return scene;
        }

        return null;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public Quest getQuestbyId(String questId) {
        for (Quest quest : quests) {
            if (quest.getId().equals(questId))
                return quest;
        }

        return null;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public Spell getSpellbyId(String spellId) {
        for (Spell spell : spells) {
            if (spell.getId().equals(spellId))
                return spell;
        }

        return null;
    }

    public void setScenes(ArrayList<Scene> scenes) {
        this.scenes = scenes;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }
}
