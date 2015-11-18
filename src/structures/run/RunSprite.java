package structures.run;

import engine.IDraw;
import engine.events.EventManager;
import exceptions.CompileTimeException;
import javafx.scene.image.Image;
import structures.data.DataSprite;

public class RunSprite implements IDrawable {
	
	public final String name;
	
	public double centerX;
	public double centerY;
	
	private Image myImage;
	private DataSprite myDataSprite;
	
	private double width;
	private double height;
	
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
	public void draw(EventManager eventManager, RunView view, RunObject object) {
		eventManager.drawImage(myImage, view, object.x, object.y, centerX, centerY, object.scaleX, object.scaleY, object.angle);
	}
	
	public double getWidth() {
		return myImage.getWidth();
	}
	
	public double getHeight() {
		return myImage.getHeight();
	}
	
}
