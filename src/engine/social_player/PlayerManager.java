package engine.social_player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

	private String name;
	private String game;

	private Map<String, Player> players;
	private List<String> myColors;
	private String filePath;

	public PlayerManager() throws IOException{
		name = "";
		game = "";
		players = new HashMap<String, Player>();
		myColors = new ArrayList<>();

		myColors.add("Blue");
		myColors.add("Green");
		myColors.add("Gray");
		myColors.add("Pink");
		myColors.add("Purple");
		myColors.add("Red");
		myColors.add("Yellow");

		InputStream serverInfo = this.getClass().getClassLoader()
				.getResourceAsStream("engine/social_player/fakeserver.txt");
		InputStreamReader is = new InputStreamReader(serverInfo);
		BufferedReader br = new BufferedReader(is);

		String read = br.readLine();
		while(read != null){
			String name = read;
			players.put(name, new Player());
			read = br.readLine();
			players.get(name).setColorPreference(read);
			read = br.readLine();
			while(!read.equals(";")){
				String game = read.substring(0, read.indexOf(","));
				String score = read.substring(read.indexOf(",") + 1);
				Double scoreDouble;
				if(score.equals("null")){
					scoreDouble = null;
				} else {
					scoreDouble = Double.parseDouble(score);
				}
				setPlayerHighScore(name, game, scoreDouble);
				read = br.readLine();
			}
			read = br.readLine();
		}
		write();
	}

	public List<String> getColors(){
		return myColors;
	}

	public void setPlayer(String name){
		if(!players.containsKey(name) && !name.equals("")){
			players.put(name, new Player());
			setPlayerHighScore(name, game, null);
		}
		this.name = name;
		write();
	}

	public void setGame(String game){
		this.game = game;
	}

	public String getPlayerName(){
		return name;
	}

	public String getColorPreference(){
		return players.get(name).getColorPreference();
	}

	public void setColorPreference(String color){
		players.get(name).setColorPreference(color);
		write();
	}

	public boolean hasPlayer(){
		return players.containsKey(name);
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
		write();
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

	public void write(){
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/engine/social_player/fakeserver.txt"), "utf-8"));
			for(String s : players.keySet()){
				writer.write(s + "\n");
				writer.write(players.get(s).getColorPreference() + "\n");
				Map<String, Double> highScores = players.get(s).getHighScores();
				for(String g : highScores.keySet()){
					writer.write(g + "," + highScores.get(g) + "\n");
				}
				writer.write(";\n");
			}
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/*ignore*/
			}
		}
	}

}
