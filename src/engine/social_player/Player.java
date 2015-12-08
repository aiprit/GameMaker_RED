package engine.social_player;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private Map<String, Double> highScore;
	private String colorPreference;

	public Player(){
		highScore = new HashMap<String, Double>();
		colorPreference = "red";
	}

	public Double getHighScore(String game){
		if(highScore.containsKey(game)){
			return highScore.get(game);
		} else {
			return null;
		}
	}

	public void setHighScore(String game, Double score){
		highScore.put(game, score);
	}
	
	public boolean contains(String game){
		return highScore.containsKey(game);
	}
	
	public String getColorPreference(){
		return colorPreference;
	}
	
	public void setColorPreference(String color){
		colorPreference = color;
	}

}
