package structures.run;

import java.util.List;

public class RunRoom {
	
	String name;
	RunView myView;
	String background;
	List<RunObject> myObjects;
	
	public RunRoom() {
		
	}
	
	public String getBackground(){
		return background;
	}
	
	public List<RunObject> getObjects() {
		return myObjects;
	}

}
