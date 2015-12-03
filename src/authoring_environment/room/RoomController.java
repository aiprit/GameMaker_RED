package authoring_environment.room;


import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.bounding_box.BoundingBoxController;
import authoring_environment.room.button_toolbar.ButtonToolbarController;
import authoring_environment.room.configure_popup.ConfigureController;
import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.object_instance.ObjectInstanceController;
import authoring_environment.room.object_list.ObjectListController;
import authoring_environment.room.view.ViewController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataView;
import structures.data.IDataGame;
import utils.rectangle.*;


public class RoomController {
	private static final double CLONE_OFFSET = 15;
	
	private ResourceBundle myResources;
	
	private DataRoom model;
	private RoomEditor view;
	
	private ObjectListController myObjectListController;
	private ButtonToolbarController myButtonToolbarController;
	private ViewController myViewController;
	
	public RoomController(ResourceBundle resources, DataRoom room, IDataGame gameObject) {
		myResources = resources;
		model = room;
		model.getView().setView(new Rectangle(room.getView().getX(), room.getView().getY(),
				gameObject.getViewWidth(), gameObject.getViewHeight()));
		view = new RoomEditor(myResources, room.getName());
		populateEditor(room);
		initializeObjectListContainer(gameObject);
		initializeView();
		initializeButtonToolbar();
		view.getPreview().getCanvas().redrawCanvas();
	}
	
	public void launch() {
		view.show();
	}
	
	public RoomEditor getEditor() {
		return view;
	}
	
	private void populateEditor(DataRoom room) {
		view.getPreview().getCanvas().setWidth(model.getSize()[0]);
		view.getPreview().getCanvas().setHeight(model.getSize()[1]);
		view.getPreview().getCanvas().setBackgroundColor(model.getBackgroundColor());
		for (DataInstance instance : model.getObjectInstances()) {
			ObjectInstanceController controller = new ObjectInstanceController(instance);
			view.getPreview().getCanvas().addInstance(controller.getDraggableImage(), 
					new Point2D(instance.getX(), instance.getY()));
		}
	}
	
	private void initializeObjectListContainer(IDataGame gameObject) {
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
		myViewController = new ViewController(model.getView());
		view.getPreview().getCanvas().setView(myViewController.getDraggableView());
		view.getPreview().getCanvas().drawView();
		view.getPreview().getCanvas().setOnMouseClicked(e -> onClick(e, view.getPreview().getCanvas().getObjectMap()));
	}
	
	private void onClick(MouseEvent event, List<DraggableImage> objectMap) {
		view.getPreview().setOnKeyPressed(null);
		view.getPreview().getCanvas().redrawCanvas();
		for (DataInstance instance : model.getObjectInstances()) {
			ObjectInstanceController currentObject = new ObjectInstanceController(instance);
			if (view.getPreview().getCanvas().contains(event.getX(), event.getY(), currentObject.getDraggableImage())){
				BoundingBoxController boundBox = new BoundingBoxController(view.getPreview().getCanvas(), currentObject);
				boundBox.draw();
				view.getPreview().setOnKeyPressed(e -> handleKeyPress(e, currentObject));
				if (event.getClickCount() == 2) {
					Consumer<Void> redrawFunc = e -> view.getPreview().getCanvas().redrawCanvas();
					ConfigureController configure = new ConfigureController(myResources, instance, view.getPreview().getCanvas().getClickedImage(currentObject.getDraggableImage()), redrawFunc); 
					configure.initialize();
				} 
			} 
		}
	}
	
	
	
	private void handleKeyPress(KeyEvent event, ObjectInstanceController controller) {
		switch (event.getCode()) {
		case DELETE:
			delete(controller);
			break;
		case BACK_SPACE:
			delete(controller);
			break;
		case V:
			if (event.isControlDown() || event.isShortcutDown()) {
				clone(controller);
			}
			break;
		default:
			break;
		}
	}
	
	private void clone(ObjectInstanceController controller) {
		double x = controller.getDataInstance().getX() + CLONE_OFFSET;
		double y = controller.getDataInstance().getY() + CLONE_OFFSET;
		double width = controller.getDraggableImage().getImage().getWidth() * controller.getDraggableImage().getScaleX();
		double height = controller.getDraggableImage().getImage().getHeight() * controller.getDraggableImage().getScaleY();
		if (!view.getPreview().getCanvas().inRoomWidthBounds(width, x)) {
			x = view.getPreview().getCanvas().getWidth() - width;
		}
		if (!view.getPreview().getCanvas().inRoomHeightBounds(height, y)) {
			y = view.getPreview().getCanvas().getHeight() - height;
		}
		createAndAddObjectInstance(controller.getDraggableImage().getImage(), 
				controller.getDataInstance().getParentObject(), controller.getDataInstance(), x, y);
	}
	
	private void delete(ObjectInstanceController instance) {
		model.removeObjectInstance(instance.getDataInstance());
		view.getPreview().getCanvas().removeInstance(instance.getDraggableImage());
		view.getPreview().getCanvas().redrawCanvas();
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
		return model.getView();
	}
	
	private void startObjectDrag(MouseEvent event) {
		PotentialObjectInstance objectInstance = myObjectListController.startObjectDragAndDrop(event, view.getRoot());
		try {
			ImageView spriteInstance = objectInstance.getImageView();
			if (spriteInstance != null) {
				view.getRoot().getChildren().add(spriteInstance);
				setUpDraggingBehavior(objectInstance);
			}
		} catch (NullPointerException e) {}
	}
	
	private void setUpDraggingBehavior(PotentialObjectInstance objectInstance) {
		objectInstance.getImageView().setOnMouseReleased(e -> addSpriteToRoom(e, objectInstance));
	}
	
	private void addSpriteToRoom(MouseEvent e, PotentialObjectInstance potentialObjectInstance) {
		Point2D screenPoint = new Point2D(e.getScreenX(), e.getScreenY());
		Point2D canvasPoint = view.getPreview().getCanvas().screenToLocal(screenPoint);
		double width = potentialObjectInstance.getImageView().getImage().getWidth() * potentialObjectInstance.getImageView().getScaleX();
		double height = potentialObjectInstance.getImageView().getImage().getHeight() * potentialObjectInstance.getImageView().getScaleY();
		if (view.getPreview().getCanvas().inRoomBounds(width, height, canvasPoint.getX()-width/2, canvasPoint.getY()-height/2)) {
			createAndAddObjectInstance(potentialObjectInstance.getImageView().getImage(), potentialObjectInstance.getObject(),
					null, canvasPoint.getX()-width/2, canvasPoint.getY()-height/2);
			view.getRoot().getChildren().remove(potentialObjectInstance.getImageView());
		}
	} 
	
	private void createAndAddObjectInstance(Image image, DataObject object, DataInstance instance, double x, double y) {
		DoubleProperty[] coordinates = createDoubleProperties(x, y);
		ObjectInstanceController objectInstance = instance == null ?
				new ObjectInstanceController(image, object, coordinates[0], coordinates[1]) :
				new ObjectInstanceController(image, instance, coordinates[0], coordinates[1]);
		view.getPreview().addImage(objectInstance.getDraggableImage());
		model.addObjectInstance(objectInstance.getDataInstance());
		view.getPreview().getCanvas().redrawCanvas();
	}
	
	private DoubleProperty[] createDoubleProperties(double primitiveX, double primitiveY) {
		DoubleProperty[] properties = new DoubleProperty[2];
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		x.set(primitiveX);
		y.set(primitiveY);
		properties[0] = x;
		properties[1] = y;
		return properties;
	}
}