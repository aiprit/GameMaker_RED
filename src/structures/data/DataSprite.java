package structures.data;

import exceptions.ResourceFailedException;
import authoring_environment.FileHandlers.FileManager;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import structures.IResource;

public class DataSprite implements IResource {

	private String myBaseFileName;
	private String myName;
	private WritableImage myImage;
	private double myCenterX, myCenterY;
	private boolean myHaveLoaded;

	public DataSprite(String name, String baseFileName) {
		myName = name;
		myBaseFileName = baseFileName;
		myHaveLoaded = false;
		myCenterX = 0.0;
		myCenterY = 0.0;

	}

	public Image getImage() {
		return myImage;
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}

	public String getBaseFileName() {
		return myBaseFileName;
	}

	public void setBaseFileName(String baseFileName) {
		myBaseFileName = baseFileName;
	}

	public void setCenterX(double centerX) {
		myCenterX = centerX;
	}

	public double getCenterX() {
		return myCenterX;
	}

	public void setCenterY(double centerY) {
		myCenterY = centerY;
	}

	public double getCenterY() {
		return myCenterY;
	}

	@Override
	public boolean loaded() {
		return myHaveLoaded;
	}

	public void load(String gameName) throws ResourceFailedException {
		FileManager gmf = new FileManager(gameName);
		myImage = gmf.getSprite(myName);
		myHaveLoaded = true;
	}

	@Override
	public String toString() {
		return getName();
	}
}
