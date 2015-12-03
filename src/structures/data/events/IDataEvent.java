package structures.data.events;

import java.util.Map;

public interface IDataEvent {
    String getName();
   
    Map<String, String> dumpContents();
    
    default boolean getLocalCheck(){
    	return false;
    }
    
    default boolean hasXY(){
    	return false;
    }
}
