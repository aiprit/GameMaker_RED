package authoring_environment.main;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import structures.data.DataSound;

public class SoundListView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private ArrayList<HBox> list;
	
	public void init() {
		list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label("Sounds");
		header.getChildren().addAll(headerLabel);
		list.add(header);

//		ListView<HBox> listView = new ListView<HBox>();
//		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
//		listView.setItems(myObservableList);
	}
	
	public Button addSound(DataSound o) {
		Button play = new Button("Play");
		Label label = new Label(o.getName());
		HBox hbox = new HBox();
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
		hbox.getChildren().addAll(label, play);
        list.add(hbox);
        return play;
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
