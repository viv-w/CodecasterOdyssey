package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Constants;
import com.fyp.codecasterodyssey.user.User;

public class StartGameScreen extends BaseScreen {
    
    private Table table, botTable;
    private Label nameLabel, errorLabel;
    private TextField nameText;
    private TextButton confirmButton, returnButton;

    public StartGameScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        nameLabel = new Label("Enter your username", game.getSkin());
        table.add(nameLabel).pad(5);
        table.row();

        // textfield to input username
        nameText = new TextField("", game.getSkin());
        table.add(nameText).pad(5);
        table.row();

        // label to display error message
        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(0.75f);
        table.add(errorLabel).pad(5);
        table.row();

        // button to confirm username
        confirmButton = new TextButton(" Confirm ", game.getSkin());
        table.add(confirmButton).pad(5);
        table.row();

        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String username = nameText.getText();

                // ensure username is not empty
                if(username.isEmpty())
                    errorLabel.setText("please enter your username");

                // ensure username is not equal to a reserved value
                else if(username.equals("nil"))
                    errorLabel.setText("invalid username, please choose another username");
                
                // ensure username is alphanumeric
                else if(!isUsernameAlphanumeric(username))
                    errorLabel.setText("invalid username, please use only alphanumeric characters for your username");
                
                // ensure username is unique
                else if(!isUsernameUnique(username))
                    errorLabel.setText("username is taken, please choose another username");
                
                // FIXME should I limit the characters?
                // display confirmation dialog
                else {
                    errorLabel.setText("");
                    setConfirmDialog(username);
                }
            }
        });

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
    
    private boolean isUsernameAlphanumeric(String username) {
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }

    private boolean isUsernameUnique(String username) {
        return new User(username).getUsername().equals("nil");
    }

    private void setConfirmDialog(String username) {
        final String tempUsername = username;
        Dialog confirmDialog = new Dialog(" Username Confirmation", game.getSkin()) {
            @Override
            protected void result(Object object) {
                boolean confirm = (Boolean) object;
                if (confirm) {
                    game.setCurrentUser(tempUsername);
                    game.getCurrentUser().setupNewUser(tempUsername);
                    game.changeScreen(Constants.GAME);
                }
            }
        };

        confirmDialog.text(" Are you sure your username is " + username + "? "); 
        confirmDialog.button(" yes ", true);
        confirmDialog.button(" no ", false);
        confirmDialog.show(stage);

    }
}
