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
		init(gameName);
	}
	
	@Override
	public void init(String myName){
		myGame = readXML(myName);
		setupCanvas();
		myEngine = new Engine(myGame, myCanvas);
		
		
		
		
		
	}

	private DataGame readXML(String myName) {
		//read in file at String from XML reader
<<<<<<< HEAD
		XMLReader myReader = new XMLReader();
		myGame = null;
		setupCanvas();
		return myGame;
=======
		DataGame game = null;
		return game;
>>>>>>> 5644db48da7e09e02296a6096d40c947f1b16dce
	}
	
	private void setupCanvas(){
		//add info from DataGame to Canvas
		myCanvas = new Canvas();
	}

}
