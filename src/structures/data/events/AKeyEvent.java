package structures.data.events;

import javafx.scene.input.KeyCode;

public abstract class AKeyEvent implements IDataEvent {
	private KeyCode myCode;

	public AKeyEvent(KeyCode code){
		myCode = code;
	}

	public KeyCode getKeyCode() {
		return myCode;
	}
}
