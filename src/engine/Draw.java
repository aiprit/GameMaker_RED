package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import structures.run.RunGame;
import structures.run.RunRoom;

public class Draw implements IDraw {
	
	private Canvas myCanvas;
	
	public Draw(Canvas canvas) {
		myCanvas = canvas;
	}

	@Override
	public void draw(RunGame game) {
		RunRoom myRoom = game.getCurrentRoom();
		
		StackPane myPane = new StackPane();
		
		if(myRoom.getBackground() != null){
			//my
		}
	}
}