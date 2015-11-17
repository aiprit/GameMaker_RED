package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import structures.run.RunRoom;

public class Draw extends StackPane implements IDraw {
	
	private Pane myBackgroundPane;
	private Canvas myCanvas;
	
	public Draw(Canvas canvas) {
		myCanvas = canvas;
		myCanvas.setWidth(400);
		myCanvas.setHeight(400);
		myBackgroundPane = new Pane();
		myBackgroundPane.setPrefWidth(400);
		myBackgroundPane.setPrefHeight(400);
		this.getChildren().add(myBackgroundPane);
		this.getChildren().add(myCanvas);
	}

	@Override
	public void draw(RunRoom room) {
		
		System.out.println("draw called");
		
		if(room.getBackground() != null){
			myBackgroundPane.setStyle("-fx-background-color: black;");
		}
		myBackgroundPane.setStyle("-fx-background-color: black;");
	}
}