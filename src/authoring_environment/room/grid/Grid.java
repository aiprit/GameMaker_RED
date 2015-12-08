package authoring_environment.room.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring_environment.room.preview.DraggableNode;
import authoring_environment.room.view.DraggableView;
import javafx.geometry.Point2D;
import utils.Point;

public class Grid {
	private static final String GRID_CELL_SIZE = "GridCellSize";
	
	private double myCellSize;
	private boolean isVisible;
	private List<List<Point2D>> myMesh;
	private double myCanvasWidth;
	private double myCanvasHeight;
	
	public Grid(ResourceBundle resources, double width, double height) {
		myCellSize = Double.parseDouble(resources.getString(GRID_CELL_SIZE));
		isVisible = true;
		myMesh = new ArrayList<List<Point2D>>();
		myCanvasWidth = width;
		myCanvasHeight = height;
		populateMesh();
	}
	
	public double getCellSize() {
		return myCellSize;
	}
	
	public void setCellSize(double size) {
		myCellSize = size;
		populateMesh();
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public List<List<Point2D>> getMesh() {
		return myMesh;
	}
	
	public void setSize(double width, double height) {
		myCanvasWidth = width; myCanvasHeight = height;
		populateMesh();
	}
	
	private void populateMesh() {
		myMesh.clear();
		double numColumns = myCanvasWidth / myCellSize;
		double numRows = myCanvasHeight / myCellSize;
		for (int i=0; i < numRows; i++) {
			myMesh.add(new ArrayList<Point2D>());
			for (int j=0; j < numColumns; j++) {
				List<Point2D> currentRow = myMesh.get(i);
				currentRow.add(new Point2D(j*myCellSize, i*myCellSize));
			}
		}
	}
	
	public void snapViewToGrid(DraggableView view) {
		Point2D viewPoint = new Point2D(view.getX(), view.getY());
		int[] closestCell = findClosestGridPoint(viewPoint);
		view.setX(closestCell[0]*myCellSize);
		view.setY(closestCell[1]*myCellSize);
	}
	
	public void snapObjectToGrid(DraggableNode object) {
		Point c = object.getBounds().centerPoint();
		Point tl = object.getBounds().topLeft();
		Point2D objectPoint = new Point2D(tl.x, tl.y);
		int[] closestCell = findClosestGridPoint(objectPoint);
		object.setX(closestCell[0]*myCellSize + c.x - tl.x);
		object.setY(closestCell[1]*myCellSize + c.y - tl.y);
	}
	
	private int[] findClosestGridPoint(Point2D objectPoint) {
		int[] closestCell = {0, 0};
		List<List<Double>> distances = mapDistancesOverGrid(objectPoint);
		double minDistance = Double.MAX_VALUE;
		for (int i=0; i < distances.size(); i++) {
			List<Double> row = distances.get(i);
			for (int j=0; j < row.size(); j++) {
				if (row.get(j) < minDistance) {
					minDistance = row.get(j);
					closestCell[0] = j;
					closestCell[1] = i;
				}
			}
		}
		return closestCell;
	}
	
	private List<List<Double>> mapDistancesOverGrid(Point2D objectPoint) {
		return myMesh.stream()
				.map(row -> row.stream().map(point -> calculateDistance(objectPoint, point)).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}
	
	private double calculateDistance(Point2D point0, Point2D point1) {
		return Math.sqrt(Math.pow(Math.abs(point1.getX()-point0.getX()), 2) + Math.pow(Math.abs(point1.getY()-point0.getY()), 2));
	}

}
