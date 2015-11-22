package structures.data.events;

import java.util.Map;

public interface IDataEvent {
    String getName();
   
    Map<String, String> dumpContents();
    
    public default boolean getLocalCheck(){
    	return false;
    }
    
    public default boolean hasXY(){
    	return false;
    }
}
