// This entire file is part of my masterpiece.
// Logan Rooper

package authoring_environment.FileHandlers;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataSprite;

/**
 * @author loganrooper
 *
 */
public class SpriteView {
	public void draw(DataSprite sprite) {
		BorderPane myPane = new BorderPane();
		Stage s = new Stage();
		Image img = sprite.getImage();
		Canvas c = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext centerGC = c.getGraphicsContext2D();
		centerGC.drawImage(img, 0, 0);
		myPane.setCenter(c);
		s.setScene(new Scene(myPane));
		s.show();
	}
}
