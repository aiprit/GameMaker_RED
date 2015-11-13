package engine;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.input.InputEvent;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {
	
	private RunGame myGame;
	private EventManager eventManager;
	private IGamePlayListener myListener;
	private Queue<InputEvent> inputs;
	
	public Engine(RunGame runGame){
		eventManager = new EventManager(myGame, inputs);
		inputs = new LinkedList<InputEvent>();
		myListener = new GamePlayListener(inputs);
	}

	public void load(RunGame runGame) {
		myGame = runGame;
	}
	
	public void step() {
		eventManager.loop();
	}

	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	public void reset() {
		
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
