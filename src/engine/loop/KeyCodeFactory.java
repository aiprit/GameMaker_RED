package engine.loop;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class KeyCodeFactory {
	private static Map<String, KeyCode> myKeyCodeMap;
	
	public KeyCodeFactory() {
		if (myKeyCodeMap == null) {
			init();
		}
	}
	
	private void init() {
		myKeyCodeMap = new HashMap<>();
	}
}
