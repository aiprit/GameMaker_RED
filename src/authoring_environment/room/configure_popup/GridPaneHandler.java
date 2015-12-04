package authoring_environment.room.configure_popup;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GridPaneHandler {
	private List<TextField> myTextFields;
	public List<TextField>  setUpGridPane(int n, String[] labelStrings, GridPane pane) {
		myTextFields = new ArrayList<TextField>();
		for (int i = 0; i < n; i++) {
			pane.add(new Label(labelStrings[i]), 0, i);
			TextField field = new TextField();
			myTextFields.add(field);
			pane.add(field, 1, i);
		}
		return myTextFields;
	}
}
