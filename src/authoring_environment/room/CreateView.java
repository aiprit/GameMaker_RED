package authoring_environment.room;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CreateView {
	
	
	public VBox create() {
		Rectangle rect = new Rectangle();
		VBox box = new VBox();
		rect.setWidth(200);
		rect.setHeight(200);
		rect.setFill(Color.TRANSPARENT);
		box.getChildren().add(rect);
		
		//box.getChildren().add(source);
		box.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
		return box;
	}
}
