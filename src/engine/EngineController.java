package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
	private IGUIHandler myGUIHandler;
	private IGamePlayHandler myPlayingHandler;
	private Boolean paused;
	private SavedGameHandler savedGames;

	public EngineController(Stage stage) throws ResourceFailedException {
		init();
		
		paused = false;
		//savedGames = new SavedGameHandler(myGame.getName());
		myGUIHandler = new GUIHandler(paused, savedGames);
		//myPlayingHandler = new GamePlayListener(new LinkedList<InputEvent>());
		
		myFrontEnd = new FrontEnd(stage, myGUIHandler, myEngine.getListener(), myRunningGame);
	}

	public void init() throws ResourceFailedException {
		String myName;
		List<String> choices = new ArrayList<>();
		addGamesFromDirectory(choices);

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

		myReader = new XMLReader();
		myWriter = new XMLWriter();
		
		// myGame = myReader.read(myName);
		myGame = null;
		
		//convert DataGame to a RunGame and pass that to the
		//engine in the constructor
		
//		try {
//			myRunningGame = new RunGame(myGame);
//		} catch (CompileTimeException | RuntimeException e) {
//			e.printStackTrace();
//		}
		
		myRunningGame = null;
		
		myEngine = new Engine(myRunningGame);
	}

	private void addGamesFromDirectory(List<String> choices) {
		for (final File fileEntry : new File("Games/").listFiles()) {
			choices.add(fileEntry.getName());
		}
	}
}
