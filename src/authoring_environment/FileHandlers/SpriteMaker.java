package authoring_environment.FileHandlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
	public static void load(Stage s, ObservableList<DataSprite> sprites){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		File selectedFile = fileChooser.showOpenDialog(s);
		BufferedImage img;
		
		try {
			
			img = ImageIO.read(selectedFile);
			String name = askName();
			File outputfile = new File("images/" + name + ".png");
			
		    ImageIO.write(img, "png", outputfile);
		    DataSprite newSprite = new DataSprite(name, outputfile.getName());
		    try {
				newSprite.load("images/");
			} catch (ResourceFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   // System.out.println(outputfile.getAbsolutePath());
		    sprites.add(newSprite);
		    
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void show(DataSprite sprite){
		BorderPane myPane = new BorderPane();
		Stage s = new Stage();
	//	Group root = new Group();
		Canvas c = new Canvas(100, 100);
		GraphicsContext centerGC = c.getGraphicsContext2D();
		Image img = sprite.getImage();

		double xPos = 50 - img.getWidth()/2;
		double yPos = 50 - img.getHeight()/2;
		centerGC.drawImage(img, xPos, yPos); //, myController.getSize()[0]*spriteWidth, myController.getSize()[1]*spriteHeight);
//		spriteUpdate = new Button(centerResources.getString("buttonText"));
		//root.getChildren().addAll(c);
		myPane.setCenter(c);
//		root.getChildren().add(myPane);
		s.setScene(new Scene(myPane));
		s.show();
	}
	private static String askName(){
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return result.get();
		}
		else 
			return "IMAGE";
		
		
	}
}
