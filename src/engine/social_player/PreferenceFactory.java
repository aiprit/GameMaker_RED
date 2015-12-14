package engine.social_player;

import java.io.BufferedReader;
import java.io.IOException;

public class PreferenceFactory {
	
	public PlayerPreference getPreference(String key, BufferedReader br) throws IOException{
		if(key.equals("color")){
			return new ColorPreference(br.readLine());
		} else {
			return null;
		}
	}

}
