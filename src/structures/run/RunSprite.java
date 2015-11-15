package structures.run;

import engine.IDraw;
import exceptions.CompileTimeException;
import javafx.scene.image.Image;
import structures.data.DataSprite;

public class RunSprite {
	
	public final String name;
	
	private Image myImage;
	private DataSprite myDataSprite;
	private IDraw myDrawInterface;
	
	public RunSprite(IDraw drawInterface, String name) {
		this.name = name;
		myDrawInterface = drawInterface;
	}
	
	public RunSprite(IDraw drawInterface, DataSprite dataSprite) throws CompileTimeException {
		name = dataSprite.getName();
		if (!dataSprite.loaded()) {
			throw new CompileTimeException("Can't create RunSprite from unloaded DataSprite '%s'", name);
		}
		myImage = dataSprite.getImage();
		myDrawInterface = drawInterface;
	}
	
	public void draw(double x, double y, double xScale, double yScale) {
		// TODO: draw this RunSprite!
	}
	
	public DataSprite getData() {
		return myDataSprite;
	}
	
}
