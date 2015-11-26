package engine.front_end;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import structures.run.RunView;
import utils.rectangle.IRectangle;

public interface IDraw {
	
	void drawImage(Image image, RunView view, double x, double y, double centerX, double centerY, double scaleX, double scaleY, double angle);
	void drawBackgroundImage(Image image, RunView view, double roomWidth, double roomHeight);
	void drawBackgroundColor(String color, RunView view);
	void drawRectangle(IRectangle rectangle, RunView view, Paint paint);
	void drawText(String message, RunView view);
	
}
