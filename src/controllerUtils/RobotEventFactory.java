package controllerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sun.javafx.robot.FXRobot;

import javafx.scene.input.KeyCode;
import net.java.games.input.Event;

public class RobotEventFactory {
	
	private Properties xbox;
	private Map<String, String> eventMap;
	
	public RobotEventFactory() throws IOException{
		InputStream commands = this.getClass().getClassLoader()
		.getResourceAsStream("controllerUtils/controllerAttributes.txt");
		
		InputStreamReader is = new InputStreamReader(commands);
		BufferedReader br = new BufferedReader(is);
		List<String> components = new ArrayList<>();

		String read = br.readLine();
		while(read != null){
			if(read.contains("//")){
				read = read.substring(0, read.indexOf(" //"));
			}
			components.add(read);
			read = br.readLine();
		}
		
		InputStream s = this.getClass().getClassLoader()
        .getResourceAsStream("controllerUtils/xbox.properties");
		xbox = new Properties();
		xbox.load(s);
		eventMap = new HashMap<String, String>();
		
		for(String component : components){
			eventMap.put(component, xbox.getProperty(component));
		}
	}
	
	public void executeEvent(FXRobot robot, Event event){
		
		if(eventMap.containsKey(event.getComponent().getName())){
			if(eventMap.get(event.getComponent().getName()).contains("Key Press")){
				KeyCode kc = KeyCode.valueOf(eventMap.get(event.getComponent().getName()).substring(10));
				robot.keyPress(kc);
				robot.keyRelease(kc);
			}
		}
		
	}

}
