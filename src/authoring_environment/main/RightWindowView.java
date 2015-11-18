package authoring_environment.main;

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
import structures.data.DataSound;
import structures.data.DataSprite;

public class RightWindowView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public void init(BorderPane bp, VBox window, ObservableList<DataSound> sounds, ObservableList<DataSprite> sprites){
		update(bp, window, sounds, sprites);
	}
	private void makeSoundWindow(ObservableList<DataSound> sounds, VBox window) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label("Object");
		header.getChildren().addAll(headerLabel);
		list.add(header);

		for(int i = 0; i <sounds.size(); i++){
			Button edit = new Button("EDIT");
			Label label = new Label(sounds.get(i).getName());
			
			HBox hbox = new HBox();
			final int j  = i;
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
			EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){		
						System.out.println("hey");


				}
			};
			edit.setOnAction(objectClick);
			hbox.getChildren().addAll(label, edit);
            list.add(hbox);

		}
		Button plus = new Button(" + ");
		Label label = new Label("New");
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){		
					System.out.println("hey");

			}
		};
		plus.setOnAction(objectClick);
		hbox.getChildren().addAll(label, plus);
        list.add(hbox);
		//ListView<HBox> listView = makeHBox(sButtonClick, newList, resources.getString(NEW_ITEM), resources.getString(OBJECT_TITLE));
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		window.getChildren().add(listView);
	}
	private void makeSpriteWindow(ObservableList<DataSprite> sprites, VBox window) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label("Object");
		header.getChildren().addAll(headerLabel);
		list.add(header);

		for(int i = 0; i <sprites.size(); i++){
			Button edit = new Button("EDIT");
			Label label = new Label(sprites.get(i).getName());
			
			HBox hbox = new HBox();
			final int j  = i;
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
			EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){		
						System.out.println("hey");


				}
			};
			edit.setOnAction(objectClick);
			hbox.getChildren().addAll(label, edit);
            list.add(hbox);

		}
		Button plus = new Button(" + ");
		Label label = new Label("New");
		HBox hbox = new HBox();
		label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        EventHandler<ActionEvent> objectClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){		
					System.out.println("hey");

			}
		};
		plus.setOnAction(objectClick);
		hbox.getChildren().addAll(label, plus);
        list.add(hbox);
		//ListView<HBox> listView = makeHBox(sButtonClick, newList, resources.getString(NEW_ITEM), resources.getString(OBJECT_TITLE));
		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		window.getChildren().add(listView);
		
	}
	public void update(BorderPane bp, VBox window, ObservableList<DataSound> sounds, ObservableList<DataSprite> sprites){
		makeSpriteWindow(sprites, window);
		makeSoundWindow(sounds, window);
		bp.setRight(window);
	}

}
