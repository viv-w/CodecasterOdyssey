package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.Spell;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class SpellScreen extends BaseScreen {

    private Table root, scrollTable;
    private ScrollPane scrollPane;
    private Label spellLabel, contentLabel;

    public SpellScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // TODO use SelectBox to pick spells ELSE label "no spells collected"
        Spell spell = game.getAllPaths().get(0).getSpells().get(1);

        spellLabel = new Label(spell.getId(), game.getSkin());
        root.add(spellLabel).pad(5);
        root.row();

        scrollTable = new Table();
        StringBuilder contentBuilder = new StringBuilder();
        for(String line : spell.getContent()) {
            contentBuilder.append(personalizeContent(line)).append("\n");
        }

        contentLabel = new Label(contentBuilder.toString(), game.getSkin());
        contentLabel.setWrap(true);
        scrollTable.add(contentLabel).minWidth(400).pad(5);
        scrollTable.row();

        scrollPane = new ScrollPane(scrollTable, game.getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.validate();
        root.add(scrollPane).prefHeight(300).minWidth(300).pad(5);
        root.row();

        stage.addActor(new ReturnButton(game, "game"));
    }
    
    private String personalizeContent(String line) {
        String personalizeLine = line;

        if(line.contains("<username>"))
            personalizeLine = personalizeLine.replace("<username>", game.getCurrentUser().getUsername());

        return personalizeLine;
    }
}
