package structures.data.events;

import javafx.scene.input.KeyCode;
import org.w3c.dom.Element;

public class EventFactory {

    public IDataEvent getEvent(Element e){
        String className = e.getAttribute("class");

        if(className.equals("structures.data.events.CollisionEvent")){
            return new CollisionEvent(e.getAttribute("dataObject"));
        } else if(className.equals("structures.data.events.GameEndEvent")){
            return new GameEndEvent();
        } else if(className.equals("structures.data.events.GameStartEvent")){
            return new GameStartEvent();
        } else if(className.equals("structures.data.events.GlobalMousePressedEvent")){
            return new GlobalMousePressedEvent(e.getAttribute("mouseState"));
        } else if(className.equals("structures.data.events.KeyPressedEvent")){
            return new KeyPressedEvent(KeyCode.getKeyCode(e.getAttribute("keyCode")));
        } else if(className.equals("structures.data.events.KeyReleasedEvent")){
            return new KeyReleasedEvent(KeyCode.getKeyCode(e.getAttribute("keyCode")));
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
