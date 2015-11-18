package engine;

import structures.run.RunObject;

public interface IObjectModifiedHandler {
    void onObjectCreate(RunObject runObject);
    void onObjectDestroy(RunObject runObject);
}
