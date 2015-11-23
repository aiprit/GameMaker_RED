package structures.run;

import engine.front_end.IDraw;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
