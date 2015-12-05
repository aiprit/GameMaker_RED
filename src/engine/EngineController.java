package engine;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import XML.XMLEditor;
import engine.events.EventManager;
import engine.events.IGUIControllerHandler;
import engine.events.IInputHandler;
import engine.front_end.FrontEnd;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.run.RunGame;
import structures.run.RunObject;
import utils.Point;

public class EngineController implements IGUIControllerHandler, IInputHandler {

	private XMLEditor myEditor;
	private DataGame myGame;
	private RunGame currentRunGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;
	private String myCurrentGame;
	private boolean debugActivated;

	public EngineController(Stage stage) throws ResourceFailedException {
		EventManager eventManager = new EventManager();
		debugActivated = false;
		myCurrentGame = getUserChoice();
		currentRunGame = readObject();
		try {
			myFrontEnd = new FrontEnd(eventManager, stage, myCurrentGame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//starts the first room loop
		myEngine = new Engine(currentRunGame, eventManager);
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

	public String getGamesDirectory() {
		String path = getClass().getResource("/dummy.file").getPath().replace("dummy.file", "");
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}

	public RunGame readObject() throws ResourceFailedException{

		//set myGame to the game that the user chooses
		//System.out.println(userGame);
		myEditor = new XMLEditor();
		myGame = myEditor.readXML("Games/" + myCurrentGame + "/XML/GameFile.xml/");

		//convert DataGame to a RunGame
		RunGame runGame = null;
		try {
			runGame = new RunGame(myGame);
		} catch (CompileTimeException | RuntimeException e) {
			e.printStackTrace();
		}

		return runGame;

	}

	public void setupEventManager(EventManager eventManager){
		eventManager.addGUIBackendInterface(myEngine.getGUIBackendHandler());
		eventManager.addGUIControllerInterface(this);
		eventManager.addObjectModifiedInterface(myEngine.getObjectHandler());
		eventManager.addFrontEndUpdateInterface(myFrontEnd.getFrontEndUpdateHandler());
		eventManager.addRoomUpdateInterface(myFrontEnd.getRoomUpdateHandler());
		eventManager.addRoomUpdateInterface(myEngine.getRoomUpdateHandler());
		eventManager.addUserInputInterface(this);
	}

	@Override
	public void onReset() throws ResourceFailedException {
		myEngine.pause();
		currentRunGame = readObject();
		myEngine.changeGame(currentRunGame);
	}

	@Override
	public void onChangeGame(String game) throws ResourceFailedException {
		myCurrentGame = game;
		onReset();
	}

	@Override
	public void onLoadSave(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSave() {
		//TODO: move save to the controller
		DataGame currentGameData;
		try {
			currentGameData = currentRunGame.toData();
		}
		catch (CompileTimeException e) {
		}
	}

	@Override
	public void onMouseEvent (MouseEvent event) {
		if(debugActivated){
			if (event.isControlDown()) {
				double x = event.getX();
				double y = event.getY();
				RunObject obj = myEngine.getObjectClicked(new Point(x, y));
				if (obj == null) return;
				myFrontEnd.makeObjectInformationBar(obj);
			}
		}
	}

	@Override
	public void onKeyEvent (KeyEvent event) {
		// Do nothing (for now)
	}
	
	@Override
	public void setDebug(boolean value){
		debugActivated = value;
	}
	
}
