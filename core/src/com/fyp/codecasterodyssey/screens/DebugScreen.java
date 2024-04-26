package com.fyp.codecasterodyssey.screens;

import com.fyp.codecasterodyssey.CodecasterOdyssey;
import com.fyp.codecasterodyssey.UI.ReturnButton;
import com.fyp.codecasterodyssey.UI.codeEditor.codeEditor.CodeEditor;

public class DebugScreen extends BaseScreen {


    public DebugScreen(final CodecasterOdyssey codecasterOdyssey) {
        super(codecasterOdyssey);
    }

    @Override
    protected void setupUI() {
        CodeEditor editor = new CodeEditor(game.getSkin());
        editor.setPosition(10, 50);
        editor.setSize(400, 265);
        editor.setText(
                "public class Welcome\n"+
                "{\n" +
                "   public static void main(String args[])\n" +
                "   {\n" +
                "       // prints Hello, world \n" +
                "       System.out.print(\"Hello, world\");\n" +
                "   }\n" +
                "}");
        stage.addActor(editor);

        
        stage.addActor(new ReturnButton(game, true));
    }
    
}
