package structures.run;

import engine.loop.IDraw;
import front_end.IDraw;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
