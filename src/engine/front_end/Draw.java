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
import javafx.scene.transform.Rotate;
import structures.run.RunView;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

public class Draw extends StackPane implements IDraw {

	private Canvas myCanvas;
	private GraphicsContext myGraphicsContext;

	public Draw(Canvas canvas) {
		myCanvas = canvas;
		myGraphicsContext = canvas.getGraphicsContext2D();
		this.getChildren().add(myCanvas);
	}

	@Override
	public void drawImage(	Image image, RunView view, double x, double y,
			double centerX, double centerY,
			double scaleX, double scaleY, double angle, double alpha) {

		//draw the new object
		Rectangle disp = view.getView();
		double tlx = x - disp.x();
		double tly = y - disp.y();
		myGraphicsContext.save();
		rotate(angle, tlx + image.getWidth() * scaleX / 2, tly + image.getHeight() * scaleY / 2);
		myGraphicsContext.setGlobalAlpha(alpha);
		myGraphicsContext.drawImage(image, tlx, tly, image.getWidth() * scaleX, image.getHeight() * scaleY);
		myGraphicsContext.restore();
	}
	
	private void rotate(double angle, double pivotX, double pivotY) {
		Rotate rot = new Rotate(angle, pivotX, pivotY);
		myGraphicsContext.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(),
				rot.getTy());
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
		myGraphicsContext.drawImage(image, -disp.x(), -disp.y(), roomWidth, roomHeight);
		myGraphicsContext.restore();
	}

	@Override
	public void drawBackgroundColor(String color, RunView view) {
		Rectangle disp = view.getView();
		myGraphicsContext.setFill(Color.valueOf(color));
		myGraphicsContext.fillRect(0, 0, disp.width(), disp.height());
	}

	@Override
	public void drawText(String message, RunView view) {
		Rectangle disp = view.getView();
		myGraphicsContext.setFill(Color.BLACK);
		myGraphicsContext.setFont(new Font("Times New Roman", disp.width()/message.length()));
		myGraphicsContext.fillText(message, 40, 100);
	}
}