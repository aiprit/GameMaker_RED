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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RoomBackground extends Canvas {
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private Color myColor;
	private Image myImage;
	private String myImageFileName;
	private Image luigiImage;
	private Map<DraggableNode, Point2D> myObjectMap;
	
	public RoomBackground(ResourceBundle resources) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		myColor = DEFAULT_COLOR;
		setColorFill(DEFAULT_COLOR);
		luigiImage = new Image(getClass().getClassLoader().getResourceAsStream("Luigi.png"));
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
	
	private void press(MouseEvent event) {
		for (DraggableNode node : myObjectMap.keySet()) {
			if (contains(event.getX(), event.getY(), node)) {
					node.setXOffset(node.getX() - event.getX());
					node.setYOffset(node.getY() - event.getY());
					node.setDraggable(true);
					//just break out of for loop for now, get first node that is clicked
					System.out.println("Hi");
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
		System.out.println("Event x is " + x);
		System.out.println("Event y is " + y);
		for (DraggableNode node : myObjectMap.keySet()) {
			//if node is being dragged
			if (node.getDraggable()) {
				double adjustedX = x + node.getXOffset();
				double adjustedY = y + node.getYOffset();
				node.setX(adjustedX);
				node.setY(adjustedY);
				myObjectMap.put(node, new Point2D(adjustedX, adjustedY));
				this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
				for (DraggableNode drag : myObjectMap.keySet()) {
					this.getGraphicsContext2D().drawImage(drag.getImage(), drag.getX(), drag.getY());
				}
			}
		}
		
		//	for (Image image : myObjectMap.keySet()) {
			//image contains click
			//if (contains(x, y, image)) {
				//this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
				//for (Image obj : myObjectMap.keySet()) {
				//	this.getGraphicsContext2D().drawImage(obj, myObjectMap.get(obj).getX(), myObjectMap.get(obj).getY());
				//}
				//this.getGraphicsContext2D().drawImage(image, x, y);
				//myObjectMap.put(image, new Point2D(x, y));
		//	}
				
		//}
		//this.getGraphicsContext2D().clearRect(0, 0, luigi.getWidth(), luigi.getHeight());
	}
	
	private boolean contains(double x, double y, DraggableNode node) {
		//System.out.println("Image x cor is " + myObjectMap.get(image).getX());
		//System.out.println("Image y cor is " + myObjectMap.get(image).getY());

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
	
	//TODO write this to IRoom as well
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
	private class DraggableNode {
		private boolean draggable;
		private Image image;
		private double xCor;
		private double yCor;
		private double xOffset;
		private double yOffset;
		
		public DraggableNode(Image imageObj, double x, double y) {
			image = imageObj;
			draggable = false;
			xCor = x;
			yCor = y;
		}
		
		public double getXOffset() {
			return xOffset;
		}
		public double getYOffset() {
			return yOffset;
		}
		
		public void setXOffset(double xOff) {
			xOffset = xOff;
		}
		
		public void setYOffset(double yOff) {
			yOffset = yOff;
		}
		
		public double getX() {
			return xCor;
		}
		public boolean getDraggable() {
			return draggable;
		}
		public double getY() {
			return yCor;
		}
		public void setDraggable(boolean bool) {
			draggable = bool;
		}
		public Image getImage() {
			return image;
		}
		public void setX(double x) {
			xCor = x;
		}
		public void setY(double y) {
			yCor = y;
		}
	}
}
