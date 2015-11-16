package structures.run;

import java.util.Map;
import structures.IObject;
import structures.data.events.IDataEvent;
import utils.Vector;

public class RunObject {
	
	public final String name;
	public double scaleX;
	public double scaleY;
	public double angle;
	public Vector velocity;
	
	private RunSprite mySprite;
	private Map<IDataEvent, RunAction> myEvents;
	
	public RunObject(String name) {
		this.name = name;
	}
	
	public void trigger(IDataEvent event) {
		// TODO: Groovy run event
	}
	
	public IObject toData() {
		// TODO: What the hell is this method for?
		return null;
	}

}