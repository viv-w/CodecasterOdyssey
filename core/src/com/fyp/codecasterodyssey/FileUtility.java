package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class FileUtility {

    // User JSON Management
    private static final String PREFS = ".savedata/";
    private static final String PROFILE = "Profile_";
    private static final String JSON = ".json";

    public static ArrayList<String> getAllUsers() {
        ArrayList<String> saveList = new ArrayList<>();

        if(Gdx.files.local(PREFS).isDirectory()) {
            FileHandle[] files = Gdx.files.local(PREFS).list();

            for (FileHandle file : files) {
                String fileName = file.name();

                if(fileName.startsWith(PROFILE)) 
                    saveList.add(fileName.substring(8, fileName.length() - 5));
            }
        }

        return saveList;
    }

    public static boolean isUserExist(String username) {
        return Gdx.files.local(PREFS + PROFILE + username + JSON).exists();
    }

    // FIXME I might need to make a folder for each user (store their solutions?)
    // FIXME can I just use one JSON variable for all methods?
    public static void updateUserJSON(User user) {
        Json json = new Json();
        json.setOutputType(OutputType.json);

        String jsonStr = json.prettyPrint(user);
        FileHandle file = Gdx.files.local(PREFS + PROFILE + user.getUsername() + JSON);
        file.writeString(jsonStr, false);
    }

    public static User loadUserJSON(String username) {
        FileHandle file = Gdx.files.local(PREFS + PROFILE + username + JSON);
        Json json = new Json();
        User user = json.fromJson(User.class, file);

        return user;
    }

    // LearningPath JSON Management
    @SuppressWarnings("unchecked")
    public static ArrayList<LearningPath> loadPaths() {

        FileHandle file = Gdx.files.internal("paths" + JSON);
        Json json = new Json();
        ArrayList<LearningPath> paths = json.fromJson(ArrayList.class, LearningPath.class, file);

        return paths;
    }
}
