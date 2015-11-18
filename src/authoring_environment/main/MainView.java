/**
 * 
 */
package authoring_environment.main;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author loganrooper
 *
 */
public class MainView {
	BorderPane bp;

	public MainView(Stage myStage) {
		ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
		bp = new BorderPane();
		int width = Integer.parseInt(r.getString("ViewWidth"));
		int height = Integer.parseInt(r.getString("ViewHeight"));
		Scene s = new Scene(bp, width, height, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
	}

	public void init() {
		
	}

	/**
	 * @param leftBP the left borderpane
	 */
	public void setPanes(BorderPane leftBP) {
		bp.setLeft(leftBP);
	}
}