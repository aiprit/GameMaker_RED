/**
 * 
 */
package engine;

import exceptions.CompileTimeException;
import structures.run.RunGame;

/**
 * @author loganrooper
 *
 */
public class RedrawHandler implements IRedrawHandler {
	Draw draw;
	private RunGame myGame;
	
	public RedrawHandler(RunGame myGame) {
		this.myGame = myGame;
	}
	
	@Override
	public void redraw() {
		System.out.println("Redrawing...");
	}

	@Override
	public void setDrawer(Draw draw) {
		this.draw = draw;
	}
	
	public void draw() throws CompileTimeException {
		draw.draw(getMyGame());
		
		//TODO:
	}

	public RunGame getMyGame() {
		return myGame;
	}
}
