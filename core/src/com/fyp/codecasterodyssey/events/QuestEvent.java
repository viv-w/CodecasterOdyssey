package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.fyp.codecasterodyssey.screens.GameScreen;

public class QuestEvent extends Event {

    private Table output;
    private Label outputLabel;
    private TextArea input;

    public QuestEvent() {}

    @Override
    public void execute() {
        gameView.clear();
        gameView.pad(0);
        ((GameScreen) game.getScreen()).enableTestButton(true);

        output = new Table();
        output.top();
        output.setBackground(game.getSkin().getDrawable("field"));
        output.setColor(Color.GRAY);
        outputLabel = new Label("", game.getSkin().get("black", Label.LabelStyle.class));
        output.add(outputLabel).pad(2).expand().fill();
        gameView.add(output).left().fill().expand().width(Value.percentWidth(0.4f, gameView));

        input = new TextArea("System.out.println(\" \");", game.getSkin().get("codeArea", TextField.TextFieldStyle.class));
        gameView.add(input).fill().expand().width(Value.percentWidth(0.6f, gameView));

    }

    // TODO Quest101.json = solution(s), question(s), 
    // error feedback = check line by line!!
    // settings: reset code soln if any?
    // if its quest101, find qn101.json OR quest.getQnLink(Level.Medium)??
    public void checkCode() 
    {
        String correctAnswer = "System.out.println(\"Hello Mundur!\");";

        if(input.getText().equals(correctAnswer)) { // change this to using some other command instead
            outputLabel.setText("Hello Mundur!");
            successDialog(" Hello " + game.getCurrentUser().getUsername() + "! ");
        } else {
            outputLabel.setText("ERROR");
        }
    }

    private void successDialog(String message) {
        Dialog dialog = new Dialog(" Quest Completed ", game.getSkin());
        dialog.text(message);
        dialog.button(" OK ", true);
        dialog.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getCurrentUser().addQuest(game.getCurrentUser().getCurrentQuest());
                game.getCurrentUser().setCurrentQuest(null);
                ((GameScreen) game.getScreen()).enableTestButton(false);
                complete();
                dialog.remove(); 
            }
        });
        dialog.show(((GameScreen) game.getScreen()).getStage());
    }
}
