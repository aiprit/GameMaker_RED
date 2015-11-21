package structures.run;

import engine.IDraw;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
