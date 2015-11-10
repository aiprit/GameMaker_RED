package engine;

import XML.*;
import javafx.scene.canvas.Canvas;
import structures.data.DataGame;

public class Player implements IPlayer {
	
	private Canvas myCanvas;
	private DataGame myGame;
	private Engine myEngine;
	
	private XMLReader myReader;
	private XMLWriter myWriter;
	
	public Player(String gameName){
		//BorderPane myView = new BorderPane();
		init(gameName);
		myReader = new XMLReader();
		myWriter = new XMLWriter();
	}
	
	@Override
	public void init(String myName){
		myGame = readXML(myName);
		setupCanvas();
		myEngine = new Engine(myGame, myCanvas);
	}

	private DataGame readXML(String myName) {
		//read in file at String from XML reader
		DataGame game = myReader.read(myName);
		return game;
	}
	
	private void setupCanvas(){
		//add info from DataGame to Canvas
		myCanvas = new Canvas();
	}
	
	private void saveGame(String fileName){
		//called from the user interface
		myWriter.write(myEngine.save(), fileName);
	}

}
