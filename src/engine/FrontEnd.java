/**
 * 
 */
package engine;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * @author loganrooper
 */
public class FrontEnd {
	private Canvas myCanvas;
	
	public FrontEnd(Stage stage) {
		stage.hide();
	}
	
	private void setupCanvas(){
		//add info from DataGame to Canvas
		myCanvas = new Canvas();
	}
	
}