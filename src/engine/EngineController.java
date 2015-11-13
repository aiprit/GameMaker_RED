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
	private IRedrawHandler redrawHandler;

	public EngineController(Stage stage) throws ResourceFailedException {
		initializeGame();
		myRunningGame = dataGameToRunGame(myGame);
		myEngine = new Engine(myRunningGame);
		myGameEngineHandler = new GameEngineHandler(paused, savedGames);
		savedGames = new SavedGameHandler(myGame.getName());
		myFrontEnd = new FrontEnd(stage, myEngine.getListeners(), myRunningGame, myGameEngineHandler);
		redrawHandler = myFrontEnd.getRedrawHandler();
		myReader = new XMLReader();
		myWriter = new XMLWriter();
		myEngine.setRedrawHandler(redrawHandler);
		myEngine.fireUp();
	}

	public void initializeGame() throws ResourceFailedException {
		String myName;
		List<String> choices = new ArrayList<>();
		choices.add("ExampleGame");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a Game", choices);
		dialog.setTitle("Select a Game");
		dialog.setHeaderText("Select a Game");
		dialog.setContentText("Choose a game:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			myName = result.get();
		} else {
			//TODO: handle this case
			throw new ResourceFailedException("Gamefile missing.");
		}
		myGame = new DataGame(myName);
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		return new RunGame(dataGame);
	}
	
	
}
