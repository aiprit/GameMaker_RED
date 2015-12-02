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

		InputStream s = this.getClass().getClassLoader()
				.getResourceAsStream("controllerUtils/controller.properties");
		xbox = new Properties();
		xbox.load(s);

		String controllerFile = xbox.getProperty("controller");

		InputStream commands = this.getClass().getClassLoader()
				.getResourceAsStream("controllerUtils/" + controllerFile);

		InputStreamReader is = new InputStreamReader(commands);
		BufferedReader br = new BufferedReader(is);
		List<String> components = new ArrayList<>();

		String read = br.readLine();
		while(read != null){
			//ignore the comments in the text file
			if(read.substring(0, 2).equals("//")){
				read = br.readLine();
			}
			else {
				if(read.contains("//")){
					read = read.substring(0, read.indexOf(" //"));
				}
				components.add(read);
				read = br.readLine();
			}
		}

		eventMap = new HashMap<String, String>();

		for(String component : components){
			eventMap.put(component, xbox.getProperty(component));
		}
	}

	public void executeEvent(FXRobot robot, Event event){

		if(eventMap.containsKey(event.getComponent().getName())){
			if(eventMap.get(event.getComponent().getName()) == null ||
					eventMap.get(event.getComponent().getName()).equals("")){
				return;
			}
			KeyCode kc = KeyCode.valueOf(eventMap.get(event.getComponent().getName()));
			robot.keyPress(kc);
			robot.keyRelease(kc);
		}

	}

}
