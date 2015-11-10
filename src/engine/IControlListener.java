package engine;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface IControlListener {
	void onKeyEvent(KeyEvent event);
	void onMouseEvent(MouseEvent event);
}
