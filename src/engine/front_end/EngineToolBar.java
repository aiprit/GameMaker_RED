// This entire file is part of my masterpiece.
// Brenna Milligan

package engine.front_end;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import engine.events.EventManager;
import exceptions.ResourceFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class EngineToolBar extends HBox {

    private static final String CHANGE_GAME = "Change game";
    private static final String _10 = "10";
    private static final String OPEN_IMAGE = "open.png";
    private static final String SAVE_IMAGE = "save.png";
    private static final String RESET_IMAGE = "reset.png";
    private static final String PAUSE_IMAGE = "pause.png";
    private static final String PLAY_IMAGE = "play.png";
    private static final String DEFAULT_GAMES_PACKAGE = "Games/";
    private static final String DEFAULT_IMAGE_PACKAGE = "resources/";

    private EventManager myEventManager;
    private HighScoreView myHighScoreView;

    public EngineToolBar (EventManager eventManager, HighScoreView highScoreView) {
        super(8);
        setAlignment(Pos.CENTER);
        myEventManager = eventManager;
        myHighScoreView = highScoreView;
        getChildren().addAll(makeToolBar(), makeVBox());
    }

    public ToolBar makeToolBar () {
        Button playButton = new Button();
        playButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + PLAY_IMAGE));
        playButton.setOnMouseClicked(e -> {
            myEventManager.onResume();
        });

        Button pauseButton = new Button();
        pauseButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + PAUSE_IMAGE));
        pauseButton.setOnMouseClicked(e -> {
            myEventManager.onPause();
        });

        Button resetButton = new Button();
        resetButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + RESET_IMAGE));
        resetButton.setOnMouseClicked(e -> {
            try {
                myEventManager.onReset();
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        Button saveButton = new Button();
        saveButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + SAVE_IMAGE));
        saveButton.setOnMouseClicked(e -> {
            myEventManager.onSave();
        });

        Button openButton = new Button();
        openButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + OPEN_IMAGE));
        openButton.setOnMouseClicked(e -> {
            myEventManager.onLoadSave(_10);
        });

        return new ToolBar(playButton, pauseButton, resetButton, saveButton, openButton);
    }

    public VBox makeVBox () {
        VBox change = new VBox(2);
        Text changeTitle = new Text(CHANGE_GAME);
        ChoiceBox<String> cb =
                new ChoiceBox<String>();
        cb.setFocusTraversable(false);
        cb.getItems().addAll(

        addGamesFromDirectory());
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed (ObservableValue<? extends String> source,
                                 String oldValue,
                                 String newValue) {
                onGameChange(cb.getValue());
            }
        });
        change.getChildren().addAll(changeTitle, cb);
        return change;
    }

    private void onGameChange (String name) {
        try {
            myHighScoreView.setGame(name);
            myEventManager.onChangeGame(name);
        }
        catch (ResourceFailedException e) {
            e.printStackTrace();
        }
    }

    private List<String> addGamesFromDirectory () {
        List<String> choices = new ArrayList<String>();
        for (final File fileEntry : new File(DEFAULT_GAMES_PACKAGE).listFiles()) {
            choices.add(fileEntry.getName());
        }
        return choices;
    }

}
