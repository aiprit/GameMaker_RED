package structures.run;

import engine.IDraw;
import engine.events.EventManager;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
