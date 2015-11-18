package authoring_environment;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RightWindowView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public void init(BorderPane bp, VBox window){
		makeSpriteWindow(window);
		makeSoundWindow(window);
		bp.setRight(window);
	}
	private void makeSoundWindow(VBox window) {
		EventHandler<ActionEvent> newSounds = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		
		makeListView(window, newSounds, 1, r.getString("MakeNewItem"), r.getString("SoundsListTitle"));
	}
	private void makeSpriteWindow(VBox window) {
		EventHandler<ActionEvent> newSprite = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		makeListView(window, newSprite, 1, r.getString("MakeNewItem"), r.getString("SpritesListTitle"));
	}
	private void makeListView(VBox V, EventHandler<ActionEvent> e, int n, String name, String title) {
		ListView<HBox> listView = makeHBox(e, n, name, title);
		
		V.getChildren().add(listView);
	}
	private ListView<HBox> makeHBox(EventHandler<ActionEvent> e, int n, String name, String title) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label(title);
		header.getChildren().addAll(headerLabel);
		list.add(header);
		for (int i = 0; i < n; i++) {
           // list.add(new HBoxCell("Item " + i, "Button " + i));
			Button plus = new Button(" + ");
			plus.setOnAction(e);
			Label label = new Label(name);
			HBox hbox = new HBox();
		
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            
            hbox.getChildren().addAll(label, plus);
            list.add(hbox);
		}

		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		return listView;
	}
}
