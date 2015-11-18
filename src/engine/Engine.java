package engine;

import exceptions.CompileTimeException;
import structures.data.DataGame;
import structures.run.RunGame;

public class Engine {

	private RunGame myOriginalGame;
	private RunGame myGame;
	private IGamePlayHandler myListener;
	private RoomLoop myLevel;
	private IDraw myFrontendListener;

	public Engine(RunGame runGame) {
		myGame = runGame;
		myOriginalGame = runGame;
		myListener = new GamePlayListener();
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
		myLevel = new RoomLoop(myGame.getCurrentRoom(), myListener, myFrontendListener);
		myLevel.start();
	}
	
	public IGamePlayHandler getListener(){
		return myListener;
	}
	
	public void setDrawListener(IDraw drawListener){
		//starts the first game loop
		myFrontendListener = drawListener;
		runLevel();
	}

}
