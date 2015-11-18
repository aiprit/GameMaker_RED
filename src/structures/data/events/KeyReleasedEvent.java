package structures.data.events;

import javafx.scene.input.KeyCode;

public class KeyReleasedEvent implements IDataEvent {

	public final KeyCode keyCode;

    public KeyReleasedEvent(KeyCode code){
        keyCode = code;
    }

    public int hashCode() {
    	return this.getClass().hashCode() ^ this.keyCode.hashCode();
    }

    public boolean equals(Object obj) {
    	if (obj.getClass().equals(this.getClass())) {
    		if (((KeyReleasedEvent)obj).keyCode.hashCode() == this.keyCode.hashCode()) {
    			return true;
    		}
    	}
    	return false;
    }

    public String getName() {
    	return String.format("Key Release %s", keyCode.getName());
    }
    @Override
    public String toString(){
    	return this.getName();
    }

}
