package authoring_environment.room;


import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.button_toolbar.ButtonToolbar;
import authoring_environment.room.button_toolbar.ButtonToolbarController;
import authoring_environment.room.configure_popup.ConfigureController;
import authoring_environment.room.object_instance.DraggableImage;
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
	private ButtonToolbarController myButtonToolbarController;
	private ViewController myViewController;
	
	public RoomController(DataGame gameObject) {
		myResources = ResourceBundle.getBundle("resources/RoomResources");
		model = new DataRoom("New Room", Double.parseDouble(myResources.getString(PREVIEW_WIDTH)),
				Double.parseDouble(myResources.getString(PREVIEW_HEIGHT)));
		gameObject.addRoom(model);
		view = new RoomEditor(myResources);
		initializeObjectListContainer(gameObject);
		initializeView();
		initializeButtonToolbar();
		view.getPreview().getCanvas().redrawCanvas();
	}
	
	public RoomController(DataRoom room, DataGame gameObject) {
		myResources = ResourceBundle.getBundle("resources/RoomResources");
		model = room;
		view = new RoomEditor(myResources);
		populateEditor(room);
		initializeObjectListContainer(gameObject);
		initializeButtonToolbar();
		initializeView();
		view.getPreview().getCanvas().redrawCanvas();
	}
	
	public void launch() {
		view.show();
	}
	
	private void populateEditor(DataRoom room) {
		view.getPreview().getCanvas().setWidth(model.getSize()[0]);
		view.getPreview().getCanvas().setHeight(model.getSize()[1]);
		for (DataInstance instance : model.getObjectInstances()) {
			DoubleProperty[] point = createDoubleProperties(instance.getX(), instance.getY());
			view.getPreview().getCanvas().addInstance(new DraggableImage(instance.getImage(), point[0], point[1]), 
					new Point2D(instance.getX(), instance.getY()));
		}
	}
	
	private void initializeObjectListContainer(DataGame gameObject) {
		myObjectListController = new ObjectListController(myResources, gameObject.getObjects());
		Consumer<MouseEvent> dragStarterFunction = e -> startObjectDrag(e);
		myObjectListController.setOnMouseClicked(dragStarterFunction);
		view.getObjectsAndPreview().getChildren().add(myObjectListController.getObjectListContainer());
		view.getObjectsAndPreview().getChildren().add(view.getPreview());
	}
	
	private void initializeButtonToolbar() {
		myButtonToolbarController = new ButtonToolbarController(myResources, 
				view.getPreview().getCanvas(), model);
		view.getTotalView().getChildren().add(myButtonToolbarController.getButtonToolbar());
	}
	
	private void initializeView() {
		myViewController = new ViewController(model.getDataView());
		view.getPreview().getCanvas().setView(myViewController.getDraggableView());
		view.getPreview().getCanvas().drawView();
		view.getPreview().getCanvas().setOnMouseClicked(e -> doubleClicked(e, view.getPreview().getCanvas().getObjectMap()));
	}
	
	private void doubleClicked(MouseEvent event, Map<DraggableImage, Point2D> objectMap) {
		if (event.getClickCount() == 2) {
			for (DataInstance instance : model.getObjectInstances()) {
				//TODO add scale x and scale y factors
				double width = instance.getParentObject().getSprite().getImage().getWidth();
				double height = instance.getParentObject().getSprite().getImage().getHeight();
				if (view.getPreview().getCanvas().contains(event.getX(), event.getY(), instance.getX(), instance.getY(), width, height)){
					ConfigureController configure = new ConfigureController(myResources, instance, Consumer<DataInstance> consumer);
					configure.initialize();
				}
			}
		}
	}
	
	private void delete(DataInstance instance) {
		model.removeObjectInstance(instance);
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
			setUpDraggingBehavior(objectInstance);
		}
	}
	
	private void setUpDraggingBehavior(PotentialObjectInstance objectInstance) {
		objectInstance.getImageView().setOnMouseReleased(e -> addSpriteToRoom(e, objectInstance));
	}
	
	private void addSpriteToRoom(MouseEvent e, PotentialObjectInstance potentialObjectInstance) {
		Point2D screenPoint = new Point2D(e.getScreenX(), e.getScreenY());
		Point2D previewPoint = view.getPreview().screenToLocal(screenPoint);
		if (inRoomBounds(previewPoint)) {
			DoubleProperty[] xy = createDoubleProperties(previewPoint.getX(), previewPoint.getY());
			ObjectInstanceController objectInstance = new ObjectInstanceController(potentialObjectInstance.getImageView().getImage(),
					potentialObjectInstance.getObject(), xy[0], xy[1]);
			//view.getPreview().addImage(objectInstance.getDraggableImage(), configurePopup.getConfigureView());
			view.getPreview().addImage(objectInstance.getDraggableImage());
			view.getRoot().getChildren().remove(potentialObjectInstance.getImageView());
			model.addObjectInstance(objectInstance.getDataInstance());
			view.getPreview().getCanvas().redrawCanvas();
		} else {
			//TODO get rid of the object
		}
	} 
	
	private DoubleProperty[] createDoubleProperties(double X, double Y) {
		DoubleProperty[] properties = new DoubleProperty[2];
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		x.set(X);
		y.set(Y);
		properties[0] = x;
		properties[1] = y;
		return properties;
	}
	
	private boolean inRoomBounds(Point2D scenePoint) {
		return scenePoint.getX() >= 0 && scenePoint.getX() <= view.getPreview().getPrefWidth()
				&& scenePoint.getY() >= 0 && scenePoint.getY() <= view.getPreview().getPrefHeight();
	}
}