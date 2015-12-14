// This entire file is part of my masterpiece.
// Bailey Wall

package engine.social_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

	private Map<String, Double> highScore;
	private List<PlayerPreference> preferences;

	public Player(){
		highScore = new HashMap<>();
		preferences = new ArrayList<>();
	}

	public Double getHighScore(String game){
		if(highScore.containsKey(game)){
			return highScore.get(game);
		} else {
			return null;
		}
	}

	public Map<String, Double> getHighScores(){
		return highScore;
	}

	public void setHighScore(String game, Double score){
		highScore.put(game, score);
	}

	public boolean contains(String game){
		return highScore.containsKey(game);
	}

	public List<PlayerPreference> getPreferences(){
		return preferences;
	}

	public void addPreference(PlayerPreference pref){
		preferences.add(pref);
	}

	public String getPreference(String type){
		for(PlayerPreference p : preferences){
			if(p.getTextKey().equals(type)){
				return p.getPreference();
			}
		}
		return null;
	}

	public void setPreference(String type, String value){
		for(PlayerPreference p : preferences){
			if(p.getTextKey().equals(type)){
				p.setPreference(value);
				return;
			}
		}
	}

}
