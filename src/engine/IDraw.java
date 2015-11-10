package engine;

import javafx.scene.canvas.Canvas;
import structures.run.RunRoom;

public interface IDraw {
	
	void draw(RunRoom myRoom);
	void setCanvas(Canvas myCanvas);

}
