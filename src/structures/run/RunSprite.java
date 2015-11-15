package structures.run;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import structures.data.DataSprite;

public class RunSprite {
    
    private String myName;
    private Image myImage;
    
    public RunSprite(DataSprite dataSprite) throws ResourceFailedException {
        myName = dataSprite.getName();
        myImage = dataSprite.getImage();
    }
    
    public DataSprite toData() {
        return new DataSprite(myName);
    }
    
    public ImageView getImageView() {
        return new ImageView(myImage);
    }

}
