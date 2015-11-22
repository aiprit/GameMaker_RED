package engine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import structures.run.RunView;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

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
		myGraphicsContext.save();
		myGraphicsContext.translate(x - disp.x(), y - disp.y());
		myGraphicsContext.rotate(-1 * angle);
		myGraphicsContext.scale(scaleX, scaleY);
		myGraphicsContext.drawImage(image, -1 * centerX, -1 * centerY);
		myGraphicsContext.restore();

	}
	
	@Override
	public void drawRectangle(IRectangle rect, RunView view, Paint paint) {
		Rectangle disp = view.getView();
		myGraphicsContext.setStroke(paint);
		myGraphicsContext.strokeRect(rect.x() - disp.x(), rect.y() - disp.y(), rect.width(), rect.height());
	}
	
	public void drawBackground(Image image, RunView view){
		Rectangle disp = view.getView();
		myGraphicsContext.clearRect(0, 0, disp.width(), disp.height());
	}
}