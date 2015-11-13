package engine;

import java.util.Queue;

import javafx.scene.input.InputEvent;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {
	
	private RunGame myGame;
	private Draw myDraw;
	private EventManager eventManager;
	private IGamePlayListener myListener;
	private Queue<InputEvent> inputs;
	
	public Engine(DataGame dataGame){
		myGame = dataGameToRunGame(dataGame);
		eventManager = new EventManager(myGame);
		myListener = new GamePlayListener(inputs);
	}

	public void load(DataGame dataGame) {
		myGame = dataGameToRunGame(dataGame);
	}
	
	public void step() {
		eventManager.step();
		myDraw.draw(myGame.getCurrentRoom());
	}

	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	public void reset() {
		
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		//change DataGame to RunGame
		return null;
	}

//	@Override
//	public void registerGameEventListener(IGameEventListener listener) {
//		myListener = listener;
//		
//		myListener.() = 
//	}

}
