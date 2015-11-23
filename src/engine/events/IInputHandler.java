package engine.events;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface IInputHandler {
	void onMouseEvent(MouseEvent event);
	void onKeyEvent(KeyEvent event);
}