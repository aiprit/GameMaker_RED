package engineInterfaces;

import javafx.scene.canvas.Canvas;

public interface IEngine {
	
	void setGame(DataGame myGame);
	void setCanvas(Canvas myCanvas);
	DataGame save();
	void reset();
	
}
