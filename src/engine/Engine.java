package engine;

import exceptions.CompileTimeException;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private IGamePlayHandler myListener;
	private RoomLoop myLevel;
	private EventManager myEventManager;

	public Engine(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
		myListener = new GamePlayListener();
		updateLevel();
		myEventManager = new EventManager(myGame.getCurrentRoom(), myListener);
	}
	
	public EventManager getEventManager() {
	    return myEventManager;
	}

	public void load(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
	}

	public DataGame save() throws CompileTimeException {
	    DataGame currentGameData;
            try {
                currentGameData = myGame.toData();
            }
            catch (CompileTimeException e) {
                throw new CompileTimeException(e.getMessage());
            }
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
