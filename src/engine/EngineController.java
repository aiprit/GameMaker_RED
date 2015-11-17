package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import XML.XMLEditor;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.run.RunGame;

public class EngineController {
	private DataGame myGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;
	private XMLEditor myEditor;
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
		
		//set myGame to the game that the user chooses
		myEditor = new XMLEditor();
		myGame = myEditor.readXML(myName);
		
		//convert DataGame to a RunGame and pass that to the
		//engine in the constructor
		try {
			myRunningGame = new RunGame(myGame);
		} catch (CompileTimeException | RuntimeException e) {
			e.printStackTrace();
		}
		
		myEngine = new Engine(myRunningGame);
	}
}
