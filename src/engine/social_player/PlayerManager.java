package engine.social_player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

	private String name;
	private String game;

	private Map<String, Player> players;

	public PlayerManager() throws IOException{
		name = "";
		game = "";
		players = new HashMap<String, Player>();

		InputStream serverInfo = this.getClass().getClassLoader()
				.getResourceAsStream("engine/social_player/fakeserver.txt");
		InputStreamReader is = new InputStreamReader(serverInfo);
		BufferedReader br = new BufferedReader(is);

		String read = br.readLine();
		while(read != null){
			String name = read;
			players.put(name, new Player());
			read = br.readLine();
			while(!read.equals(";")){
				String game = read.substring(0, read.indexOf(","));
				String score = read.substring(read.indexOf(",") + 1);
				double scoreDouble = Double.parseDouble(score);
				setPlayerHighScore(name, game, scoreDouble);
				read = br.readLine();
			}
			read = br.readLine();
		}
	}

	public void setPlayer(String name){
		if(!players.containsKey(name) && !name.equals("")){
			players.put(name, new Player());
			setPlayerHighScore(name, game, null);
		}
		this.name = name;
	}

	public void setGame(String game){
		this.game = game;
	}

	public String getPlayerName(){
		return name;
	}

	public Double getPlayerHighScore(){
		if(players.containsKey(name)){
			return players.get(name).getHighScore(game);
		}
		else {
			return null;
		}
	}

	public void setPlayerHighScore(double score){
		setPlayerHighScore(game, score);
	}

	public void setPlayerHighScore(String game, double score){
		setPlayerHighScore(name, game, score);
	}

	public void setPlayerHighScore(String name, String game, Double score){
		players.get(name).setHighScore(game, score);
	}

	public void clearInfo(){
		this.name = "";
	}

	public Map<String, String> getAllPlayers(){
		Map<String, String> playerInfo = new HashMap<String, String>();
		for(String s : players.keySet()){
			if(players.get(s).contains(game)){
				playerInfo.put(s, players.get(s).getHighScore(game) + "");
			}
		}
		return playerInfo;
	}

}
