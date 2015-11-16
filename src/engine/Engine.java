package engine;

import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private IGamePlayHandler myListener;
	private RoomLoop myLevel;

	public Engine(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
		myListener = new GamePlayListener();
		updateLevel();
	}

	public void load(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
	}

	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	public void reset() {
		myGame = myOriginalGame;
	}
	
	public void updateLevel(){
		myLevel = new RoomLoop(myGame.getCurrentRoom(), myListener);
		myLevel.start();
	}
	
	public IGamePlayHandler getListener(){
		return myListener;
	}

}
