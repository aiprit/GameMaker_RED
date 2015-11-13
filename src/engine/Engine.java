package engine;

import java.util.Queue;
import javafx.scene.input.InputEvent;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {
	
	private RunGame myOriginalGame;
        private RunGame myGame;
	private EventManager eventManager;
	private IGamePlayListener myListener;
	private Queue<InputEvent> inputs;
	
	public Engine(RunGame runGame){
	        myGame = runGame;
	        myOriginalGame = myGame;
		eventManager = new EventManager(myGame, inputs);
		myListener = new GamePlayListener(inputs);
	}

	public void load(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
		eventManager.load(myGame);
	}
	
	public void step() {
		eventManager.loop();
	}

	public DataGame save() {
		return myGame.toData();
	}

	public void reset() {
		myGame = myOriginalGame;
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
