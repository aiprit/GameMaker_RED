package structures.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import exceptions.ResourceFailedException;
import groovy.util.ResourceException;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
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

    @Override
    public void load(String directory) throws ResourceFailedException {
        String url = directory + myBaseFileName;
        try {
           // myImage = new Image(new FileInputStream(url));
            BufferedImage img = getImageFromFile(url);
            WritableImage wr = null;
            if (img != null) {
                wr = new WritableImage(img.getWidth(), img.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < img.getWidth(); x++) {
                    for (int y = 0; y < img.getHeight(); y++) {
                        pw.setArgb(x, y, img.getRGB(x, y));
                    }
                }
            }
            myImage = wr;
        } catch (Exception ex) {
            String message = "Failed to load image '%s' for DataSprite '%s'";
            throw new ResourceFailedException(message, url, myName);
        }
        myHaveLoaded = true;

    }
    private BufferedImage getImageFromFile (String filename) throws ResourceException {
        try {
            // first test if path is relative
            URL loc = getClass().getClassLoader().getResource(filename);
            if (loc != null) {
                filename = loc.toString();
            }
            // now assume it is canoncial
            BufferedImage image = ImageIO.read(new File(filename));
            while (image.getWidth(null) < 0) {
                // wait for size to be known
            }
            
            return image;
        }
        catch (Exception e) {
            throw new ResourceException(e);
        }
    }
}
