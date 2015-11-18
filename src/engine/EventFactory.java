package engine;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.MousePressedEvent;

public class EventFactory {
	
	public IDataEvent getEvent(InputEvent e){
		if(e instanceof KeyEvent){
			KeyEvent ke = ((KeyEvent) e);
			if (ke.getEventType().toString().equals("KEY_PRESSED")){
				return new KeyPressedEvent(ke.getCode());
			}
			else if(ke.getEventType().toString().equals("KEY_RELEASED")){
				return new KeyReleasedEvent(ke.getCode());
			}
		}
		else if(e instanceof MouseEvent){
			MouseEvent me = ((MouseEvent) e);
			if(me.getEventType().toString().equals("MOUSE_PRESSED")){
				if(me.getButton().toString().equals("PRIMARY")){
					return new MousePressedEvent("Left");
				}
				if(me.getButton().toString().equals("SECONDARY")){
					return new MousePressedEvent("Right");
				}
			}
		}
		return null;
	}

}
