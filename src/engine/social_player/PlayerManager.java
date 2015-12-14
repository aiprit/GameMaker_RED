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
import java.util.ResourceBundle;

public class PlayerManager {

	private String name;
	private String game;

	private Map<String, Player> players;
	private List<String> myColors;
	private List<PlayerPreference> myDefaultPreferences;
	private ResourceBundle r = ResourceBundle.getBundle("engine/front_end/colors");

	public PlayerManager() throws IOException{
		//the default is to start out with nobody logged in
		name = "";
		game = "";
		players = new HashMap<String, Player>();
		myColors = new ArrayList<>();
		myDefaultPreferences = new ArrayList<>();

		for(String s : r.keySet()){
			myColors.add(r.getString(s));
		}

		InputStream serverInfo = this.getClass().getClassLoader()
				.getResourceAsStream("engine/social_player/newfakeserver.txt");
		InputStreamReader is = new InputStreamReader(serverInfo);
		BufferedReader br = new BufferedReader(is);

		PreferenceFactory preferenceFactory = new PreferenceFactory();

		String read = br.readLine();
		while(read != null){
			if(read.equals("default")){
				setupDefaults(br);
				read = br.readLine();
			}
			String name = read;
			players.put(name, new Player());
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
			while(!read.equals(";")){
				players.get(name).addPreference(preferenceFactory.getPreference(read, br));
				read = br.readLine();
			}
			read = br.readLine();
		}
		addDefaults();
		write();
	}

	public void setupDefaults(BufferedReader br) throws IOException{
		PreferenceFactory preferenceFactory = new PreferenceFactory();
		String read = br.readLine();
		while(!read.equals(";")){
			myDefaultPreferences.add(preferenceFactory.getPreference(read, br));
			read = br.readLine();
		}
	}

	public void addDefaults(){
		for(String n : players.keySet()){
			for(PlayerPreference p : myDefaultPreferences){
				if(players.get(n).getPreference(p.getTextKey()) == null){
					players.get(n).addPreference(p);
				}
			}
		}
	}

	public List<String> getColors(){
		return myColors;
	}

	public void setPlayer(String name){
		if(!players.containsKey(name) && !name.equals("")){
			players.put(name, new Player());
			setPlayerHighScore(name, game, null);
			addDefaults();
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

	public List<PlayerPreference> getPreferences(){
		return players.get(name).getPreferences();
	}

	public String getPreference(String type){
		return players.get(name).getPreference(type);
	}

	public void setPreference(String type, String value){
		players.get(name).setPreference(type, value);
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
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/engine/social_player/newfakeserver.txt"), "utf-8"));
			writer.write("default\n");
			for(PlayerPreference p : myDefaultPreferences){
				writer.write(p.getTextKey() + "\n");
				writer.write(p.getPreference() + "\n");
			}
			writer.write(";\n");
			for(String s : players.keySet()){
				writer.write(s + "\n");
				Map<String, Double> highScores = players.get(s).getHighScores();
				for(String g : highScores.keySet()){
					writer.write(g + "," + highScores.get(g) + "\n");
				}
				writer.write(";\n");
				List<PlayerPreference> preferences = players.get(s).getPreferences();
				for(PlayerPreference p : preferences){
					writer.write(p.getTextKey() + "\n");
					writer.write(p.getPreference() + "\n");
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
