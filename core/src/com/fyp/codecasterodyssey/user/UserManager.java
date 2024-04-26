package com.fyp.codecasterodyssey.user;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class UserManager {

    public static ArrayList<String> getAllUsers() {
        ArrayList<String> saveList = new ArrayList<>();

        if(Gdx.files.external(".prefs/").isDirectory()) {
            FileHandle[] files = Gdx.files.external(".prefs/").list();

            for (FileHandle file : files) {
                String fileName = file.name();

                if(fileName.startsWith("Profile_"))
                    saveList.add(fileName.substring(8));
            }
        }
        
        return saveList;
    }
}
