package com.fyp.codecasterodyssey.user;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.fyp.codecasterodyssey.user.User;

// FileUtils
public class UserManager {
    private static final String PREFS = ".prefs/";
    private static final String PROFILE = "Profile_";
    private static final String JSON = ".json";

    public static ArrayList<String> getAllUsers() {
        ArrayList<String> saveList = new ArrayList<>();

        if(Gdx.files.external(PREFS).isDirectory()) {
            FileHandle[] files = Gdx.files.external(PREFS).list();

            for (FileHandle file : files) {
                String fileName = file.name();

                if(fileName.startsWith(PROFILE)) 
                    saveList.add(fileName.substring(8, fileName.length() - 5));
            }
        }

        return saveList;
    }

    public static boolean isUserExist(String username) {
        return Gdx.files.external(PREFS + PROFILE + username + JSON).exists();
    }

    public static void createUserJSON(User user) {
        Json json = new Json();
        json.setOutputType(OutputType.json);
        String jsonStr = json.prettyPrint(user);
        FileHandle file = Gdx.files.external(PREFS + PROFILE + user.getUsername() + JSON);
        file.writeString(jsonStr, false);
    }

    public static User loadUserJSON(String username) {
        FileHandle file = Gdx.files.external(PREFS + PROFILE + username + JSON);
        Json json = new Json();
        User user = json.fromJson(User.class, file);

        return user;
    }
}
