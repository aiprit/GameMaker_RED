package structures.data.events;

import javafx.scene.input.KeyCode;

public abstract class AKeyEvent implements IDataEvent {
	private KeyCode myCode;
	
	public KeyCode getKeyCode() {
		return myCode;
	}
}
