package engine;

import engine.events.EventManager;
import engine.events.IFrontEndUpdateHandler;
import engine.events.IGUIHandler;
import engine.events.IGamePlayHandler;
import engine.events.IObjectModifiedHandler;
import engine.front_end.IDraw;
import engine.loop.RoomLoop;
import engine.loop.groovy.GroovyEngine;
import exceptions.CompileTimeException;
import structures.data.DataGame;
import structures.run.RunGame;
import structures.run.RunRoom;

/**
 * Runs a game when given a RunGame. Does all the logic, 
 * including running the event loop and executing user
 * Groovy code, but is still separate from the user
 * interface and most GUI components.
 *
 */
public class Engine implements IFrontEndUpdateHandler {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private RoomLoop myLevel;
	private EventManager myEventManager;
	private IGUIHandler myGUIHandler;
	private IGamePlayHandler myGameplayHandler;
	private IObjectModifiedHandler myObjectHandler;
	private IDraw myDrawListener;
	private GroovyEngine myGroovyEngine;

	public Engine(RunGame runGame, EventManager eventManager) {
		myGame = runGame;
		//SavedGameHandler thisIsBroken = new SavedGameHandler(myGame.getName());
		myGUIHandler = new GUIListener(this, false, null);
		myGameplayHandler = new GamePlayListener();
		myOriginalGame = runGame;
		myEventManager = eventManager;
		myGroovyEngine = new GroovyEngine(myGame, eventManager);
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
		System.out.println(myGame.getCurrentRoomNumber());
		myLevel = new RoomLoop(myGame.getCurrentRoom(), myEventManager, myDrawListener, myGroovyEngine);
		myObjectHandler = myLevel.getObjectHandler();
		//have to do this to change the object tracker when the room changes
		myEventManager.clearObjectModifiedInterface();
		myEventManager.addObjectModifiedInterface(myObjectHandler);
		myLevel.start();
	}
	
	public IGUIHandler getGUIHandler(){
		return myGUIHandler;
	}
	
	public IGamePlayHandler getGamePlayHandler(){
		return myGameplayHandler;
	}
	
	public IFrontEndUpdateHandler getFrontEndUpdateHandler(){
		return this;
	}
	
	public IObjectModifiedHandler getObjectHandler(){
		return myObjectHandler;
	}

	public void pause() {
		myLevel.pause();
	}
	
	public void setDrawListener(IDraw drawListener){
		myDrawListener = drawListener;
		runLevel();
	}

	@Override
	public void onRoomChanged(RunRoom runRoom) {
		//how else need to change the level?
		myLevel.cancel();
		runLevel();
	}

	@Override
	public void setHighScore(double highScore) {
		myGame.setHighScore(highScore);
	}

}
