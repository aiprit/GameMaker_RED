package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.events.GlobalMousePressedEvent;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.ObjectMousePressedEvent;
import utils.Point;

public class InputEventFactory {
	
	public static List<IDataEvent> getEvents(InputEvent e){
		List<IDataEvent> myEvents = new ArrayList<>();
		if(e instanceof KeyEvent) {
			KeyEvent ke = ((KeyEvent) e);
			if (ke.getEventType().toString().equals("KEY_PRESSED")){
				myEvents.add(new KeyPressedEvent(ke.getCode()));
			}
			else if(ke.getEventType().toString().equals("KEY_RELEASED")){
				myEvents.add(new KeyReleasedEvent(ke.getCode()));
			}
		}
		else if(e instanceof MouseEvent){
			MouseEvent me = ((MouseEvent) e);
			if(me.getEventType().toString().equals("MOUSE_PRESSED")){
				if(me.getButton().toString().equals("PRIMARY")){
					myEvents.add(new ObjectMousePressedEvent("Left"));
					myEvents.add(new GlobalMousePressedEvent("Left"));
				}
				else if(me.getButton().toString().equals("SECONDARY")){
					myEvents.add(new ObjectMousePressedEvent("Right"));
					myEvents.add(new GlobalMousePressedEvent("Right"));
				}
			}
		}
		return myEvents;
	}
	
	public static Point getCoordinates(InputEvent e){
		MouseEvent me = (MouseEvent) e;
		return new Point(me.getSceneX(), me.getSceneY());
	}

}
