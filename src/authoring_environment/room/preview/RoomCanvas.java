package authoring_environment.room.preview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring_environment.FileHandlers.FileManager;
import authoring_environment.room.error.ErrorPopup;
import authoring_environment.room.grid.Grid;
import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.view.DraggableView;
import exceptions.ResourceFailedException;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import utils.rectangle.IRectangle;

public class RoomCanvas extends Canvas {
	private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "FileNotFoundExceptionMessage";
	private static final int GRID_LINE_WIDTH = 1;
	private static final String VIEW_OPACITY = "ViewOpacity";
	private static final String VIEW_COLOR = "ViewColor";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final int VIEW_STROKE_WIDTH = 4;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	private ResourceBundle myResources;
	private String myBackgroundColor;
	private List<DraggableImage> myObjectList;
	private DraggableView myRoomView;
	private Grid myGrid;
	private String gameName;

	public RoomCanvas(ResourceBundle resources, double width, double height, String gameName) {
		super(width, height);
		this.gameName = gameName;
		myResources = resources;
		myBackgroundColor = DEFAULT_COLOR.toString();
		setColorFill(DEFAULT_COLOR);
		myObjectList = new ArrayList<DraggableImage>();
		this.setOnMouseDragged(e -> drag(e));
		this.setOnMouseReleased(e -> released(e));
		myGrid = new Grid(myResources, super.getWidth(), super.getHeight());
	}

	public List<DraggableImage> getObjectMap() {
		return myObjectList;
	}

	public DraggableView getRoomView() {
		return myRoomView;
	}

	public Grid getGrid() {
		return myGrid;
	}

	public String getBackgroundColor() {
		return myBackgroundColor;
	}

	public void setBackgroundColor(String color) {
		if (color == null) {
			myBackgroundColor = DEFAULT_COLOR.toString();
		} else {
			myBackgroundColor = color;
		}
	}

	public void addNodeToMap(DraggableImage image) {
		if (myGrid.isVisible()) {
			myGrid.snapObjectToGrid(image);
		}
		this.getGraphicsContext2D().drawImage(image.getImage(), image.getX(), image.getY());
		myObjectList.add(image);
	}

	private void released(MouseEvent event) {
		for (DraggableNode node : myObjectList) {
			if (node.getDraggable()) {
				node.setDraggable(false);
				if (myGrid.isVisible()) {
					myGrid.snapObjectToGrid(node);
				}
			}
		}
		if (myGrid.isVisible() && myRoomView.getDraggable()) {
			myGrid.snapViewToGrid(myRoomView);
		}
		myRoomView.setDraggable(false);
	}

	private void drag(MouseEvent event) {
		double x = event.getSceneX() - Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
		double y = event.getSceneY();
		if (myRoomView.getDraggable()) {
			updateNodePosition(myRoomView, x, y);
		} else {
			for (DraggableImage node : myObjectList) {
				// if node is being dragged
				if (node.getDraggable()) {
					updateNodePosition(node, x, y);
				}
			}
		}
		redrawCanvas();
	}

	private void updateNodePosition(DraggableNode node, double x, double y) {
		double adjustedX = x + node.getXOffset();
		double adjustedY = y + node.getYOffset();
		if (node.inRoomWidthBounds(adjustedX, this.getWidth())) {
			node.setX(adjustedX);
		}
		if (node.inRoomHeightBounds(adjustedY, this.getHeight())) {
			node.setY(adjustedY);
		}
	}

	public void redrawCanvas() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		drawBackground();
		drawObjects();
		if (myGrid.isVisible()) {
			drawGridLines();
		}
		drawView();
	}

	public void drawSnapshot() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		drawBackground();
		drawObjects();
	}

	private void drawObjects() {
		for (DraggableImage drag : myObjectList) {
			if (!drag.getVisibility()) {
				continue;
			}
			IRectangle rect = drag.getBounds();
			drawImage(drag.getImage(), drag.getX(), drag.getY(), rect.centerX(), rect.centerY(), drag.getScaleX(), drag.getScaleY(), drag.getAngle(), drag.getAlpha());
			//drawRotatedImage(drag.getImage(), drag.getAngle(), drag.getX(), drag.getY(), drag.getScaleX(),
			//		drag.getScaleY(), drag.getAlpha());
		}
	}

	public void drawImage(	Image image, double x, double y,
			double centerX, double centerY,
			double scaleX, double scaleY, double angle, double alpha) {

		GraphicsContext myGraphicsContext = this.getGraphicsContext2D();


		//draw the new object
		myGraphicsContext.save();
		myGraphicsContext.translate(x, y);
		myGraphicsContext.rotate(angle);
		myGraphicsContext.scale(scaleX, scaleY);

		myGraphicsContext.setGlobalAlpha(alpha);
		myGraphicsContext.drawImage(image, -1 * centerX /scaleX, -1 * centerY /scaleY);
		myGraphicsContext.restore();

	}

	private void drawGridLines() {
		double cellSize = myGrid.getCellSize();
		this.getGraphicsContext2D().setStroke(Color.BLACK);
		this.getGraphicsContext2D().setLineWidth(GRID_LINE_WIDTH);
		// draw vertical lines
		for (int i = 0; i < this.getWidth(); i += cellSize) {
			this.getGraphicsContext2D().strokeLine(i, 0, i, this.getHeight());
		}
		// draw horizontal lines
		for (int i = 0; i < this.getHeight(); i += cellSize) {
			this.getGraphicsContext2D().strokeLine(0, i, this.getWidth(), i);
		}
	}

	private void drawBackground() {
		try {
			Color fill = Color.valueOf(myBackgroundColor);
			setColorFill(fill);
		} catch (IllegalArgumentException e) {
			FileManager fm = new FileManager(gameName);
			try {
				setImageFill(fm.getBackground(myBackgroundColor));
			} catch (ResourceFailedException e1) {
				String errorMessage = String.format(myResources.getString(FILE_NOT_FOUND_EXCEPTION_MESSAGE), myBackgroundColor);
				ErrorPopup error = new ErrorPopup(myResources, errorMessage);
			}
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
		myObjectList.add(image);
	}

	public void removeInstance(DraggableImage instance) {
		myObjectList.remove(getClickedImage(instance));
	}

	public DraggableImage getClickedImage(DraggableImage instance) {
		Point2D point = new Point2D(instance.getX(), instance.getY());
		for (DraggableImage dragImage : myObjectList) {
			Point2D dragImagePoint = new Point2D(dragImage.getX(), dragImage.getY());
			if (dragImagePoint.equals(point)) {
				return dragImage;
			}
		}
		return null;
	}

	public void drawView() {
		List<Integer> viewRGB = Arrays.asList(myResources.getString(VIEW_COLOR).split(",")).stream()
				.map(val -> Integer.parseInt(val)).collect(Collectors.toList());
		this.getGraphicsContext2D().setStroke(Color.rgb(viewRGB.get(0), viewRGB.get(1), viewRGB.get(2)));
		this.getGraphicsContext2D().setLineWidth(VIEW_STROKE_WIDTH);
		this.getGraphicsContext2D().strokeRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(),
				myRoomView.getHeight());
		if (myRoomView.isVisible()) {
			this.getGraphicsContext2D().setFill(Color.rgb(viewRGB.get(0), viewRGB.get(1), viewRGB.get(2),
					Double.parseDouble(myResources.getString(VIEW_OPACITY))));
			this.getGraphicsContext2D().fillRect(myRoomView.getX(), myRoomView.getY(), myRoomView.getWidth(),
					myRoomView.getHeight());
		}
	}

	public void setView(DraggableView view) {
		myRoomView = view;
	}
}
