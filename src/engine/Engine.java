package engine;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javafx.scene.input.InputEvent;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private EventManager eventManager;
	private IGamePlayHandler myListener;
	private Queue<InputEvent> inputs;
	private Map<String, Double> variables;

	public Engine(RunGame runGame) {
		inputs = new LinkedList<InputEvent>();
		myGame = runGame;
		myOriginalGame = (RunGame) runGame.clone();
		eventManager = new EventManager(myGame, inputs);
		myListener = new GamePlayHandler(inputs);
	}

	public void load(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame.clone();
	}

	public void step() {
		eventManager.loop();
	}

	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	public void reset() {
		myGame = myOriginalGame;
	}

	public IGamePlayHandler getListeners() {
		return myListener;
	}

	// @Override
	// public void registerGameEventListener(IGameEventListener listener) {
	// myListener = listener;
	//
	// myListener.() =
	// }

}
