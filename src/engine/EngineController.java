package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import XML.XMLEditor;
import engine.events.EventManager;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import structures.TestGame2;
import structures.TestGameObject;
import structures.data.DataGame;
import structures.run.RunGame;

public class EngineController {
	
	private XMLEditor myEditor;
	private DataGame myGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;

	public EngineController(Stage stage) throws ResourceFailedException {
		
		EventManager eventManager = new EventManager();
		String gameChoice = getUserChoice();
		RunGame runGame = readObject(gameChoice, eventManager);
                myFrontEnd = new FrontEnd(runGame, eventManager, stage);
		//starts the first room loop
                myEngine = new Engine(runGame, eventManager);
		myEngine.setDrawListener(myFrontEnd.getDrawListener());
		
		//sets up the event manager
		setupEventManager(eventManager);
	}

	public String getUserChoice() throws ResourceFailedException {
		List<String> choices = addGamesFromDirectory();
		
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a Game", choices);
		dialog.setTitle("Select a Game");
		dialog.setHeaderText("Select a Game");
		dialog.setContentText("Choose a game:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceFailedException("Gamefile missing.");
		}
	}
	
	private List<String> addGamesFromDirectory() {
		List<String> choices =  new ArrayList<String>();
		for (final File fileEntry : new File("Games/").listFiles()) {
			choices.add(fileEntry.getName());
		}
		return choices;
	}
	
	public RunGame readObject(String userGame, EventManager eventManager) throws ResourceFailedException{
		
		//set myGame to the game that the user chooses
		myEditor = new XMLEditor();
		myGame = myEditor.readXML(userGame);
		//use for testing
		//TestGame2 tgo = new TestGame2();
		//myGame = tgo.getTestGame();
		
		TestGameObject tgo2 = new TestGameObject();
		
		DataGame game2 = tgo2.getTestGame();
		RunGame game = null;
		//convert DataGame to a RunGame
		try {
			game = new RunGame(game2);
		} catch (CompileTimeException | RuntimeException e) {
			e.printStackTrace();
		}
		
		return game;
		
	}
	
	public void setupEventManager(EventManager eventManager){
		eventManager.addGUIInterface(myEngine.getGUIHandler());
		eventManager.addRoomChangedInterface(myFrontEnd.getRoomChangedHandler());
		eventManager.addRoomChangedInterface(myEngine.getRoomChangedHandler());
		eventManager.addUserInputInterface(myEngine.getGamePlayHandler());
		eventManager.addObjectModifiedInterface(myEngine.getObjectHandler());
	}
}
