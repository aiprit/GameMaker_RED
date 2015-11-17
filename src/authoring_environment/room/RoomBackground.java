package authoring_environment.room;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class RoomBackground extends Canvas {
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private RoomController myController;
	private Color myColor;
	private Image myImage;
	private String myImageFileName;
	private Map<DraggableImage, Point2D> myObjectMap;
	private DraggableView myRoomView;
	
	public RoomBackground(ResourceBundle resources, RoomController controller) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myController = controller;
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);
		initializeView(resources);
		myObjectMap = new HashMap<DraggableImage, Point2D>();
		this.setOnMousePressed(e -> press(e));
		this.setOnMouseDragged(e -> drag(e));
		this.setOnMouseReleased(e -> released(e));
	}
	
	public void addNodeToMap(Image image, Point2D point) {
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		x.set(point.getX());
		y.set(point.getY());
		DraggableImage dragNode = new DraggableImage(image, x, y);
		this.getGraphicsContext2D().drawImage(image, point.getX(), point.getY());
		myObjectMap.put(dragNode, point);
	}
	
	private void initializeView(ResourceBundle resources) {
		DoubleProperty x = new SimpleDoubleProperty();
		x.set(0);
		DoubleProperty y = new SimpleDoubleProperty();
		y.set(0);
		myRoomView = new DraggableView(resources, x, y);
		drawView();
	}
	
	private void press(MouseEvent event) {
		if (contains(event.getX(), event.getY(), myRoomView)) {
			myRoomView.setDraggable(true);
		} else {
			for (DraggableImage node : myObjectMap.keySet()) {
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

	private void redrawCanvas() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
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
	}
	
	public void setRoomWidth(double width) {
		this.setWidth(width);
		myController.setSize(width, this.getHeight());
	}
	
	public void setRoomHeight(double height) {
		this.setHeight(height);
		myController.setSize(this.getWidth(), height);
	}
	
	private void drawView() {
		this.getGraphicsContext2D().setStroke(Color.LIMEGREEN);
		this.getGraphicsContext2D().strokeRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(), myRoomView.getHeight());
	}
}
