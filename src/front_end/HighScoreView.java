package front_end;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HighScoreView extends VBox {

	public HighScoreView(){
		this.setWidth(500);
		this.setHeight(50);
		Text scoreInfo = new Text("Your high score is:");
		this.getChildren().add(scoreInfo);
	}

	public void updateScore(double score){
		this.getChildren().clear();
		Text scoreInfo = new Text("Your high score is:\n" + score);
		this.getChildren().add(scoreInfo);
	}

}
