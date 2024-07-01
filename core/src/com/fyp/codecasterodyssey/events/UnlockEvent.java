package com.fyp.codecasterodyssey.events;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fyp.codecasterodyssey.screens.GameScreen;
import com.github.tommyettinger.textra.TypingLabel;

public class UnlockEvent extends Event {

    private boolean isSpell;
    private String itemId;
    private boolean completeSpellTT, completeQuestTT; // tutorials
    
    public UnlockEvent() {}

    @Override
    public void execute() {

        itemId = isSpell ? scene.getSpell() : scene.getQuest();

        gameView.clear();

        TypingLabel label = new TypingLabel("{SIZE=%150}" + itemId + " obtained!", game.getSkin(), game.getFontFamily().connected[0]);
        gameView.add(label);

        // for first-timer
        completeSpellTT = !((GameScreen) game.getScreen()).getSpellTutorial();
        completeQuestTT = !((GameScreen) game.getScreen()).getQuestTutorial();

        if(itemId.equals("spell101") && !completeSpellTT) {
            gameView.row();
            String text = "{SIZE=%75}Congratulations, you have earned your first spell! Click the View Spells button below to look at your collected spells!";
            TypingLabel TutorialLabel = new TypingLabel(text, game.getSkin(), game.getFontFamily().connected[0]);
            TutorialLabel.setWrap(true);
            TutorialLabel.skipToTheEnd();
            gameView.add(TutorialLabel).expandX().fillX().pad(5);
        } else if(itemId.equals("quest101") && !completeQuestTT) {
            gameView.row();
            String text = "{SIZE=%75}Awesome, you have obtained your first quest! Click the View Quest button below to look at your first task!";
            TypingLabel TutorialLabel = new TypingLabel(text, game.getSkin(), game.getFontFamily().connected[0]);
            TutorialLabel.setWrap(true);
            TutorialLabel.skipToTheEnd();
            gameView.add(TutorialLabel).expandX().fillX().pad(5);
        }

        gameView.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!label.isSkipping())
                    label.skipToTheEnd();
                else if(!completeSpellTT && isSpell)
                    return;
                else if(!completeQuestTT && !isSpell)
                    return;
                else 
                    complete();
            }
        });

        if(isSpell) game.getCurrentUser().addSpell(itemId);
        else game.getCurrentUser().setCurrentQuest(itemId);
        ((GameScreen) game.getScreen()).resetButtons();
        
    }

    public boolean getIsSpell() {
        return isSpell;
    }
}
