package engine.front_end;

import java.awt.image.BufferedImage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import structures.run.RunView;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

public class Draw extends StackPane implements IDraw {

	private Canvas myCanvas;
	private GraphicsContext myGraphicsContext;

	public Draw(Canvas canvas) {
		myCanvas = canvas;
		myGraphicsContext = canvas.getGraphicsContext2D();
		myCanvas.setWidth(500);
		myCanvas.setHeight(500);
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

	public void drawBackgroundImage(Image image, RunView view, double roomWidth, double roomHeight){
		Rectangle disp = view.getView();
		myCanvas.setWidth(disp.width());
		myCanvas.setHeight(disp.height());
		myGraphicsContext.save();
		myGraphicsContext.clearRect(0, 0, disp.width(), disp.height());
		myGraphicsContext.drawImage(image, -disp.x(), 0, roomWidth, roomHeight);
		myGraphicsContext.restore();
	}

	@Override
	public void drawBackgroundColor(String color, RunView view) {
		Rectangle disp = view.getView();
		myGraphicsContext.setFill(Color.valueOf(color));
		myGraphicsContext.fillRect(disp.x(), disp.y(), disp.width(), disp.height());
	}

	@Override
	public void drawText(String message, RunView view) {
		Rectangle disp = view.getView();
		myGraphicsContext.setFill(Color.BLACK);
		myGraphicsContext.setFont(new Font("Times New Roman", disp.width()/message.length()));
		myGraphicsContext.fillText(message, 40, 100);
	}
}