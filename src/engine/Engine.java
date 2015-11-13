package engine;

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
		eventManager = new EventManager(myGame);
		myListener = new GamePlayListener(inputs);
	}

	public void load(RunGame runGame) {
		myGame = runGame;
	}
	
	public void step() {
		eventManager.step();
	}

	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	public void reset() {
		
	}

	/**
	 * @return
	 */
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
