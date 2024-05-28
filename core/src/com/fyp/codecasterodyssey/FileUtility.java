package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.fyp.codecasterodyssey.events.Scene;

public class FileUtility {

    // User JSON Management
    private static final String SAVEDATA = "savedata/";
    private static final String PROFILE = "Profile_";
    private static final String JSON = ".json";

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        if(!Gdx.files.local(SAVEDATA).isDirectory())
            Gdx.files.local(SAVEDATA).mkdirs();
            
        FileHandle[] files = Gdx.files.local(SAVEDATA).list();

        for (FileHandle file : files) {
            Json json = new Json();
            users.add(json.fromJson(User.class, file));
        }

        return users;
    }

    public static boolean isUserExist(String username) {
        return Gdx.files.local(SAVEDATA + PROFILE + username + JSON).exists();
    }

    // FIXME I might need to make a folder for each user (store their solutions?)
    public static void updateUserJSON(User user) {
        Json json = new Json();
        json.setOutputType(OutputType.json);

        String jsonStr = json.prettyPrint(user);
        FileHandle file = Gdx.files.local(SAVEDATA + PROFILE + user.getUsername() + JSON);
        file.writeString(jsonStr, false);
    }

    public static User loadUserJSON(String username) {
        FileHandle file = Gdx.files.local(SAVEDATA + PROFILE + username + JSON);
        Json json = new Json();
        User user = json.fromJson(User.class, file);

        return user;
    }

    // LearningPath JSON Management
    @SuppressWarnings("unchecked")
    public static ArrayList<LearningPath> getAllPaths() {

        FileHandle file = Gdx.files.internal("paths" + JSON);
        Json json = new Json();
        ArrayList<LearningPath> paths = json.fromJson(ArrayList.class, LearningPath.class, file);

        return paths;
    }

    public static ArrayList<Scene> getAllScenes() {
        ArrayList<Scene> allScenes = new ArrayList<>();

        FileHandle[] files = Gdx.files.internal("scenes/").list();

        for (FileHandle file : files) {
            if(file.extension().equals("json")) {
                Json json = new Json();
                allScenes.add(json.fromJson(Scene.class, file));
            }
        }

        return allScenes;
    }
}
