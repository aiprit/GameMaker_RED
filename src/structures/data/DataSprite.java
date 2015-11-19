package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSprite implements IResource {

	private String myBaseFileName;
	private String myName;
	private Image myImage;
	private double myCenterX, myCenterY, myScaleX, myScaleY;
	private boolean myHaveLoaded;
	
	public DataSprite(String name, String baseFileName) {
		myName = name;
		myBaseFileName = baseFileName;
		try {
			load("TestGame/sprites/");
		} catch (ResourceFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public double getScaleX(){
		return myScaleX;
	}

	public double getScaleY(){
		return myScaleY;
	}

	public void setScaleX(double scale){
		myScaleX = scale;
	}

	public void setScaleY(double scale){
		myScaleY = scale;
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

	@Override
	public void load(String directory) throws ResourceFailedException {
		myImage = new Image(getClass().getClassLoader().getResourceAsStream(myBaseFileName));
//        String url = directory + myBaseFileName;
//        try {
//            myImage = new Image(getClass().getClassLoader().getResourceAsStream(url));
//        } catch (Exception ex) {
//            String message = "Failed to load image '%s' for DataSprite '%s'";
//            throw new ResourceFailedException(message, url, myName);
//        }
//        myHaveLoaded = true;
    }
}
