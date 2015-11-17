package authoring_environment.room;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HBoxHandler {
	
	public List<HBox> createHBoxes(int n, String[] labelStrings) {
		List<HBox> hBoxList = new ArrayList<HBox>();
		for (int i = 0; i < n; i++) {
			HBox box = new HBox();
			box.getChildren().add(new Label(labelStrings[i]));
			box.getChildren().add(new TextField());
			hBoxList.add(box);
		}
		return hBoxList;
	}
}
