package structures.data.abstract_classes;

import java.util.Map;

import structures.data.interfaces.IDataEvent;

public abstract class MousePressedEvent implements IDataEvent {

	public String mouseState;
	
	public MousePressedEvent(String mouseState){
		this.mouseState = mouseState;
	}
	
	@Override
	public boolean hasXY(){
		return true;
	}
	
	public abstract int hashCode();
	
	public abstract boolean equals(Object obj);
	
	public abstract Map<String, String> dumpContents();

}
