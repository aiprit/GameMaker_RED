package engine;

import javafx.scene.canvas.Canvas;
import structures.data.DataGame;

public interface IEngine {
	
	void setGame(DataGame myGame);
	void setCanvas(Canvas myCanvas);
	DataGame save();
	void reset();
	
}
