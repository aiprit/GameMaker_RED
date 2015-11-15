package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import XML.XMLReader;
import XML.XMLWriter;
import exceptions.CompileTimeException;
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

	public EngineController(Stage stage) {
		initializeGame();
		myRunningGame = dataGameToRunGame(myGame);
		myEngine = new Engine(myRunningGame);
		myGameEngineHandler = new GameEngineHandler(paused, savedGames);
		savedGames = new SavedGameHandler(myGame.getName());
		try {
			myFrontEnd = new FrontEnd(stage, myEngine.getListeners(), myRunningGame, myGameEngineHandler);
		} catch (CompileTimeException e) {
			e.printStackTrace();
		}
		redrawHandler = myFrontEnd.getRedrawHandler();
		myReader = new XMLReader();
		myWriter = new XMLWriter();
		myEngine.setRedrawHandler(redrawHandler);
		myEngine.fireUp();
	}

	public void initializeGame() {
		String myName = "";
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
			// TODO: handle this case
			System.err.println("Gamefile missing.");
		}
		myGame = myReader.read(myName);
	}

	private RunGame dataGameToRunGame(DataGame dataGame) {
		return new RunGame(dataGame);
	}

}
