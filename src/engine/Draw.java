package engine;

import javafx.scene.Node;
import exceptions.CompileTimeException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import structures.run.RunGame;
import structures.run.RunRoom;

public class Draw extends StackPane implements IDraw {
	
	private Canvas myCanvas;
	
	public Draw() {
		myCanvas = new Canvas();
		this.getChildren().add(myCanvas);
	}

	@Override
	public void draw(RunGame game) throws CompileTimeException {
		//RunRoom myRoom = game.getCurrentRoom();
		
		//this.getChildren().remove(myCanvas);
		
		//this.setPrefWidth(200);
		//this.setPrefHeight(200);
		//this.setStyle("-fx-background-color: black;");
		
		myCanvas.setWidth(300);
		myCanvas.setHeight(300);
		GraphicsContext gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.TEAL);
		gc.strokeText("a text", 100, 100);
		gc.fillRect(0, 0, 20, 20);
		
		//if(myRoom.getBackground() != null){
			//my
		//}
		
	}
}