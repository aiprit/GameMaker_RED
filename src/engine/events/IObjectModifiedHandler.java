package engine.events;

import structures.run.RunObject;
import utils.Point;
import utils.Rectangle;

public interface IObjectModifiedHandler {
    void onObjectCreate(RunObject runObject);
    void onObjectDestroy(RunObject runObject);
    void setView(Point coordinates);
    void addStringToDraw(String draw);
}
