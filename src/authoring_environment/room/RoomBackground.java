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



public class RoomBackground extends Canvas {
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private RoomController myController;
	private Color myColor;
	private Image myImage;
	private String myImageFileName;
	//TODO change to Map of DraggableNode, add view as Drag
	private Map<DraggableImage, Point2D> myObjectMap;
	private ResourceBundle myResources;
	private DraggableView myRoomView;
	
	public RoomBackground(ResourceBundle resources, RoomController controller) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myController = controller;
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);

		myResources = resources;
		//FOR TESTING
		Image luigiImage = new Image(getClass().getClassLoader().getResourceAsStream("Luigi.png"));
		DoubleProperty luigiX = new SimpleDoubleProperty();
		luigiX.set(0);
		DoubleProperty luigiY = new SimpleDoubleProperty();
		luigiY.set(0);
		DraggableImage luigi = new DraggableImage(luigiImage, luigiX, luigiY);
		DoubleProperty marioX = new SimpleDoubleProperty();
		marioX.set(300);
		DoubleProperty marioY = new SimpleDoubleProperty();
		marioY.set(300);
		Image marioImage = new Image(getClass().getClassLoader().getResourceAsStream("Mario.png"));
		DraggableImage mario = new DraggableImage(marioImage, marioX, marioY);
		
		
		this.getGraphicsContext2D().drawImage(luigi.getImage(), luigi.getX(), luigi.getY());
		this.getGraphicsContext2D().drawImage(mario.getImage(), mario.getX(), mario.getY());

		myObjectMap = new HashMap<DraggableImage, Point2D>();
		myObjectMap.put(luigi, new Point2D(luigi.getX(), luigi.getY()));
		myObjectMap.put(mario, new Point2D(mario.getX(), mario.getY()));

		initializeView(resources);
		//myObjectMap = new HashMap<DraggableImage, Point2D>();
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
		ConfigurePopUp configurePopUp = new ConfigurePopUp(myResources);
		configurePopUp.initializePopUp();
	}
	
	private void initializeView(ResourceBundle resources) {
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		x.set(myController.getView().getView().x());
		y.set(myController.getView().getView().y());
		myRoomView = new DraggableView(myController.getView(), x, y);
		drawView();
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

	private void redrawCanvas() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		for (DraggableImage drag : myObjectMap.keySet()) {
			//System.out.println(myObjectMap.keySet().size());
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
		//TODO redraw already existing nodes on canvas after changing background
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
