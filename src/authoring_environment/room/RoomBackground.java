package authoring_environment.room;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
	private Map<DraggableNode, Point2D> myObjectMap;
	private ResourceBundle myResources;
	
	public RoomBackground(ResourceBundle resources, RoomController controller) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myController = controller;
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);

		myResources = resources;
		//FOR TESTING
		Image luigiImage = new Image(getClass().getClassLoader().getResourceAsStream("Luigi.png"));
		DraggableNode luigi = new DraggableNode(luigiImage, 0, 0);

		Image marioImage = new Image(getClass().getClassLoader().getResourceAsStream("Mario.png"));
		DraggableNode mario = new DraggableNode(marioImage, 300, 300);
		
		
		this.getGraphicsContext2D().drawImage(luigi.getImage(), luigi.getX(), luigi.getY());
		this.getGraphicsContext2D().drawImage(mario.getImage(), mario.getX(), mario.getY());

		myObjectMap = new HashMap<DraggableNode, Point2D>();
		myObjectMap.put(luigi, new Point2D(luigi.getX(), luigi.getY()));
		myObjectMap.put(mario, new Point2D(mario.getX(), mario.getY()));
		this.setOnMousePressed(e -> press(e));
		this.setOnMouseDragged(e -> drag(e));
		this.setOnMouseReleased(e -> released(e));
	}
	
	public void addNodeToMap(Image image, Point2D point) {
		DraggableNode dragNode = new DraggableNode(image, point.getX(), point.getY());
		this.getGraphicsContext2D().drawImage(image, point.getX(), point.getY());
		myObjectMap.put(dragNode, point);
		ConfigurePopUp configurePopUp = new ConfigurePopUp(myResources);
		configurePopUp.initializePopUp();
	}
	
	private void press(MouseEvent event) {
		for (DraggableNode node : myObjectMap.keySet()) {
			if (contains(event.getX(), event.getY(), node)) {
					node.setXOffset(node.getX() - event.getX());
					node.setYOffset(node.getY() - event.getY());
					node.setDraggable(true);
					break;
			}
		}
	}
	
	private void released(MouseEvent event) {
		for (DraggableNode node: myObjectMap.keySet()) {
			if (node.getDraggable())
				node.setDraggable(false);
		}
	}
	private void drag(MouseEvent event) {
		double x = event.getSceneX() - 270;
		double y = event.getSceneY();
		for (DraggableNode node : myObjectMap.keySet()) {
			//if node is being dragged
			if (node.getDraggable()) {
				double adjustedX = x + node.getXOffset();
				double adjustedY = y + node.getYOffset();
				//HERE
				node.setX(adjustedX);
				node.setY(adjustedY);
				myObjectMap.put(node, new Point2D(adjustedX, adjustedY));
				this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
				for (DraggableNode drag : myObjectMap.keySet()) {
					this.getGraphicsContext2D().drawImage(drag.getImage(), drag.getX(), drag.getY());
				}
			}
		}
	}
	
	private boolean contains(double x, double y, DraggableNode node) {
		return (x > node.getX() && x <= node.getX() + node.getImage().getWidth() && 
				y > node.getY() && y <= node.getY() + node.getImage().getHeight());
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
	
	//TODO write this through RoomController as well
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
	
}
