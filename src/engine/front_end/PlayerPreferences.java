package engine.front_end;

import java.util.List;

import engine.social_player.PlayerManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerPreferences extends VBox {

	private PlayerManager myPlayers;
	private List<String> colors;
	private ListView<String> lv;
	private String selectedString;
	private HighScoreView myView;

	public PlayerPreferences(PlayerManager myPlayers, HighScoreView myView){
		this.myPlayers = myPlayers;
		this.myView = myView;
		colors = myPlayers.getColors();
		selectedString = "";
		this.setWidth(100);
		this.setHeight(100);
		this.setStyle("-fx-background-color: #FFFFFF");
		updateView();
	}

	public void updateView(){
		this.getChildren().clear();
		Text preferencesTitle = new Text("Preferences for " + myPlayers.getPlayerName());
		this.getChildren().add(preferencesTitle);
		Text colorTitle = new Text("\nTheme preference: ");
		this.getChildren().add(colorTitle);
		lv = new ListView<>();
		lv.setPrefSize(200, 250);
		lv.setEditable(true);
		lv.setItems(FXCollections.observableArrayList(colors));

		lv.getSelectionModel().selectedItemProperty().addListener(
	            new ChangeListener<String>() {
	                public void changed(ObservableValue<? extends String> ov, 
	                    String old_val, String new_val) {
	                        System.out.println(new_val);
	                        myPlayers.setColorPreference(new_val);
	                        myView.updateColor(new_val);
	            }
	        });
		
		lv.getSelectionModel().select(myPlayers.getColorPreference());

		this.getChildren().add(lv);

	}

}
