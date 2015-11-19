package structures.data;

import utils.Rectangle;

/**
 * The View that determines what the camera paints on the screen
 * within a Room. Views are always bound to a specific Room, and
 * they must have a unique name within a Room.
 * <p>
 * Use the access to the mutable Rectangle in .getRectangle()
 * to modify our coordinates.
 */
public class DataView {

    private final String myName;
    private Rectangle myView;

    public DataView(String name, double x, double y, double width, double height) {
        myName = name;
        myView = new Rectangle(x, y, width, height);
    }

    public DataView(String name, Rectangle rect) {
        myName = name;
        myView = rect;
    }

    public String getName() {
        return myName;
    }

    public Rectangle getView() {
        return myView;
    }

    public void setView(Rectangle rect) {
        myView = rect;
    }

    public double getX(){
        return myView.x();
    }

    public double getY(){
        return myView.y();
    }

    public double getWidth(){
        return myView.width();
    }

    public double getHeight(){
        return myView.height();
    }
}
