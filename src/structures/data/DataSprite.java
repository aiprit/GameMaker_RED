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
        myHaveLoaded = false;
        myCenterX = 0.0;
        myCenterY = 0.0;
        myScaleX = 1.0;
        myScaleY = 1.0;
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

    public double getScaleX() {
        return myScaleX;
    }

    public void setScaleX(double scale) {
        myScaleX = scale;
    }

    public double getScaleY() {
        return myScaleY;
    }

    public void setScaleY(double scale) {
        myScaleY = scale;
    }

    public String getBaseFileName() {
        return myBaseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        myBaseFileName = baseFileName;
    }

    public double getCenterX() {
        return myCenterX;
    }

    public void setCenterX(double centerX) {
        myCenterX = centerX;
    }

    public double getCenterY() {
        return myCenterY;
    }

    public void setCenterY(double centerY) {
        myCenterY = centerY;
    }

    @Override
    public boolean loaded() {
        return myHaveLoaded;
    }

    @Override
    public void load(String directory) throws ResourceFailedException {
        String url = directory + myBaseFileName;
        try {
            myImage = new Image(getClass().getClassLoader().getResourceAsStream(url));
        } catch (Exception ex) {
            String message = "Failed to load image '%s' for DataSprite '%s'";
            throw new ResourceFailedException(message, url, myName);
        }
        myHaveLoaded = true;
    }
}
