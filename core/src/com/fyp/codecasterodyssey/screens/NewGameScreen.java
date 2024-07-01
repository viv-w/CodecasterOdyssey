package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.FileUtility;
import com.fyp.codecasterodyssey.User;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class NewGameScreen extends BaseScreen {
    
    private Table root;
    private Label nameLabel, errorLabel;
    private TextField nameText;
    private TextButton confirmButton;

    public NewGameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        stage.addActor(root);
        root.setFillParent(true);

        nameLabel = new Label("Enter your username", game.getSkin());
        root.add(nameLabel).pad(5);
        root.row();

        // textfield to input username
        nameText = new TextField("", game.getSkin());
        root.add(nameText).pad(5);
        root.row();

        // label to display error message
        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(0.75f);
        root.add(errorLabel).pad(5);
        root.row();

        // button to confirm username
        confirmButton = new TextButton(" Confirm ", game.getSkin());
        root.add(confirmButton).pad(5);
        root.row();

        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String username = nameText.getText();

                // ensure username is not empty
                if(username.isEmpty())
                    errorLabel.setText("please enter your username");
                
                // ensure username is alphanumeric
                else if(!isUsernameAlphanumeric(username))
                    errorLabel.setText("invalid username, please use only alphanumeric characters for your username");
                
                // ensure length is less than equal 15 characters
                else if(username.length() > 15)
                    errorLabel.setText("invalid username, please use only at most 15 characters");

                // ensure username is unique
                else if(FileUtility.isUserExist(username))
                    errorLabel.setText("username is taken, please choose another username");
                
                // display confirmation dialog
                else {
                    errorLabel.setText("");
                    setConfirmDialog(username);
                }
            }
        });

        stage.addActor(new ReturnButton(game, stage, "menu"));
    }
    
    private boolean isUsernameAlphanumeric(String username) {
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }

    private void setConfirmDialog(String username) {
        final String tempUsername = username;
        Dialog confirmDialog = new Dialog(" Username Confirmation ", game.getSkin()) {
            @Override
            protected void result(Object object) {
                boolean confirm = (Boolean) object;
                if (confirm) {

                    User user = new User(tempUsername);
                    game.getAllUsers().add(user);
                    game.setCurrentUser(user);
                    game.setQuestlogs();
                    game.changeScreen(ScreenType.HOME);

                }
            }
        };

        confirmDialog.text(" Are you sure your username is " + username + "? "); 
        confirmDialog.button(" yes ", true);
        confirmDialog.button(" no ", false);
        confirmDialog.show(stage);

    }
}
