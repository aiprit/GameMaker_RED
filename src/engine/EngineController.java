package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import XML.XMLReader;
import XML.XMLWriter;
import exceptions.ResourceFailedException;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.run.RunGame;

public class EngineController {
	private DataGame myGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;
	private XMLReader myReader;
	private XMLWriter myWriter;
	private RunGame myRunningGame;
	private IGameEngineHandler myGameEngineHandler;
	private Boolean paused;
	private SavedGameHandler savedGames;

	public EngineController(Stage stage) throws ResourceFailedException {
		init();
		myGameEngineHandler = new GameEngineHandler(paused, savedGames);
		savedGames = new SavedGameHandler(myGame.getName());
		myFrontEnd = new FrontEnd(stage, myEngine.getListeners(), myRunningGame, myGameEngineHandler);
		myReader = new XMLReader();
		myWriter = new XMLWriter();
	}

	public void init() throws ResourceFailedException {
		String myName;
		List<String> choices = new ArrayList<>();
		choices.add("Mario");
		choices.add("Recent Game 2");
		choices.add("Dog");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a Game", choices);
		dialog.setTitle("Select a Game");
		dialog.setHeaderText("Select a Game");
		dialog.setContentText("Choose a game:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			myName = result.get();
		} else {
			// handle this case
			throw new ResourceFailedException("Gamefile missing.");
		}

		// myGame = myReader.read(myName);
		myGame = null;
		myRunningGame = new RunGame(myGame, new Draw(null));
		myEngine = new Engine(myRunningGame);
	}
}
