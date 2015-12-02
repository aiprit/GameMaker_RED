package engine;

import engine.events.EventManager;
import engine.events.IGUIBackendHandler;
import engine.events.IGameUpdatedHandler;
import engine.events.IObjectModifiedHandler;
import engine.front_end.IDraw;
import engine.loop.RoomLoop;
import engine.loop.groovy.GroovyEngine;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Point;

/**
 * Runs a game when given a RunGame. Does all the logic, 
 * including running the event loop and executing user
 * Groovy code, but is still separate from the user
 * interface and most GUI components.
 *
 */
public class Engine implements IGameUpdatedHandler {

	private RunGame myGame;
	private RoomLoop myLevel;
	private EventManager myEventManager;
	private IGUIBackendHandler myGUIHandler;
	private IObjectModifiedHandler myObjectHandler;
	private IDraw myDrawListener;
	private GroovyEngine myGroovyEngine;

	public Engine(RunGame runGame, EventManager eventManager) {
		myGame = runGame;
		myGUIHandler = new GUIBackendListener(this, false);
		myEventManager = eventManager;
		myGroovyEngine = new GroovyEngine(myGame, eventManager);
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
	
	public RunObject getObjectClicked(Point p) {
	    RunObject runObject = null;
	    for (RunObject obj : myGame.getCurrentRoom().getObjects()) {
	        if (obj.getBounds().contains(p)) {
	            runObject = obj;
	        }
	    }
	    return runObject;
	}

	public IGUIBackendHandler getGUIBackendHandler(){
		return myGUIHandler;
	}

	public IGameUpdatedHandler getFrontEndUpdateHandler(){
		return this;
	}

	public IObjectModifiedHandler getObjectHandler(){
		return myObjectHandler;
	}

	public void pause() {
		myLevel.pause();
	}
	
	public void resume(){
		myLevel.resume();
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
