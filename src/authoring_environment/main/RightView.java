/**
 * 
 */
package authoring_environment.main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author loganrooper
 *
 */
public class RightView {
	BorderPane rightPane;
	
	public RightView(SpriteListView spriteListView, SoundListView soundListView) {
		rightPane = new BorderPane();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(spriteListView.getPane(), soundListView.getPane());
		rightPane.setCenter(vbox);
	}
	
	public Pane getPane() {
		return rightPane;
	}
}
