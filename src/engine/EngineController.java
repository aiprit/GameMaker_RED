package engine;

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
	private IGameEngineHandler myGameEngineHandler;
	
	public EngineController(Stage stage) throws ResourceFailedException {
		init();
		myGameEngineHandler = new GameEngineHandler();
		myFrontEnd = new FrontEnd(stage, myEngine.getListeners(), myRunningGame, myGameEngineHandler); 
		myReader = new XMLReader();
		myWriter = new XMLWriter();
	}
	
	public void init() throws ResourceFailedException{
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
		if (result.isPresent()){
		    myName = result.get();
		} else {
			//handle this case
			throw new ResourceFailedException("Gamefile missing.");
		}

		//myGame = myReader.read(myName);
		myGame = null;
		myRunningGame = dataGameToRunGame(myGame);
		myEngine = new Engine(myRunningGame);
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		//change DataGame to RunGame
		return null;
	}
}
