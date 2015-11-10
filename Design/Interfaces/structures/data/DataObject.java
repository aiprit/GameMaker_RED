package structures.data;

import java.awt.geom.Point2D;
import java.util.Map;

public class DataObject {
    Map<DataEvent, DataObject> actions;
    String compiledGroovyScript;
    String objectTitle;
    String spriteImage;

    Point2D position;
    Point2D velocity;
    double heading, angularVelocity;
    double scaleX, scaleY, alpha;

    boolean visible;
    boolean justCreated;
    boolean markedForDestruction;

    int ID;

    int zIndex;
}
