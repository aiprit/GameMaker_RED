package structures.run;

import java.util.Map;
import java.util.Vector;

import javafx.event.EventType;
import javafx.scene.image.ImageView;

public class RunObject {
	
	String name;
	ImageView mySprite;
	double width;
	double height;
	double rotation;
	Vector velocity;
	Map<EventType, RunAction> myEvents;

}
