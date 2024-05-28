package com.fyp.codecasterodyssey.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.LearningPath;
import com.fyp.codecasterodyssey.UI.BackgroundTable;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.github.tommyettinger.textra.TypingLabel;

public class SpellProgressScreen extends BaseScreen {

    private Table root;
    private BackgroundTable spellTable;
    private Label spellLabel;
    private ProgressBar spellProgress;

    public SpellProgressScreen(CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        spellTable = new BackgroundTable();
        spellTable.setBackgroundColour(1, 1, 1, 0.2f);
        spellLabel = new Label("Spell Collection", game.getSkin());
        spellTable.add(spellLabel).pad(2);
        spellTable.row();
        spellProgress = new ProgressBar(0, 100, 1, false, game.getSkin());
        spellProgress.setValue(0);
        spellTable.add(spellProgress).pad(5);
        root.add(spellTable).top();
        root.row();

        if(!game.getCurrentUser().getCollectedSpells().isEmpty()) {
            float totalSpells = 0;
            float collectedSpells = 0;
            for (LearningPath path : game.getAllPaths()) {
                if (!path.getSpells().isEmpty()) {
                    totalSpells += path.getSpells().size();
                }
            }
            if (!game.getCurrentUser().getCollectedSpells().isEmpty())
                collectedSpells = (game.getCurrentUser().getCollectedSpells().size() / totalSpells) * 100;
            spellProgress.setValue(collectedSpells);
        } else {
            TypingLabel noSpell = new TypingLabel("no spells collected", game.getSkin());
            noSpell.skipToTheEnd();
            root.add(noSpell).minHeight(250);
        }

        stage.addActor(new ReturnButton(game, stage, "home"));

    }
}
