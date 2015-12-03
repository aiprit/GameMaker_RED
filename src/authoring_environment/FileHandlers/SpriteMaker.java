package authoring_environment.FileHandlers;

import java.io.File;
import java.util.Optional;
import game_file_manager.GameFileManager;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSprite;

public class SpriteMaker {
<<<<<<< HEAD
	public static void load(Stage s, ObservableList<DataSprite> sprites) {
		File selectedFile = FileHelper.choose(s);
		GameFileManager gfm = new GameFileManager();

		Optional<DataSprite> ds = gfm.makeSprite(selectedFile, FileHelper.askName());
		if (ds.isPresent()) {
			sprites.add(ds.get());
		} else {
			// Try again
			load(s, sprites);
=======
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static void load(Stage s, DataGame game){
		
		File selectedFile = FileHelper.choose(s);
		BufferedImage img;	
		try {
			try{
				img = ImageIO.read(selectedFile);

				String name = FileHelper.askName();
				File outputfile = new File(r.getString("Games")+ game.getName() + r.getString("imagesFolder") + name + ".png");	
				ImageIO.write(img, "png", outputfile);
				DataSprite newSprite = new DataSprite(name, outputfile.getName());
					try {
						newSprite.load(r.getString("Games")+ game.getName() +  r.getString("imagesFolder"));
					} catch (ResourceFailedException e) {

						e.printStackTrace();
					}
					
					game.getSprites().add(newSprite);
				} catch(IllegalArgumentException e2){
				
				}  
		    
		} catch (IOException e1) {
				
>>>>>>> master
		}
	}

	public static void show(DataSprite sprite) {
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