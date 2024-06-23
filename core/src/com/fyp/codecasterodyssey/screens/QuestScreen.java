package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Quest;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.github.tommyettinger.textra.TypingLabel;

public class QuestScreen extends BaseScreen {

    private Table root;

    public QuestScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        root.pad(5);
        root.top();
        stage.addActor(root);

        String currentQuest = game.getCurrentUser().getCurrentQuest();
        if(currentQuest != null) {
            Quest quest = null;
            for(Quest tQuest: game.getAllQuests()) {
                if(tQuest.getId().equals(currentQuest))
                    quest = tQuest;
            }
            // TODO activate quest = add to JSON? but that mean scene progress need to be save when quitting....
            // add a new storage of like activatedQuest or collectedQuest?? (yg alum dibuat) but is this wise?
            // and maybe add a system to show completed Quests actually...

            boolean isOutput = false;
            StringBuilder output = new StringBuilder();

            for(String line : quest.getContent()) {

                if(line.startsWith("OUTPUT_START")) {
                    isOutput = true;
                    output.setLength(0);
                    line = line.replace("OUTPUT_START", "").trim();
                }

                if(isOutput) {
                    if (line.contains("OUTPUT_END")) {
                        line = line.replace("OUTPUT_END", "").trim();
                        isOutput = false;
                        output.append(line);

                        Table outputBlock = new Table();

                        outputBlock.setBackground(game.getSkin().getDrawable("field"));
                        TypingLabel label = new TypingLabel("[BLACK]" + output.toString(), game.getSkin(), game.getFontFamily().connected[1]);
                        label.skipToTheEnd();
                        outputBlock.add(label).pad(2);
                        root.add(outputBlock).pad(5).left();
                        root.row();
                    } else {
                        output.append(line).append("\n");
                    }
                } 
                else {
                    TypingLabel label = new TypingLabel(line, game.getFontFamily().connected[1]);
                    label.setWrap(true);
                    label.skipToTheEnd();
                    root.add(label).pad(5).fillX().expandX();
                    root.row();
                }
            }

        }
        else {
            root.add(new Label("no quest selected", game.getSkin()));
        }

        stage.addActor(new ReturnButton(game, stage, "game"));

    }
    
}
