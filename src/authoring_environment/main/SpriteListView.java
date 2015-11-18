package authoring_environment.main;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import structures.data.DataSprite;

public class SpriteListView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private ArrayList<HBox> list;

	public void init() {
		list = new ArrayList<HBox>();

		HBox header = new HBox();
		Label headerLabel = new Label("Sprite");
		header.getChildren().addAll(headerLabel);
		list.add(header);
		
//		ListView<HBox> listView = new ListView<HBox>();
//		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
//		listView.setItems(myObservableList);
//		window.getChildren().add(listView);
	}

	public Button addSprite(DataSprite o) {
		Button edit = new Button("EDIT");
		Label label = new Label(o.getName());
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, edit);
		list.add(hbox);
		return edit;
	}
	
	public Button addPlus() {
		Button plus = new Button(" + ");
		Label label = new Label("New");
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, plus);
		list.add(hbox);
		return plus;
	}
	
	public Node getPane() {
		Pane gp = new Pane();
		for (HBox h : list) {
			gp.getChildren().add(h);
		}
		return gp;
	}
}
