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

public class EngineController implements IGameControlEvent {
	private DataGame myGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;
	private XMLReader myReader;
	private XMLWriter myWriter;
	
	public EngineController(Stage stage) throws ResourceFailedException {
		myFrontEnd = new FrontEnd(stage); 
		init();
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

		myGame = myReader.read(myName);
		myEngine = new Engine(myGame);
		myEngine.registerGameEventListener(this);
	}
	
	private void saveGame(String fileName){
		//called from the user interface
		myWriter.write(myEngine.save(), fileName);
	}

	@Override
	public void onNewHighScore(String username, double score) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onSave() {
		//TODO: Timestamp name
		String filename = "asdf";
		saveGame(filename);
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		// Show file explorer here
	}
}
