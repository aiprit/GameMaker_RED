package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoomBackground extends Rectangle {

	public RoomBackground(ResourceBundle resources) {
		super(Double.parseDouble(resources.getString("PreviewWidth")), 
				Double.parseDouble(resources.getString("PreviewHeight"))-1);
		this.setFill(Color.ANTIQUEWHITE);
	}
	
}
