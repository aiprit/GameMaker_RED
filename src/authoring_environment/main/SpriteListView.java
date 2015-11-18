package authoring_environment.main;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import structures.data.DataSprite;

public class SpriteListView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private List<HBox> list;

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
	
		public ListView<HBox> getPane() {
		
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		return listView;
	}
}
