package authoring_environment.room;


import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.configure_popup.ConfigureController;
import authoring_environment.room.object_instance.ObjectInstanceController;
import authoring_environment.room.object_list.ObjectListController;
import authoring_environment.room.view.ViewController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import structures.data.DataGame;
import structures.data.DataInstance;
import structures.data.DataRoom;
import structures.data.DataView;


public class RoomController {
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	
	private ResourceBundle myResources;
	
	private DataRoom model;
	private RoomEditor view;
	
	private ObjectListController myObjectListController;
	private ButtonToolbar myButtonToolbar;
	private ViewController myView;
	
	public RoomController(String roomName, DataGame gameObject) {
		myResources = ResourceBundle.getBundle("resources/RoomResources");
		model = new DataRoom(roomName, Double.parseDouble(myResources.getString(PREVIEW_WIDTH)),
				Double.parseDouble(myResources.getString(PREVIEW_HEIGHT)));
		gameObject.addRoom(model);
		view = new RoomEditor(myResources);
		initializeObjectListContainer(gameObject);
		//initializeButtonToolbar(resources);
		initializeView();
	}
	
	public RoomController(DataRoom room, DataGame gameObject) {
		myResources = ResourceBundle.getBundle("resources/RoomResources");
		model = room;
		view = new RoomEditor(myResources);
		initializeObjectListContainer(gameObject);
		//initializeButtonToolbar(resources);
		initializeView();
	}
	
	public void launch() {
		view.show();
	}
	
	private void initializeObjectListContainer(DataGame gameObject) {
		myObjectListController = new ObjectListController(myResources, gameObject.getObjects());
		Consumer<MouseEvent> dragStarterFunction = e -> startObjectDrag(e);
		myObjectListController.setOnMouseClicked(dragStarterFunction);
		view.getObjectsAndPreview().getChildren().add(myObjectListController.getObjectListContainer());
		view.getObjectsAndPreview().getChildren().add(view.getPreview());
	}
	
	private void initializeButtonToolbar() {
		ButtonHandler handler = new ButtonHandler(myResources, view.getPreview());
		myButtonToolbar = new ButtonToolbar(myResources, handler.getButtons());
		view.getTotalView().getChildren().add(myButtonToolbar);
	}
	
	private void initializeView() {
		myView = new ViewController(model.getDataView());
		//HARDCODED FOR US
		myView.getDraggableView().getHeightProperty().set(400);
		myView.getDraggableView().getWidthProperty().set(400);
		//
		view.getPreview().getCanvas().setView(myView.getDraggableView());
		view.getPreview().getCanvas().drawView();
	}

	public String getName() {
		return model.getName();
	}

	public void addObject(DataInstance object) {
		model.addObjectInstance(object);
	}
	
	public double[] getSize() {
		return model.getSize();
	}
	
	public void setSize(double width, double height) {
		model.setSize(width, height);
	}
	
	public void setView(DataView view) {
		model.setView(view);
	}
	
	public DataView getView() {
		return model.getDataView();
	}
	
	private void startObjectDrag(MouseEvent event) {
		PotentialObjectInstance objectInstance = myObjectListController.startObjectDragAndDrop(event);
		ImageView spriteInstance = objectInstance.getImageView();
		if (spriteInstance != null) {
			view.getRoot().getChildren().add(spriteInstance);
			dragSpriteIntoPreview(objectInstance);
		}
	}
	
	private void dragSpriteIntoPreview(PotentialObjectInstance objectInstance) {
		objectInstance.getImageView().setOnMousePressed(e -> setUpDraggingBehavior(objectInstance));
	}
	
	private void setUpDraggingBehavior(PotentialObjectInstance objectInstance) {
		objectInstance.getImageView().setOnMouseDragged(e -> addSpriteToRoom(e, objectInstance));
	}
	
	private void addSpriteToRoom(MouseEvent e, PotentialObjectInstance potentialObjectInstance) {
		potentialObjectInstance.updateSpritePosition(e);
		Point2D scenePoint = new Point2D(e.getSceneX(), e.getSceneY());
		if (potentialObjectInstance.inRoomBounds()) {
			Point2D canvasPoint = view.getPreview().translateSceneCoordinates(scenePoint);
			DoubleProperty x = new SimpleDoubleProperty();
			DoubleProperty y = new SimpleDoubleProperty();
			x.set(canvasPoint.getX());
			y.set(canvasPoint.getY());
			ObjectInstanceController objectInstance = new ObjectInstanceController(potentialObjectInstance.getImageView().getImage(),
					potentialObjectInstance.getObject(), x, y);
			ConfigureController configurePopup = new ConfigureController(myResources, objectInstance.getDataInstance());
			configurePopup.initialize();
			//view.getPreview().addImage(objectInstance.getDraggableImage(), configurePopup.getConfigureView());
			view.getPreview().addImage(objectInstance.getDraggableImage());
			view.getRoot().getChildren().remove(potentialObjectInstance.getImageView());
			model.addObjectInstance(objectInstance.getDataInstance());
			view.getPreview().getCanvas().redrawCanvas();
		} else {
			//TODO get rid of the object
		}
	} 
}