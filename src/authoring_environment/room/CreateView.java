package authoring_environment.room;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateView {
	
	
	public Rectangle create() {
		Rectangle rect = new Rectangle();
		rect.setWidth(500);
		rect.setHeight(500);
		rect.setFill(Color.BLUE);
		rect.setMouseTransparent(true);
		return rect;
	}
}
