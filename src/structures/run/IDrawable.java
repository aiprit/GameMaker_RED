package structures.run;

import engine.EventManager;

public interface IDrawable {
	public void draw(EventManager eventManager, RunView view, RunObject object);
}
