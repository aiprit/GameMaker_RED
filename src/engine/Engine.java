package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.DataGame;
import structures.run.*;

public class Engine implements IEngine, IControlListener {
	
	private RunGame myGame;
	private Draw myDraw;
	private Logic myLogic;
	private IGameEventListener myListener;
	
	public Engine(DataGame dataGame, Canvas canvas){
		myGame = dataGameToRunGame(dataGame);
		myDraw = new Draw(canvas);
		myLogic = new Logic(myGame);
	}

	@Override
	public void load(DataGame dataGame) {
		myGame = dataGameToRunGame(dataGame);
	}
	
	@Override
	public void step() {
		myLogic.step();
		myDraw.draw(myGame.getCurrentRoom());
	}

	@Override
	public DataGame save() {
		DataGame currentGameData = myGame.toData();
		return currentGameData;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	private RunGame dataGameToRunGame(DataGame dataGame){
		//change DataGame to RunGame
		return null;
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		myLogic.onKeyEvent(event);
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		myLogic.onMouseEvent(event);
	}

	@Override
	public void registerGameEventListener(IGameEventListener listener) {
		myListener = listener;
	}

}
