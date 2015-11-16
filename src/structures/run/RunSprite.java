package structures.run;

import engine.IDraw;
import exceptions.CompileTimeException;
import javafx.scene.image.Image;
import structures.data.DataSprite;

public class RunSprite implements IDrawable {
	
	public final String name;
	
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
	}
	
	public DataSprite getData() {
		return myDataSprite;
	}

	@Override
	public void draw(IDraw drawInterface, RunView view, RunObject object) {
		// TODO Draw this Sprite!
		
	}
	
}
