package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import XML.XMLEditor;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import structures.TestGame2;
import structures.TestGameObject;
import structures.data.DataGame;
import structures.run.RunGame;
import structures.run.RunObject;
import utils.GameSelector;

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
	private GroovyLibrary myGroovyLibrary;

	public EngineController(Stage stage) throws ResourceFailedException {
		init();
		
		paused = false;
		//savedGames = new SavedGameHandler(myGame.getName());
		myGUIHandler = new GUIHandler(paused, savedGames);
		//myPlayingHandler = new GamePlayListener(new LinkedList<InputEvent>());
		
		myFrontEnd = new FrontEnd(stage, myGUIHandler, myEngine.getListener(), myRunningGame);
		//starts the first room loop
		myEngine.setDrawListener(myFrontEnd.getDrawListener());
		myGroovyLibrary = new GroovyLibrary(myRunningGame);
		myGroovyLibrary.setRoomChangedHandler(myFrontEnd);
	}

	public void init() throws ResourceFailedException {
		myGame = GameSelector.getGameChoice();
		
		//convert DataGame to a RunGame and pass that to the
		//engine in the constructor
		try {
			myRunningGame = new RunGame(myGame);
		} catch (CompileTimeException | RuntimeException e) {
			e.printStackTrace();
		}
		
		//myRunningGame.getCurrentRoom().getObjects();
		myEngine = new Engine(myRunningGame);
	}
}
