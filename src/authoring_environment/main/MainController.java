
package authoring_environment.main;

import java.util.ResourceBundle;
import authoring_environment.room.RoomEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

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
	private SpriteListView spriteListView;
	private SoundListView soundListView;
	private TopMenuBar topMenuBar;

	public MainController(Stage myStage) {
		this.myStage = myStage;
		mainView = new MainView(myStage);
		objectListWindow = new ObjectListWindow();
		roomListView = new RoomListView();
		topMenuBar = new TopMenuBar();
		spriteListView = new SpriteListView();
		soundListView = new SoundListView();
		dataGame = new WelcomeWizardView(myStage).showAndWait();
		update();
	}

	public void update() {
		myStage.setTitle("Authoring Environment - Editing: " + dataGame.getName());
		mainView.init();

		// Object List
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

		// Room List
		roomListView.init();
		for (DataRoom o : dataGame.getRooms()) {
			roomListView.addRoom(o).setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// TODO: @ankit - use the RoomData object o here and open
					// your Room Editor (edit room)
				}
			});
		}

		roomListView.addPlusButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: @ankit - open your Room Editor (new room)
			}
		});

		// Right Pane: Sprites and Sounds
		// Sprites Pane
		spriteListView.init();

		// Add sprites to list
		for (DataSprite o : dataGame.getSprites()) {
			spriteListView.addSprite(o).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO: @steve call the sprite editor here (edit sprite o)
				}
			});
		}

		// Add plus
		spriteListView.addPlus().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: @steve call the sprite editor here (new sprite)
			}
		});

		// Sound Pane
		soundListView.init();

		// Add sounds to list
		for (DataSound o : dataGame.getSounds()) {
			soundListView.addSound(o).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO: @steve call the sound editor here (edit sound o)
				}
			});
		}

		// Add plus
		soundListView.addPlus().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: @steve call the sound editor here (new sound)
			}
		});

		// TopMenuBar
		topMenuBar.init();
		topMenuBar.getEditMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//TODO: handle edit event
				System.out.println("Clicked Edit");
			}
		});
		mainView.setMenuBar(topMenuBar.getMenu());
		
		// Set mainView's views
		Pane rightPane = new Pane();
		rightPane.getChildren().addAll(spriteListView.getPane(), soundListView.getPane());
		mainView.setPanes(objectListWindow.getPane(), roomListView.getPane(), rightPane);
	}
}
