package structures.run;

import engine.loop.IDraw;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
