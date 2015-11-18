package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import structures.run.RunView;
import utils.Rectangle;

public class Draw extends StackPane implements IDraw {
	
	private Pane myBackgroundPane;
	private Canvas myCanvas;
	private GraphicsContext myGraphicsContext;
	
	public Draw(Canvas canvas) {
		myCanvas = canvas;
		myGraphicsContext = canvas.getGraphicsContext2D();
        myCanvas.setWidth(1000);
        myCanvas.setHeight(1000);
        myBackgroundPane = new Pane();
        myBackgroundPane.setPrefWidth(1600);
        myBackgroundPane.setPrefHeight(1600);
        this.getChildren().add(myBackgroundPane);
        this.getChildren().add(myCanvas);
	}

	@Override
	public void drawImage(	Image image, RunView view, double x, double y,
							double centerX, double centerY,
							double scaleX, double scaleY, double angle) {

		Rectangle disp = view.getView();
		myGraphicsContext.save();
		myGraphicsContext.translate(-1 * x + disp.x(), -1 * y + disp.y());
		myGraphicsContext.rotate(-1 * angle);
		myGraphicsContext.scale(1.0 / scaleX, 1.0 / scaleY);
		myGraphicsContext.drawImage(image, -1 * centerX, -1 * centerY);
		myGraphicsContext.restore();

	}
}