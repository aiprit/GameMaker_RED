package engine;

import structures.data.DataGame;
import structures.run.*;

public class Engine implements IEngine {
	
	private RunGame myGame;
	private Draw myDraw;
	private EventManager eventManager;
	private IGameEventListener myListener;
	
	public Engine(DataGame dataGame){
		myGame = dataGameToRunGame(dataGame);
		eventManager = new EventManager(myGame);
	}

	@Override
	public void load(DataGame dataGame) {
		myGame = dataGameToRunGame(dataGame);
	}
	
	@Override
	public void step() {
		eventManager.step();
		myDraw.draw(myGame.getCurrentRoom());
	}

	@Override
	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	@Override
	public void reset() {
		
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		//change DataGame to RunGame
		return null;
	}

	@Override
	public void registerGameEventListener(IGameEventListener listener) {
		myListener = listener;
	}

}
