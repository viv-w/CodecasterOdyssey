package com.fyp.codecasterodyssey.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.CodecasterOdyssey.ScreenType;
import com.fyp.codecasterodyssey.User;

public class ProgressBarTable extends BackgroundTable {
    
    private Label titleTable;
    private String title;
    private ProgressBar progressBar;
    private Label percentageLabel;

    public ProgressBarTable(final CodecasterOdyssey game, boolean isTouchable, boolean isSpell) {
        super();
        this.setBackgroundColour(1, 1, 1, 0.2f);
        this.setTouchable(isTouchable ? Touchable.enabled : Touchable.disabled);
        if(isTouchable) {
            this.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(isSpell) 
                        game.changeScreen(ScreenType.SPELL_PROGRESS);
                    else
                        game.changeScreen(ScreenType.QUEST_PROGRESS);
                }
            });
        }

        title = isSpell ? "Spell Collection" : "Quest Completion";
        titleTable = new Label(title, game.getSkin());
        this.add(titleTable).padTop(5).colspan(2);
        this.row();

        User user = game.getCurrentUser();
        int percentage = isSpell ? user.getSpellPercentage(game.getAllSpells().size()) : user.getQuestPercentage(game.getAllQuests().size());
        
        progressBar = new ProgressBar(0, 100, 1, false, game.getSkin());
        progressBar.setValue(percentage);
        this.add(progressBar).pad(5);

        percentageLabel = new Label(percentage + "%", game.getSkin());
        this.add(percentageLabel).pad(5);
    }
}