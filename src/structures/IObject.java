package structures;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableMap;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;
import utils.Point;

public interface IObject {

	void bindEvent(IDataEvent event, List<IAction> actions);

	void removeEvent(IDataEvent e);

	void setSprite(DataSprite s);

	String getName();

	void setName(String name);

	void setPosition(Point pos);

	Point getPosition();

	void setVisible(boolean visible);

	boolean isVisible();

	void setAngle(double angle);

	double getAngle();

	void setZIndex(int zIndex);

	int getZIndex();

	void setAngularVelocity(double angularVelocity);

	double getAngularVelocity();

	double getAlpha();

	void setAlpha(double alpha);

	Map<IDataEvent, List<IAction>> getEvents();

	DataSprite getSprite();

	double getScaleX();
	double getScaleY();

	void setScaleX(double x);
	void setScaleY(double y);

}