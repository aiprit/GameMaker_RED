package engine;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import structures.run.RunView;
import utils.rectangle.IRectangle;

public interface IDraw {
	
	void drawImage(Image image, RunView view, double x, double y, double centerX, double centerY, double scaleX, double scaleY, double angle);
	void drawBackground(Image image, RunView view);
	void drawRectangle(IRectangle rectangle, RunView view, Paint paint);
	
}
