package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private Group myRoot;
	private ResourceBundle myResources;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		super.setMinHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setMinWidth(Double.parseDouble(resources.getString(PREVIEW_WIDTH)));
		myResources = resources;
		myRoot = new Group();
		handleNodes();
	}
	
	
	private void handleNodes() {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("Luigi.png"));
		ImageView imageView = new ImageView(image);
		Image second = new Image(getClass().getClassLoader().getResourceAsStream("Mario.png"));
		ImageView mario = new ImageView(second);
		myRoot.getChildren().add(mario);
		myRoot.getChildren().add(imageView);
		imageView.setX(500);
		imageView.setY(500);
		//super.setContent(imageView);
		super.setContent(myRoot);
		System.out.println(myRoot.getChildren().toString());
		for (Node n : myRoot.getChildren()) {
			n.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("here");
					double horizontalCorrection = Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
					double dragX = event.getSceneX() - horizontalCorrection; //where dragging
					double dragY = event.getSceneY(); //where dragging
					System.out.println("Event x is " + dragX);
					System.out.println("Event y is " + dragY);
					Node target = null;
//					for (Node n : myRoot.getChildren()) {
//						System.out.println(n.contains(x, y));
//						System.out.println("Image x is " + ((ImageView) n).getX());
//						System.out.println("Image Y is " + ((ImageView) n).getY());
//						if (n.contains(x, y))
//							target = n;
//					}
					//System.out.println(target == null);
						
					((ImageView) n).setX(dragX);
					((ImageView) n).setY(dragY);
					
				}
			});
		}
	}
	
	public void addNode(Node element) {
		myRoot.getChildren().add(element);
	}
	
	public void removeNode(Node element) {
		myRoot.getChildren().remove(element);
	}

}
