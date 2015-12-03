package authoring_environment.FileHandlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import exceptions.ResourceFailedException;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataSprite;

public class SpriteMaker {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static void load(Stage s, ObservableList<DataSprite> sprites){
		
		File selectedFile = FileHelper.choose(s);
		BufferedImage img;	
		try {
			try{
			img = ImageIO.read(selectedFile);


			String name = FileHelper.askName();
			File outputfile = new File(r.getString("imagesFolder") + name + ".png");	
		    ImageIO.write(img, "png", outputfile);
		    DataSprite newSprite = new DataSprite(name, outputfile.getName());
		    	try {
		    		newSprite.load(r.getString("imagesFolder"));
		    	} catch (ResourceFailedException e) {

		    		e.printStackTrace();
		    	}

		    sprites.add(newSprite);
			} catch(IllegalArgumentException e2){
				
			}  
		    
		} catch (IOException e1) {
				
		}
		
	}
	public static void show(DataSprite sprite){
		BorderPane myPane = new BorderPane();
		Stage s = new Stage();
		Image img = sprite.getImage();
	
		Canvas c = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext centerGC = c.getGraphicsContext2D();

		centerGC.drawImage(img, 0, 0); 

		myPane.setCenter(c);
		s.setScene(new Scene(myPane));
		s.show();
	}
	
}
