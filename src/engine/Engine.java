package engine;

import exceptions.CompileTimeException;
import structures.data.DataGame;
import structures.run.RunGame;
import structures.run.RunRoom;

public class Engine implements IRoomChangedHandler {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private IGamePlayHandler myListener;
	private RoomLoop myLevel;
	private EventManager myEventManager;
	private IGUIHandler myGUIHandler;

	public Engine(RunGame runGame, EventManager eventManager) {
		myGame = runGame;
		myGUIHandler = new GUIHandler(this, false, new SavedGameHandler(myGame.getName()));
		myOriginalGame = runGame;
		myEventManager = eventManager;
		myListener = new GamePlayListener();
		runLevel();
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

	//called when the drawing listener is passed to the engine
	public void reset() {
		myGame = myOriginalGame;
	}
	
	public void runLevel(){
		myLevel = new RoomLoop(myGame.getCurrentRoom(), myListener, myEventManager);
		myLevel.start();
	}
	
	public IGUIHandler getGUIHandler(){
		return myGUIHandler;
	}
	
	public IRoomChangedHandler getRoomChangedHandler(){
		return this;
	}
	
	public IGamePlayHandler getListener(){
		return myListener;
	}

	public void pause() {
		myLevel.pause();
	}

	@Override
	public void onRoomChanged(RunRoom runRoom) {
		// TODO Auto-generated method stub
	}

}
