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
    // private static final String SOLUTIONS = "solutions/";
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

        String jsonStr = json.prettyPrint(user);
        FileHandle profileFile = Gdx.files.local(userFolderPath + PROFILE_JSON);
        profileFile.writeString(jsonStr, false);
    }

    public static User loadUserJSON(String username) {
        String userFolderPath = SAVEDATA + username + "/";
        FileHandle profileFile = Gdx.files.local(userFolderPath + PROFILE_JSON);

        if(!profileFile.exists()) {
            return null; // FIXME give error dialog?
        }

        Json json = new Json();
        User user = json.fromJson(User.class, profileFile);

        return user;
    }

    // TODO Solutions .txt Management

    // LearningPath JSON Management
    @SuppressWarnings("unchecked")
    public static ArrayList<LearningPath> getAllPaths() {

        FileHandle file = Gdx.files.internal("paths" + JSON_EXTENSION);
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
