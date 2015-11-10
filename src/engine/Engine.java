package engine;

import javafx.scene.canvas.Canvas;
import structures.data.DataGame;
import structures.run.*;

public class Engine implements IEngine {
	
	private RunGame myGame;
	private Draw myDraw;
	private Logic myLogic;
	
	public Engine(DataGame dataGame, Canvas canvas){
		myGame = dataGameToRunGame(dataGame);
		myDraw = new Draw(canvas);
		myLogic = new Logic(myGame);
	}

	@Override
	public void setGame(DataGame dataGame) {
		myGame = dataGameToRunGame(dataGame);
	}

	@Override
	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		//change DataGame to RunGame
		return null;
	}

}
