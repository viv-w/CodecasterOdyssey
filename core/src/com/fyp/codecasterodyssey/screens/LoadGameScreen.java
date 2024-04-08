package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.user.UserManager;

public class LoadGameScreen extends BaseScreen {

    private Table table, scrollTable, botTable;
    private ScrollPane scrollPane;
    private TextButton returnButton; // + exportButton if files not in .prefs? 

    public LoadGameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        scrollTable = new Table();
        scrollTable.top();
        ArrayList<String> saveList = UserManager.getAllUsers();
        if(!saveList.isEmpty()) {
            for (String save : saveList) {
                Table saveTable = new Table();
                Label label = new Label(" " + save + " ", game.getSkin());
                saveTable.add(label);

                final String currentSave = save;
                saveTable.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setConfirmDialog(currentSave);
                    }
                });
                scrollTable.add(saveTable).pad(5);
                scrollTable.row();
            }

            scrollPane = new ScrollPane(scrollTable, game.getSkin());
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);
            scrollPane.validate();
            table.add(scrollPane).prefHeight(200).minWidth(300).pad(5);
            table.row();
        }
        else {
            table.add(new Label(" no saves ", game.getSkin())).pad(5);
            table.row();
        }

        // bot nav bar for home button
        botTable = new Table();
        botTable.setFillParent(true);
        botTable.left().bottom();
        returnButton = new TextButton(" Back to Menu ", game.getSkin());
        botTable.add(returnButton).pad(5);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(Constants.MENU);
            }
        });
        stage.addActor(botTable);

    }

    private void setConfirmDialog(String username) {
        final String tempUsername = username;
        Dialog confirmDialog = new Dialog(" Load Save?", game.getSkin()) {
            @Override
            protected void result(Object object) {
                boolean confirm = (Boolean) object;
                if (confirm) {
                    game.setCurrentUser(tempUsername);
                    game.changeScreen(Constants.GAME);
                }
            }
        };

        confirmDialog.text(" Load save file for " + username + "? ");
        confirmDialog.button(" yes ", true);
        confirmDialog.button(" no ", false);
        confirmDialog.show(stage);

    }

}
