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

        switch (className) {
            case "structures.data.events.CollisionEvent":
                return new CollisionEvent(g.getObjectFromString(e.getAttribute("dataObject")));
            case "structures.data.events.GameEndEvent":
                return new GameEndEvent();
            case "structures.data.events.GameStartEvent":
                return new GameStartEvent();
            case "structures.data.events.GlobalMousePressedEvent":
                return new GlobalMousePressedEvent(e.getAttribute("mouseState"));
            case "structures.data.events.KeyPressedEvent":
                return new KeyPressedEvent(KeyCode.valueOf(e.getAttribute("keyCode")));
            case "structures.data.events.KeyReleasedEvent":
                return new KeyReleasedEvent(KeyCode.valueOf(e.getAttribute("keyCode")));
            case "structures.data.events.LeaveRoomEvent":
                return new LeaveRoomEvent();
            case "structures.data.events.ObjectCreateEvent":
                return new ObjectCreateEvent();
            case "structures.data.events.ObjectDestroyEvent":
                return new ObjectDestroyEvent();
            case "structures.data.events.ObjectMousePressedEvent":
                return new ObjectMousePressedEvent(e.getAttribute("mouseState"));
            case "structures.data.events.StepEvent":
                return new StepEvent();
            default:
                return null;
        }
    }
}
