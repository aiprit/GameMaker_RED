package engine;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface IControlListener {
	public void onKeyEvent(KeyEvent event);
	public void onMouseEvent(MouseEvent event);
}
