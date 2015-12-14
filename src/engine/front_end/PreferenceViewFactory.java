package engine.front_end;

import java.util.List;

import engine.social_player.PlayerManager;
import engine.social_player.PlayerPreference;

public class PreferenceViewFactory {
	
	public PreferenceView getPreferenceView(PlayerPreference p, PlayerManager players, HighScoreView view){
		if(p.getTextKey().equals("color")){
			return new ColorPreferenceView(p.getPreference(), players, view);
		} else {
			return null;
		}
	}

}
