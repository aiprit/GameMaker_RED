package engine;

import XML.*;
import javafx.scene.canvas.Canvas;
import structures.data.DataGame;

public class Player implements IPlayer {
	
	private Canvas myCanvas;
	private DataGame myGame;
	private Engine myEngine;
	
	public Player(String gameName){
		//create front end of game play
		myCanvas = new Canvas();
		init(gameName);
	}
	
	private void init(String myName){
		readXML(myName);
		myEngine = new Engine(myGame, myCanvas);
	}

	@Override
	public DataGame readXML(String myName) {
		//read in file at String from XML reader
		XMLReader myReader = new XMLReader();
		myGame = null;
		setupCanvas();
		return myGame;
	}
	
	private void setupCanvas(){
		//add info from DataGame to Canvas
	}

}
