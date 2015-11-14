package authoring_environment.room;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class ButtonHandler {
	
	public Button[] create(int n, String[] text) {
		Button[] arr = new Button[n];
		for (int i = 0; i < n; i++) {
			Button button = new Button(text[i]);
			//button.setPadding(new Insets(0, 50, 0, 50));
			arr[i] = button;
		}
		return arr;
	}
}

