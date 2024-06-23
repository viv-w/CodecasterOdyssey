package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.Spell;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ProgressBarTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;

public class SpellProgressScreen extends BaseScreen {

    private Table root, scrollTable;
    private ScrollPane scrollPane;
    private BackgroundTable spellTable;

    public SpellProgressScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.top();
        root.setFillParent(true);
        stage.addActor(root);

        spellTable = new ProgressBarTable(game, false, true);
        root.add(spellTable).pad(10);
        root.row();

        if(!game.getCurrentUser().getCollectedSpells().isEmpty()) {

            scrollTable = new Table();
            scrollTable.top();

            for (LearningPath path : game.getAllPaths()) {
                int totalSpellsPP = path.getSpells().size();
                int userSpellsPP = 0;

                for (String spellId : game.getCurrentUser().getCollectedSpells()) {
                    for(Spell spell: path.getSpells()) {
                        if(spellId.contains(spell.getId()))
                            userSpellsPP++;
                    }
                }

                float spellPercentage = ((float) userSpellsPP / totalSpellsPP) * 100;

                BackgroundTable spellPPTable = new BackgroundTable();
                spellPPTable.setBackgroundColour(1, 1, 1, 0.2f);

                Label spellLabel = new Label(" Path " + path.getNum(), game.getSkin());
                ProgressBar spellBar = new ProgressBar(0, 100, 1, false, game.getSkin());
                Label spellPercentageLabel = new Label(Math.round(spellPercentage) + "%", game.getSkin());
                spellBar.setValue(Math.round(spellPercentage));
                spellPPTable.add(spellLabel).pad(2).left();
                spellPPTable.add(spellBar).pad(2);
                spellPPTable.add(spellPercentageLabel).pad(2).right();
                scrollTable.add(spellPPTable).minWidth(300).minHeight(50).pad(5);
                scrollTable.row();
            }

            scrollPane = new ScrollPane(scrollTable, game.getSkin());
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);
            scrollPane.setScrollingDisabled(true, false);
            scrollPane.validate();
            root.add(scrollPane).prefHeight(200).minWidth(300).pad(5);
            root.row();

        } else {
            Label noSpell = new Label("no spells collected", game.getSkin());
            root.add(noSpell).minHeight(250);
        }

        stage.addActor(new ReturnButton(game, stage, "home"));

    }
}
