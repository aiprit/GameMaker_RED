package engine.front_end;

import java.util.List;

import engine.social_player.PlayerManager;
import engine.social_player.PlayerPreference;
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
		List<PlayerPreference> preferences = myPlayers.getPreferences();
		PreferenceViewFactory preferenceViewFactory = new PreferenceViewFactory();
		for(PlayerPreference p : preferences){
			PreferenceView pv = preferenceViewFactory.getPreferenceView(p, myPlayers, myView);
			this.getChildren().add(pv.getNode());
		}

	}

}
