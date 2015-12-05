package engine.social_player;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private Map<String, Double> highScore;

	public Player(){
		highScore = new HashMap<String, Double>();
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

}
