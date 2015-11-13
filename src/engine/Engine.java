package engine;

import java.util.Queue;

import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {
	
	private RunGame myGame;
	private Draw myDraw;
	private IGamePlayListener myListener;
	private EventManager eventManager;
	private Queue<InputEvent> inputs;
	
	public Engine(DataGame dataGame){
		myGame = dataGameToRunGame(dataGame);
		eventManager = new EventManager(myGame, inputs);
		myListener = new GamePlayListener(inputs);
	}

	public void load(DataGame dataGame) {
		myGame = dataGameToRunGame(dataGame);
	}
	
	public void step() {
		eventManager.loop();
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

	public IGamePlayListener getListeners() {
		return myListener;
	}

//	@Override
//	public void registerGameEventListener(IGameEventListener listener) {
//		myListener = listener;
//		
//		myListener.() = 
//	}

}
