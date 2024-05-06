package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.fyp.codecasterodyssey.user.UserManager;

public class LoadGameScreen extends BaseScreen {

    private Table table, scrollTable;
    private ScrollPane scrollPane;
    // + exportButton if files not in .prefs? 

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

        stage.addActor(new ReturnButton(game, true));
    }

    private void setConfirmDialog(String username) {
        final String tempUsername = username;
        Dialog confirmDialog = new Dialog(" Load Save?", game.getSkin()) {
            @Override
            protected void result(Object object) {
                boolean confirm = (Boolean) object;
                if (confirm) {
                    game.setCurrentUser(UserManager.loadUserJSON(tempUsername));
                    game.changeScreen(Constants.HOME);
                }
            }
        };

        confirmDialog.text(" Load save file for " + username + "? ");
        confirmDialog.button(" yes ", true);
        confirmDialog.button(" no ", false);
        confirmDialog.show(stage);

    }

}
