package engine.events;

import structures.run.RunObject;
import utils.Point;

public interface IObjectModifiedHandler {
    void onObjectCreate(RunObject runObject);
    void onObjectDestroy(RunObject runObject);
    void setView(Point coordinates);
}
