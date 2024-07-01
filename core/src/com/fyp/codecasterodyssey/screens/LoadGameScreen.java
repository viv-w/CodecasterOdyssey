package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.FileUtility;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class LoadGameScreen extends BaseScreen {

    private Table root, scrollTable;
    private ScrollPane scrollPane;
    // exportButton?

    public LoadGameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        scrollTable = new Table();
        scrollTable.top();

        ArrayList<User> allUsers = FileUtility.getAllUsers();
        if(!allUsers.isEmpty()) {
            for (User user : allUsers) {
                BackgroundTable saveTable = new BackgroundTable();
                saveTable.setTouchable(Touchable.enabled);
                saveTable.setBackgroundColour(1, 1, 1, 0.2f);
                
                Label username = new Label(user.getUsername(), game.getSkin());
                saveTable.add(username).expandX().center();
                saveTable.row();

                Label path = new Label(" Path " + Integer.parseInt(user.getCurrentPath().substring(4)), game.getSkin());
                saveTable.add(path).expandX().left();
                saveTable.row();

                Label lastSaved = new Label(" Last Saved: " + user.getLastSaved(), game.getSkin());
                saveTable.add(lastSaved).left();
                saveTable.row();

                Label playTime = new Label(" Play Time: " + user.getFormattedPlaytime(), game.getSkin());
                saveTable.add(playTime).left();
                saveTable.row();

                Label progressPercentage = new Label(" Total Progress: " + user.getProgressPercentage(game.getAllSpells().size(), game.getAllQuests().size()) + "%", game.getSkin());
                saveTable.add(progressPercentage).left();
                saveTable.row();

                final String currentSave = user.getUsername();
                saveTable.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setConfirmDialog(currentSave);
                    }
                });
                
                scrollTable.add(saveTable).minWidth(400).minHeight(100).pad(5);
                scrollTable.row();
            }

            scrollPane = new ScrollPane(scrollTable, game.getSkin());
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);
            scrollPane.setScrollingDisabled(true, false);
            scrollPane.validate();
            root.add(scrollPane).prefHeight(200).minWidth(300).pad(5);
            root.row();
        }
        else {
            root.add(new Label(" no saves ", game.getSkin())).pad(5);
        }

        stage.addActor(new ReturnButton(game, stage, "menu"));
    }

    private void setConfirmDialog(String username) {
        final String tempUsername = username;
        Dialog confirmDialog = new Dialog(" Load Save?", game.getSkin()) {
            @Override
            protected void result(Object object) {
                boolean confirm = (Boolean) object;
                if (confirm) {
                    game.setCurrentUser(FileUtility.loadUserJSON(tempUsername));
                    game.setQuestlogs();
                    game.changeScreen(ScreenType.HOME);
                }
            }
            
        };

        confirmDialog.text(" Load save file for " + username + "? ");
        confirmDialog.button(" yes ", true);
        confirmDialog.button(" no ", false);
        confirmDialog.show(stage);

    }

}
