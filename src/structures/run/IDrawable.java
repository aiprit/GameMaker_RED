package structures.run;

import engine.IDraw;
import engine.events.EventManager;
import javafx.scene.paint.Paint;
import utils.IRectangle;

public interface IDrawable {

	void draw(IDraw myDrawListener, RunView view, RunObject object);
}
