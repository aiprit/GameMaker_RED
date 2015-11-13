package structures.run;

import java.util.List;

import javafx.scene.image.ImageView;

public class RunRoom {
	
	String name;
	RunView myView;
	ImageView myBackground;
	
	private List<RunObject> myObjects;
	
	public RunRoom() {
		
	}
	
	public List<RunObject> getObjects() {
		return myObjects;
	}

}
