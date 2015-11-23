package authoring_environment.main;

import java.util.ResourceBundle;
import authoring_environment.FileHandlers.SoundMaker;
import authoring_environment.FileHandlers.SpriteMaker;
import authoring_environment.object_editor.ObjectEditorController;
import authoring_environment.room.RoomController;
import authoring_environment.room.RoomEditor;
import authoring_environment.room.name_popup.RoomNamePopupController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;
import structures.data.access_restricters.IObjectInterface;

public class MainController implements IUpdateHandle {
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

		// Get updates
		objectListWindow.setUpdateHandle((IUpdateHandle) this);
	}

	public void refreshViews() {
		// Set mainView's views
		mainView.setPanes(objectListWindow.getPane(), roomListView.getPane(),
				new RightView(spriteListView, soundListView).getPane());
	}

	public void update() {
		System.out.println("Updating...");

		myStage.setTitle("Authoring Environment - Editing: " + dataGame.getName());
		mainView.init();

		// Object List
		objectListWindow.init();
		// Add objects to objectList
		for (DataObject o : dataGame.getObjects()) {
			System.out.println("Adding object: " + o.getName());
			objectListWindow.addObject(o).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ObjectEditorController window = new ObjectEditorController((IObjectInterface) dataGame, o);
					window.setOnClose(getUpdater());
				}
			});
		}

		// Add plus button
		objectListWindow.getPlusButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObjectEditorController window = new ObjectEditorController(dataGame);
				update();
				window.setOnClose(getUpdater());
			}
		});

		// Room List
		roomListView.init();
		// for (DataRoom o : dataGame.getRooms()) {
		for (int i = 0; i < dataGame.getRooms().size(); i++) {
			DataRoom o = dataGame.getRooms().get(i);

			roomListView.addRoom(o, i).setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					RoomNamePopupController room = new RoomNamePopupController(o, dataGame);
					room.setOnClose(e -> update(), dataGame, false);
				}
			});
		}

		roomListView.addPlusButton(dataGame.getRooms().size()).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				RoomNamePopupController room = new RoomNamePopupController(dataGame);
				room.setOnClose(e -> update(), dataGame, true);
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
					SpriteMaker.show(o);
					update();
				}
			});
		}

		// Add plus
		spriteListView.addPlus().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SpriteMaker.load(myStage, dataGame.getSprites());
				update();

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
				SoundMaker.load(myStage, dataGame.getSounds());
				update();
			}
		});

		// TopMenuBar
		topMenuBar.init();
		topMenuBar.getLoadMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: handle LOAD EVENT ADD ANDREW PLZ
				System.out.println("Clicked Load");
			}
		});
		topMenuBar.getSaveMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: handle SAVE EVENT ADD ANDREW PLZ
				System.out.println("Clicked Save");
			}
		});
		topMenuBar.getRunMenu().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: handle SAVE EVENT ADD ANDREW PLZ
				System.out.println("Run");
				//RUN HERE
			}
		});
		mainView.setMenuBar(topMenuBar.getMenu());
		refreshViews();
	}

	private IUpdateHandle getUpdater() {
		return (IUpdateHandle) this;
	}
}
