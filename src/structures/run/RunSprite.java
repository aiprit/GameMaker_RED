package structures.run;

import java.awt.image.BufferedImage;

import engine.front_end.IDraw;
import exceptions.CompileTimeException;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import structures.data.DataSprite;

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
		BufferedImage img = SwingFXUtils.fromFXImage(myImage, null);
		drawListener.drawImage(myImage, view, object.x, object.y, centerX, centerY, object.scaleX, object.scaleY, object.angle);
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
