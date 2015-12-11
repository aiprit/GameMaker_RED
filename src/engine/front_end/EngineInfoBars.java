// This entire file is part of my masterpiece.
// Brenna Milligan

package engine.front_end;

import javafx.scene.layout.VBox;


public class EngineInfoBars extends VBox {

    private HighScoreView myHighScoreView;
    private VariableView myVariableView;

    public EngineInfoBars (HighScoreView highScoreView,
                           VariableView variableView,
                           double prefHeight) {
        super(2);
        myHighScoreView = highScoreView;
        myVariableView = variableView;
        makeHighScoreBar(prefHeight);
        getChildren().add(myVariableView);
    }

    private void makeHighScoreBar (double prefHeight) {
        myHighScoreView.setPrefWidth(150);
        myHighScoreView.setFocusTraversable(false);
        myHighScoreView.setPrefHeight(prefHeight);
        getChildren().add(myHighScoreView);
    }

}
