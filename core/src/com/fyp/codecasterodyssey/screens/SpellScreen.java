package com.fyp.codecasterodyssey.screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Spell;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.github.tommyettinger.textra.TypingLabel;

public class SpellScreen extends BaseScreen {

    private Table root;
    private BackgroundTable contentTable;
    private SelectBox<String> spellSelect;
    private ScrollPane scrollPane;
    private Label emptyLabel;

    public SpellScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        root.top();
        stage.addActor(root);

        ArrayList<String> spells = game.getCurrentUser().getCollectedSpells();
        if(!spells.isEmpty()) {
            spellSelect = new SelectBox<>(game.getSkin());
            String[] spellNames = new String[spells.size()];
            for(int i = 0; i < spellNames.length; i++)
                spellNames[i] = "Spell " + Integer.parseInt(spells.get(i).substring(5)) + " ";
            spellSelect.setItems(spellNames);
            spellSelect.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    updateUI(spells.get(spellSelect.getSelectedIndex()));
                }
            });
            root.add(spellSelect).pad(5);
            root.row();

            contentTable = new BackgroundTable();
            // contentTable.setBackgroundColour(0.9f, 0.9f, 0.9f, 0.9f);
            scrollPane = new ScrollPane(contentTable, game.getSkin());
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);
            scrollPane.setScrollingDisabled(true, false);
            scrollPane.validate();
            root.add(scrollPane).expandX().fillX().pad(3);
            root.row();

            // to prevent scrollPane to overflow to ReturnButton
            emptyLabel = new Label("", game.getSkin());
            root.add(emptyLabel).pad(5);

            updateUI(spells.get(spellSelect.getSelectedIndex()));

        } else {
            root.add(new Label("no spells collected", game.getSkin()));
        }

        stage.addActor(new ReturnButton(game, stage, "game"));
    }

    private void updateUI(String spellId) {

        contentTable.clear();

        Spell spell = game.getSpellbyId(spellId);

        boolean isCode = false;
        StringBuilder code = new StringBuilder();

        for (String line : spell.getContent()) {

            if(line.startsWith("CODE_START")){

                isCode = true;
                code.setLength(0);
                line = line.replace("CODE_START", "").trim();
            }

            // what if we do... auto syntax highlight
            if(isCode) {
                if(line.contains("CODE_END")) {
                    line = line.replace("CODE_END", "").trim();
                    isCode = false;
                    code.append(line);
                    
                    Table codeBlock = new Table();

                    codeBlock.setBackground(game.getSkin().getDrawable("field"));
                    //codeBlock.setColor(0.85f, 0.85f, 0.85f, 1);
                    TypingLabel label = new TypingLabel(code.toString(), game.getSkin(),game.getFontFamily().connected[2]); 
                    label.skipToTheEnd();
                    codeBlock.add(label).pad(2);
                    contentTable.add(codeBlock).pad(5).left();
                    contentTable.row();
                } else {
                    code.append(line).append("\n");
                }
            }
            else{
                TypingLabel label = new TypingLabel("[light BLACK]" + line, game.getFontFamily().connected[1]);
                label.setWrap(true);
                label.skipToTheEnd();
                contentTable.add(label).pad(5).fillX().expandX();
                contentTable.row();
            }
        }
    }
}
