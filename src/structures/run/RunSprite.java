package structures.run;

import engine.front_end.IDraw;
import exceptions.CompileTimeException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import structures.data.DataSprite;
import utils.rectangle.IRectangle;

public class RunSprite implements IDrawable {
	
	public final String name;
	
	public double centerX;
	public double centerY;
	
	private Image myImage;
	private DataSprite myDataSprite;
	
	public RunSprite(String name) {
		this.name = name;
	}
	
	public RunSprite(DataSprite dataSprite) throws CompileTimeException {
		name = dataSprite.getName();
		if (!dataSprite.loaded()) {
			throw new CompileTimeException("Can't create RunSprite from unloaded DataSprite '%s'", name);
		}
		myImage = dataSprite.getImage();
		centerX = dataSprite.getCenterX();
		centerY = dataSprite.getCenterY();
	}
	
	public DataSprite getData() {
		return myDataSprite;
	}

	@Override
	public void draw(IDraw drawListener, RunView view, RunObject object) {
		IRectangle bounds = object.getBounds();
		drawListener.drawImage(myImage, view, object.getX(), object.getY(), bounds.centerX(), bounds.centerY(), object.getScaleX(), object.setScaleY(), object.getAngle(), object.getAlpha());
	}
	
	public double getWidth() {
		return myImage.getWidth();
	}
	
	public double getHeight() {
		return myImage.getHeight();
	}
	
	public Image getImage() {
	    return myImage;
	}
	
}
