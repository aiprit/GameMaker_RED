package authoring_environment.main;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import structures.data.DataObject;

public class ObjectListWindow {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private List<HBox> list;
	private Button plusButton;
	private BorderPane bp;
	private IUpdateHandle updater;

	public void init() {
		bp = new BorderPane();
		list = new ArrayList<>();
		HBox header = new HBox();
		Label headerLabel = new Label(r.getString("ObjectListTitle"));
		header.getChildren().addAll(headerLabel);
		list.add(header);
		addPlusButton(bp);
	}

	public BorderPane getPane() {
		return bp;
	}

	public Button getPlusButton() {
		return plusButton;
	}

	public Button addObject(DataObject o) {
		Button editBtn = new Button(r.getString("EditItem"));
		Label label = new Label(o.getName());
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, editBtn);
		list.add(hbox);
		updateList();
		return editBtn;
	}

	private void addPlusButton(BorderPane bp) {
		// Plus button
		plusButton = new Button(r.getString("plus"));
		Label label = new Label(r.getString("MakeNewItem"));
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, plusButton);
		list.add(hbox);
		updateList();
	}
	
	private void updateList() {
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		bp.setLeft(listView);
	}
	
	public void setUpdateHandle(IUpdateHandle t) {
		updater = t;
	}
	
	//@steve
	public void testNewButtonAdding() {
		//simulates adding new objects, to demonstrate how UpdateHandle can be used
		updater.update();
	}
}
