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
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataSprite;

public class SpriteMaker {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static void load(Stage s, ObservableList<DataSprite> sprites){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("fileChooserTitle");
		
		File selectedFile = fileChooser.showOpenDialog(s);
		BufferedImage img;
		
		try {
			
			img = ImageIO.read(selectedFile);
			String name = askName();
			File outputfile = new File(r.getString("imagesFolder") + name + ".png");
			
		    ImageIO.write(img, "png", outputfile);
		    DataSprite newSprite = new DataSprite(name, outputfile.getName());

		    sprites.add(newSprite);
		    
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void show(DataSprite sprite){
		BorderPane myPane = new BorderPane();
		Stage s = new Stage();
		Image img = sprite.getImage();
		Canvas c = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext centerGC = c.getGraphicsContext2D();
	

		double xPos = img.getWidth()/2;
		double yPos = img.getHeight()/2;
		centerGC.drawImage(img, xPos, yPos); 
		myPane.setCenter(c);

		s.setScene(new Scene(myPane));
		s.show();
	}
	private static String askName(){
		TextInputDialog dialog = new TextInputDialog("Sprite Name");
		dialog.setTitle("Sprite Selector");
		dialog.setHeaderText("Sprite");
		dialog.setContentText("Please enter your sprite name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return result.get();
		}
		else 
			return "IMAGE";
		
		
	}
}
