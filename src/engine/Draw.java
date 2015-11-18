package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import structures.run.RunView;
import utils.Rectangle;

public class Draw extends StackPane implements IDraw {
	
	private Canvas myCanvas;
	private GraphicsContext myGraphicsContext;
	
	public Draw(Canvas canvas) {
		myCanvas = canvas;
		myGraphicsContext = canvas.getGraphicsContext2D();
        myCanvas.setWidth(1000);
        myCanvas.setHeight(1000);
        this.getChildren().add(myCanvas);
	}

	@Override
	public void drawImage(	Image image, RunView view, double x, double y,
							double centerX, double centerY,
							double scaleX, double scaleY, double angle) {
		
		//draw the new object
		Rectangle disp = view.getView();
		myGraphicsContext.setFill(Color.BEIGE);
		myGraphicsContext.fillRect(0, 0, disp.width(), disp.height());
		
		myGraphicsContext.save();
		myGraphicsContext.translate(x - disp.x(), y - disp.y() + 200);
		myGraphicsContext.rotate(-1 * angle);
		myGraphicsContext.scale(1.0 / scaleX, 1.0 / scaleY);
		myGraphicsContext.drawImage(image, -1 * x, -1 * y);
		//myGraphicsContext.drawImage(image, -1 * centerX, -1 * centerY);
		myGraphicsContext.restore();

	}
}