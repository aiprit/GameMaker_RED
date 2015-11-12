package structures.data;

import java.awt.geom.Point2D;
import java.util.Map;

public class DataObject {
	
    Map<DataEvent, DataObject> actions;
    String name;
    String sprite;

    Point2D position;
    double direction, angularVelocity;
    double scaleX, scaleY, alpha;
    
    boolean visible;
    int zIndex;
}
