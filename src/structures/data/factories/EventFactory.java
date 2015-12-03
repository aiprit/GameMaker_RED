package structures.data.factories;

import javafx.scene.input.KeyCode;
import org.w3c.dom.Element;
import structures.data.DataGame;
import structures.data.events.CollisionEvent;
import structures.data.events.GameEndEvent;
import structures.data.events.GameStartEvent;
import structures.data.events.GlobalMousePressedEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;
import structures.data.events.LeaveRoomEvent;
import structures.data.events.ObjectCreateEvent;
import structures.data.events.ObjectDestroyEvent;
import structures.data.events.ObjectMousePressedEvent;
import structures.data.events.StepEvent;
import structures.data.interfaces.IDataEvent;

public class EventFactory {

    public IDataEvent getEvent(Element e, DataGame g){
        String className = e.getAttribute("class");

        if(className.equals("structures.data.events.CollisionEvent")){
            return new CollisionEvent(g.getObjectFromString(e.getAttribute("dataObject")));
        } else if(className.equals("structures.data.events.GameEndEvent")){
            return new GameEndEvent();
        } else if(className.equals("structures.data.events.GameStartEvent")){
            return new GameStartEvent();
        } else if(className.equals("structures.data.events.GlobalMousePressedEvent")){
            return new GlobalMousePressedEvent(e.getAttribute("mouseState"));
        } else if(className.equals("structures.data.events.KeyPressedEvent")){
            return new KeyPressedEvent(KeyCode.valueOf(e.getAttribute("keyCode")));
        } else if(className.equals("structures.data.events.KeyReleasedEvent")){
            return new KeyReleasedEvent(KeyCode.valueOf(e.getAttribute("keyCode")));
        } else if(className.equals("structures.data.events.LeaveRoomEvent")){
            return new LeaveRoomEvent();
        } else if(className.equals("structures.data.events.ObjectCreateEvent")){
            return new ObjectCreateEvent();
        } else if(className.equals("structures.data.events.ObjectDestroyEvent")){
            return new ObjectDestroyEvent();
        } else if(className.equals("structures.data.events.ObjectMousePressedEvent")){
            return new ObjectMousePressedEvent(e.getAttribute("mouseState"));
        } else if(className.equals("structures.data.events.StepEvent")){
            return new StepEvent();
        } else{
            return null;
        }
    }
}
