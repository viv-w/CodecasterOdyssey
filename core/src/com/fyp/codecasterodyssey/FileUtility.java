package com.fyp.codecasterodyssey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.fyp.codecasterodyssey.events.Scene;

public class FileUtility {

    // User JSON Management
    private static final String SAVEDATA = "savedata/";
    private static final String SCENE = "scenes/";
    private static final String QUEST = "quests/";
    private static final String SPELL = "spells/";
    private static final String SOLUTIONS = "solutions/";
    private static final String SOLUTION_TXT = "solution.txt";
    private static final String PROFILE_JSON = "profile.json";
    private static final String JSON_EXTENSION = ".json";

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        if(!Gdx.files.local(SAVEDATA).isDirectory())
            Gdx.files.local(SAVEDATA).mkdirs();
            
        FileHandle[] userFolders = Gdx.files.local(SAVEDATA).list(file -> file.isDirectory());

        for (FileHandle userFolder : userFolders) {
            FileHandle profileFile = Gdx.files.local(userFolder.path() + "/" + PROFILE_JSON);
            
            if(profileFile.exists()) {
                Json json = new Json();
                users.add(json.fromJson(User.class, profileFile));
            }
        }

        return users;
    }

    public static boolean isUserExist(String username) {
        return Gdx.files.local(SAVEDATA + username + "/").exists();
    }

    public static void updateUserJSON(User user) {

        String userFolderPath = SAVEDATA + user.getUsername() + "/";
        FileHandle userFolder = Gdx.files.local(userFolderPath);

        if(!userFolder.exists()) {
            userFolder.mkdirs();
        }

        // for saving
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        user.setLastSaved(sdf.format(new Date()));

        Json json = new Json();
        json.setOutputType(OutputType.json);

        FileHandle profileFile = Gdx.files.local(userFolderPath + PROFILE_JSON);
        profileFile.writeString(json.prettyPrint(user), false);
    }

    public static User loadUserJSON(String username) {
        String userFolderPath = SAVEDATA + username + "/";
        FileHandle profileFile = Gdx.files.local(userFolderPath + PROFILE_JSON); 
        Json json = new Json();

        return json.fromJson(User.class, profileFile);
    }

    // QuestLog Management
    @SuppressWarnings("unchecked")
    public static ArrayList<QuestLog> loadQuestLogs(String username) {
        String questLogPath = SAVEDATA + username + "/";
        FileHandle questLogFile = Gdx.files.local(questLogPath + "questlogs" + JSON_EXTENSION);

        if (questLogFile.exists()) {
            Json json = new Json();
            ArrayList<QuestLog> questLogs = json.fromJson(ArrayList.class, QuestLog.class, questLogFile);
            return questLogs;
        } else {
            return new ArrayList<>();
        }
    }

    public static void updateQuestLogsJSON(String username, ArrayList<QuestLog> questLogs) {
        String questLogPath = SAVEDATA + username + "/";
        FileHandle questLogFile = Gdx.files.local(questLogPath + "questlogs" + JSON_EXTENSION);

        Json json = new Json();
        json.setOutputType(OutputType.json);
        questLogFile.writeString(json.prettyPrint(questLogs), false);
    }

    // User Solutions .txt Management
    public static void saveSolution(String username, String questId, String input) {
        String solutionFolderPath = SAVEDATA + username + "/" + SOLUTIONS;
        FileHandle solutionFolder = Gdx.files.local(solutionFolderPath);

        if(!solutionFolder.exists()) {
            solutionFolder.mkdirs();
        }


        FileHandle solutionFile = Gdx.files.local(solutionFolderPath + questId + SOLUTION_TXT);
        solutionFile.writeString(input, false);
    }

    public static String loadSolution(String username, String questId) {
        String solutionFolderPath = SAVEDATA + username + "/" + SOLUTIONS;
        FileHandle solutionFile = Gdx.files.local(solutionFolderPath + questId + SOLUTION_TXT);

        if(solutionFile.exists()) 
            return solutionFile.readString();
        else
            return null;
    }

    // LearningPath JSON Management
    @SuppressWarnings("unchecked")
    public static ArrayList<LearningPath> getAllPaths() {

        FileHandle file = Gdx.files.internal("paths" + JSON_EXTENSION);
        Json json = new Json();
        ArrayList<LearningPath> paths = json.fromJson(ArrayList.class, LearningPath.class, file);

        for(LearningPath path : paths) {

            // scenes
            ArrayList<Scene> fullScenes = new ArrayList<>();
            for(Scene scene : path.getScenes()) {
                String sceneFilePath = SCENE + scene.getId() + JSON_EXTENSION;
                Scene fullScene = json.fromJson(Scene.class, Gdx.files.internal(sceneFilePath));
                fullScenes.add(fullScene);
            }
            path.setScenes(fullScenes);

            // quests
            ArrayList<Quest> fullQuests = new ArrayList<>();
            for (Quest quest : path.getQuests()) {
                String questFilePath = QUEST + quest.getId() + JSON_EXTENSION;
                Quest fullQuest = json.fromJson(Quest.class, Gdx.files.internal(questFilePath));
                fullQuests.add(fullQuest);
            }
            path.setQuests(fullQuests);

            // spells
            ArrayList<Spell> fullSpells = new ArrayList<>();
            for (Spell spell : path.getSpells()) {
                String spellFilePath = SPELL + spell.getId() + JSON_EXTENSION;
                Spell fullSpell = json.fromJson(Spell.class, Gdx.files.internal(spellFilePath));
                fullSpells.add(fullSpell);
            }
            path.setSpells(fullSpells);
        }

        return paths;

    }
}