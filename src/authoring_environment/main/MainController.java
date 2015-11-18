package authoring_environment.main;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.room.RoomController;
import authoring_environment.room.RoomEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;

public class MainController {

	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private DataGame dataGame;
	private Stage myStage;

	// My subcomponents
	private RoomEditor RoomEditor;

	// My views
	private MainView mainView;
	private ObjectListWindow objectListWindow;
	private RoomListView roomListView;
	private RightWindowView rightWindowView;
	private TopMenuBar topMenuBar;

	public MainController(Stage myStage) {
		this.myStage = myStage;
		mainView = new MainView(myStage);
		objectListWindow = new ObjectListWindow();
		roomListView = new RoomListView();
		topMenuBar = new TopMenuBar();
		rightWindowView = new RightWindowView();
		dataGame = new WelcomeWizardView(myStage).showAndWait();
		init();
	}

	public void init() {
		myStage.setTitle("Authoring Environment - Editing: "+dataGame.getName());
		mainView.init();
		objectListWindow.init();

		// Add objects to objectList
		for (DataObject o : dataGame.getObjects()) {
			objectListWindow.addObject(o).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO: @nick. Need to call the ObjectComponent here (edit
					// game).
					// use the DataObject o for this.
				}
			});
		}

		// Add plus button
		objectListWindow.getPlusButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: @nick. Need to call the ObjectComponent here (new
				// game).
			}
		});

		roomListView.init();
		for (DataRoom o : dataGame.getRooms()) {
			roomListView.addRoom(o).setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					//TODO: @ankit - use the RoomData object o here and open your Room Editor (edit room)
				}
			});
		}
		// topMenuBar.init();
		// rightWindowView.init();

		// Set mainView's views
		mainView.setPanes(objectListWindow.getPane());
	}
}
