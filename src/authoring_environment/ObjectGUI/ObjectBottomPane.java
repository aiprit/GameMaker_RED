package authoring_environment.ObjectGUI;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import structures.IObject;
import structures.data.DataObject;

public class ObjectBottomPane {
	private String name;
	private double[] size;
	private IObject o;
	public ObjectBottomPane(IObject object) {
		try {
			o = object;
			name = o.getName();
			size = o.getSize();
		}
		catch (NullPointerException e) {
			name = "";
			size = new double[] {50.0, 50.0};
		}
	}

	public Group init() {
		Group root = new Group();
		TextArea nameBox = new TextArea(name);
		TextArea width = new TextArea(String.valueOf(size[0]));
		width.setTranslateX(100);
		TextArea height = new TextArea(String.valueOf(size[1]));
		height.setTranslateX(200);
		Button b = new Button("Update");		
		b.setOnAction(e -> 
				update(nameBox.getText(), Double.parseDouble(width.getText()), Double.parseDouble(height.getText())));
		root.getChildren().addAll(nameBox, width, height, b);
		return root;
	}
	
	private void update(String name, double width, double height) {
		o.setSize(width, height);
		o.setName(name);
	}
}
