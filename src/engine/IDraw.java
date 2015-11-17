package engine;

import javafx.scene.image.Image;
import structures.run.RunView;

public interface IDraw {
	
	void drawImage(Image image, RunView view, double x, double y, double centerX, double centerY, double scaleX, double scaleY, double angle);

}
