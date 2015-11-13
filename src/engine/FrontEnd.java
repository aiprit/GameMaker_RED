/**
 * 
 */
package engine;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author loganrooper
 */
public class FrontEnd {
	private Canvas myCanvas;
	
	public FrontEnd(Stage stage, IGamePlayListener listener) {
		stage.hide();
		stage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        listener.setOnEvent(mouseEvent);
		    }
		});
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 listener.setOnEvent(event);
			}
		});
	}
	
	private void setupCanvas(){
		myCanvas = new Canvas();
	}
}