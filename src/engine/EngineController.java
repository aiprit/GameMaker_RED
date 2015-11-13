package engine;

import XML.XMLReader;
import XML.XMLWriter;
import javafx.stage.Stage;
import structures.data.DataGame;

public class EngineController implements IGameEventListener {
	private DataGame myGame;
	private Engine myEngine;
	private FrontEnd myFrontEnd;
	private XMLReader myReader;
	private XMLWriter myWriter;
	
	public EngineController(Stage stage) {
		//BorderPane myView = new BorderPane();
		init();
		myReader = new XMLReader();
		myWriter = new XMLWriter();
		myFrontEnd = new FrontEnd(stage); 
	}
	
	public void init(){
		//TODO: Show a popup to get the game they want to play
		String myName = "";
		
		myGame = readXML(myName);
		myEngine = new Engine(myGame);
		myEngine.registerGameEventListener(this);
	}

	private DataGame readXML(String myName) {
		//read in file at String from XML reader
		DataGame game = myReader.read(myName);
		return game;
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
		// Show file explorer here
		String filename = "asdf";
		saveGame(filename);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub	
	}

}
