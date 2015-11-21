package authoring_environment.room.preview;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.view.DraggableView;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class RoomCanvas extends Canvas {
	private static final int VIEW_STROKE_WIDTH = 4;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private Color myColor;
	private Image myImage;
	private String myImageFileName;

	private String myBackgroundColor;
	//TODO change to Map of DraggableNode, add view as Drag
	private Map<DraggableImage, Point2D> myObjectMap;
	private DraggableView myRoomView;
	
	public RoomCanvas(ResourceBundle resources) {

		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight")));
		myBackgroundColor = DEFAULT_COLOR.toString();
		setColorFill(DEFAULT_COLOR);
		myObjectMap = new HashMap<DraggableImage, Point2D>();
		this.setOnMousePressed(e -> press(e));
		this.setOnMouseDragged(e -> drag(e));
		this.setOnMouseReleased(e -> released(e));
	}
	
	public DraggableView getRoomView() {
		return myRoomView;
	}
	
	public String getBackgroundColor() {
		return myBackgroundColor;
	}
	public Map<DraggableImage, Point2D> getObjectMap() {
		return myObjectMap;
	}
	
	public void setBackgroundColor(String color) {
		if (color == null) {
			myBackgroundColor = DEFAULT_COLOR.toString();
		} else {
			myBackgroundColor = color;
		}
	}
	
	public void addNodeToMap(DraggableImage image) {
		Point2D point = new Point2D(image.getX(), image.getY());
		this.getGraphicsContext2D().drawImage(image.getImage(), image.getX(), image.getY());
		myObjectMap.put(image, point);
	}
	
	private void press(MouseEvent event) {
		if (contains(event.getX(), event.getY(), myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(), myRoomView.getHeight())) {
			myRoomView.setXOffset(-1*myRoomView.getWidth()/2);
			myRoomView.setYOffset(-1*myRoomView.getHeight()/2);
			myRoomView.setDraggable(true);
		} else {
			DraggableNode topNode = null;
			for (DraggableNode node : myObjectMap.keySet()) {
				if (contains(event.getX(), event.getY(), node.getX(), node.getY(), node.getWidth(), node.getHeight())) {
					topNode = node;
				}
			}
			if (topNode != null) {
				topNode.setXOffset(topNode.getX() - event.getX());
				topNode.setYOffset(topNode.getY() - event.getY());
				topNode.setDraggable(true);
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
		if (inRoomWidthBounds(node.getWidth(), adjustedX)) {
			node.setX(adjustedX);
		}
		if (inRoomHeightBounds(node.getHeight(), adjustedY)) {
			node.setY(adjustedY);
		}
	}
	
	public boolean inRoomBounds(double width, double height, double x, double y) {
		return inRoomWidthBounds(width, x) && inRoomHeightBounds(height, y);
	}
	
	private boolean inRoomWidthBounds(double width, double x) {
		return x >= 0 && x <= this.getWidth() - width;
	}
	
	private boolean inRoomHeightBounds(double height, double y) {
		return y >= 0 && y <= this.getHeight() - height;
	}

	public void redrawCanvas() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		drawBackground();
		for (DraggableImage drag : myObjectMap.keySet()) {
			this.getGraphicsContext2D().drawImage(drag.getImage(), drag.getX(), drag.getY());
		}
		drawView();
	}
	
	public boolean contains(double x, double y, double nodeX, double nodeY, double width, double height) {
		return (x > nodeX && x <= nodeX + width && 
				y > nodeY && y <= nodeY + height);
	}
	
	private void drawBackground() {
		try {
			Color fill = Color.valueOf(myBackgroundColor);
			setColorFill(fill);
		} catch (IllegalArgumentException e) {
			setImageFill(new Image(getClass().getClassLoader().getResourceAsStream(myBackgroundColor)));
		}
	}
	
	private void setColorFill(Color fill) {
		this.getGraphicsContext2D().setFill(fill);
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void setImageFill(Image image) {
		this.getGraphicsContext2D().setFill(new ImagePattern(image));
		this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void addInstance(DraggableImage image, Point2D point) {
		myObjectMap.put(image, point);
	}
	
	public void removeInstance(Image image, Point2D point) {
		for (DraggableImage dragImage : myObjectMap.keySet()) {
			Point2D dragImagePoint = new Point2D(dragImage.getX(), dragImage.getY());
			if (dragImage.getImage().equals(image) && dragImagePoint.equals(point)) {
				myObjectMap.remove(dragImage);
				break;
			}

		}
	}
	
	public void drawView() {
		this.getGraphicsContext2D().setStroke(Color.LIMEGREEN);
		this.getGraphicsContext2D().setLineWidth(VIEW_STROKE_WIDTH);
		this.getGraphicsContext2D().strokeRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(), myRoomView.getHeight());
		if (myRoomView.isVisible()) {
			this.getGraphicsContext2D().setFill(Color.rgb(0, 255, 0, 0.5));
			this.getGraphicsContext2D().fillRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(), myRoomView.getHeight());
		}
	}
	
	public void setView(DraggableView view) {
		myRoomView = view;
	}
}
