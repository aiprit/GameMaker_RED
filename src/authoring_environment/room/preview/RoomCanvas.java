package authoring_environment.room.preview;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.room.ConfigurePopup;
import authoring_environment.room.RoomController;
import authoring_environment.room.configure_popup.ConfigureView;
import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.view.DraggableView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class RoomCanvas extends Canvas {
	public static final Color DEFAULT_COLOR = Color.WHITE;

	private Color myColor;
	private Image myImage;
	private String myImageFileName;
	//TODO change to Map of DraggableNode, add view as Drag
	private Map<DraggableImage, Point2D> myObjectMap;
	private ResourceBundle myResources;
	private DraggableView myRoomView;
	
	public RoomCanvas(ResourceBundle resources) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight")));
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);
		myResources = resources;
		myObjectMap = new HashMap<DraggableImage, Point2D>();
		this.setOnMousePressed(e -> press(e));
		this.setOnMouseDragged(e -> drag(e));
		this.setOnMouseReleased(e -> released(e));
	}
	
//	public void addNodeToMap(DraggableImage image, ConfigureView popup) {
//		Point2D point = new Point2D(image.getX(), image.getY());
//		this.getGraphicsContext2D().drawImage(image.getImage(), image.getX(), image.getY());
//		myObjectMap.put(image, point);
//		popup.initializePopUp();
//	}
	
	public void addNodeToMap(DraggableImage image) {
		Point2D point = new Point2D(image.getX(), image.getY());
		this.getGraphicsContext2D().drawImage(image.getImage(), image.getX(), image.getY());
		myObjectMap.put(image, point);
		//ConfigurePopup popup = new ConfigurePopup(myResources);
		//popup.initializePopUp();
	}
	
	private void press(MouseEvent event) {
		if (contains(event.getX(), event.getY(), myRoomView)) {
			myRoomView.setXOffset(-1*myRoomView.getWidth()/2);
			myRoomView.setYOffset(-1*myRoomView.getHeight()/2);
			myRoomView.setDraggable(true);
		} else {
			for (DraggableNode node : myObjectMap.keySet()) {
				if (contains(event.getX(), event.getY(), node)) {
						node.setXOffset(node.getX() - event.getX());
						node.setYOffset(node.getY() - event.getY());
						node.setDraggable(true);
						break;
				}
			}
		}
	}
	
	private void released(MouseEvent event) {
		for (DraggableNode node: myObjectMap.keySet()) {
			if (node.getDraggable())
				node.setDraggable(false);
		}
		myRoomView.setDraggable(false);
	}
	private void drag(MouseEvent event) {
		double x = event.getSceneX() - 270;
		double y = event.getSceneY();
		if (myRoomView.getDraggable()) {
			updateNodePosition(myRoomView, x, y);
		} else {
			for (DraggableImage node : myObjectMap.keySet()) {
				//if node is being dragged
				if (node.getDraggable()) {
					updateNodePosition(node, x, y);
					myObjectMap.put(node, new Point2D(node.getX(), node.getY()));
				}
			}
		}
		redrawCanvas();
	}
	
	private void updateNodePosition(DraggableNode node, double x, double y) {
		double adjustedX = x + node.getXOffset();
		double adjustedY = y + node.getYOffset();
		node.setX(adjustedX);
		node.setY(adjustedY);
	}

	public void redrawCanvas() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		setColorFill(myColor);
		for (DraggableImage drag : myObjectMap.keySet()) {
			this.getGraphicsContext2D().drawImage(drag.getImage(), drag.getX(), drag.getY());
		}
		drawView();
	}
	
	private boolean contains(double x, double y, DraggableNode node) {
		return (x > node.getX() && x <= node.getX() + node.getWidth() && 
				y > node.getY() && y <= node.getY() + node.getHeight());
	}
	
	private void setColorFill(Color fill) {
		this.getGraphicsContext2D().setFill(fill);
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void setImageFill(Image image) {
		this.getGraphicsContext2D().setFill(new ImagePattern(image));
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public Color getColor() {
		return myColor;
	}

	public Image getImage() {
		return myImage;
	}

	public String getImageFileName() {
		return myImageFileName;
	}
	
	public void setImage(Image image) {
		myImage = image;
	}

	public void setImageFileName(String name) {
		myImageFileName = name;
	}
	
	public void setBackground(Color color, Image image, String fileName) {
		if (image != null) {
			setImageFill(image);
			myImage = image;
			myImageFileName = fileName;
			myColor = null;
		} else if (color != null){
			setColorFill(color);
			myColor = color;
			myImage = null;
			myImageFileName = null;
		} else {
			setColorFill(DEFAULT_COLOR);
			myColor = DEFAULT_COLOR;
			myImage = image;
			myImageFileName = fileName;
		}
		redrawCanvas();
	}
	
	public void drawView() {
		this.getGraphicsContext2D().setStroke(Color.LIMEGREEN);
		this.getGraphicsContext2D().strokeRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(), myRoomView.getHeight());
	}
	
	public void setView(DraggableView view) {
		myRoomView = view;
	}
}
