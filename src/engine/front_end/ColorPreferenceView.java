package engine.front_end;

import java.util.List;

import engine.social_player.PlayerManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ColorPreferenceView extends PreferenceView {
	
	private VBox preferenceList;
	
	public ColorPreferenceView(String colorPreference, PlayerManager players, HighScoreView view){
		
		preferenceList = new VBox(4);
		
		Text colorTitle = new Text("\nTheme preference: ");
		preferenceList.getChildren().add(colorTitle);
		ListView<String> lv = new ListView<>();
		lv.setPrefSize(200, 250);
		lv.setEditable(true);
		lv.setItems(FXCollections.observableArrayList(players.getColors()));

		lv.getSelectionModel().selectedItemProperty().addListener(
	            new ChangeListener<String>() {
	                public void changed(ObservableValue<? extends String> ov, 
	                    String old_val, String new_val) {
	                        System.out.println(new_val);
	                        players.setPreference("color", new_val);
	                        view.updateColor(new_val);
	            }
	        });
		
		lv.getSelectionModel().select(players.getPreference("color"));
		preferenceList.getChildren().add(lv);
		
	}

	@Override
	public Node getNode() {
		return preferenceList;
	}

}
