package authoring_environment.main;

import java.util.ArrayList;
import java.util.HashMap;
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
	private Button plusButton;
	private BorderPane bp;
	private IUpdateHandle updater;
	
	public void init() {
		list = new ArrayList<HBox>();
		bp = new BorderPane();
		HBox header = new HBox();
		Label headerLabel = new Label(r.getString("SpritesListTitle"));
		header.getChildren().addAll(headerLabel);
		list.add(header);
		
//		ListView<HBox> listView = new ListView<HBox>();
//		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
//		listView.setItems(myObservableList);
//		window.getChildren().add(listView);
	}

	public HashMap<String, Button> addSprite(DataSprite o) {
		Button show = new Button("Show");
		Button remove = new Button(" - ");
		
		Label label = new Label(o.getName());
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, show);
		list.add(hbox);
		updateList();
		HashMap<String, Button> buttons = new HashMap<String, Button>();
		
		buttons.put("Show", show);
		
		return buttons;
	}
	
	
	public Button addPlus() {
		Button plus = new Button(r.getString("plus"));
		Label label = new Label(r.getString("MakeNewItem"));
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, plus);
		list.add(hbox);
		updateList();
		return plus;
	}
	
		public ListView<HBox> getPane() {
		
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		return listView;
	}
		public void setUpdateHandle(IUpdateHandle t) {
			updater = t;
		}
		private void updateList() {
			ListView<HBox> listView = new ListView<HBox>();
			ObservableList<HBox> myObservableList = FXCollections.observableList(list);
			listView.setItems(myObservableList);
			bp.setLeft(listView);
		}
		
}
