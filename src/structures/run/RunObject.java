package structures.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import engine.front_end.IDraw;
import engine.loop.collisions.ICollisionChecker;
import exceptions.CompileTimeException;
import javafx.scene.image.Image;
import structures.data.DataSprite;
import structures.data.events.IDataEvent;
import utils.Vector;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

public class RunObject implements IParameters {
        public static final String NAME = "Name";
        public static final String SCALEX = "Scale X";
        public static final String SCALEY = "Scale Y";
        public static final String ANGLE = "Angle";
        public static final String ANGULAR_VELOCITY = "Angular Velocity";
        public static final String VISIBLE = "Visible";
        public static final String ALPHA = "Alpha";
        public static final String FRICTION = "Friction";
        public static final String SOLID = "Solid";
        public static final String VELOCITYX = "Velocity X";
        public static final String VELOCITYY = "Velocity Y";
        public static final String GRAVITYX = "Gravity X";
        public static final String GRAVITYY = "Gravity Y";
        
        public static final double MAX_SCALEX = 7;
        public static final double MAX_SCALEY = 7;
        public static final double MAX_ANGLE = 360;
        public static final double MAX_ANGULAR_VELOCITY = 100;
        public static final double MAX_ALPHA = 1;
        public static final double MAX_FRICTION = 2;
        public static final double MAX_VELOCITYX = 10;
        public static final double MAX_VELOCITYY = 10;
        public static final double MAX_GRAVITYX = 40;
        public static final double MAX_GRAVITYY = 40;

	public double x;
	public double y;

	public String name;
	public double scaleX;
	public double scaleY;
	public double angle;
	public double angularVelocity;
	public boolean visible;
	public Vector velocity;
	public Vector gravity;
	public double alpha;
	public double friction;
	public boolean solid;

	private RunSprite mySprite;
	private Map<IDataEvent, RunAction> myEvents;
	private long myInstanceId;

	private Rectangle myBounds;
	private ICollisionChecker myCollisionChecker;

	private Map<String, Double> myVariables;
	private Map<String, Boolean> myBooleanMap;
	private Map<String, String> myStringMap;
	private Map<String, Double> myDoubleMap;

	public RunObject(String name) {
		this.name = name;
		this.x = 0.0;
		this.y = 0.0;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.angle = 0.0;
		this.velocity = Vector.ZERO;
		this.gravity = Vector.ZERO;
		this.friction = 0.0;
		this.angularVelocity = 0.0;
		this.visible = true;
		this.alpha = 1.0;
		this.solid = false;
		myInstanceId = 0L;
		myEvents = new HashMap<IDataEvent, RunAction>();
		myVariables = new HashMap<>();
		myBooleanMap = new TreeMap<>();
	        myStringMap = new TreeMap<>();
	        myDoubleMap = new TreeMap<>();

		myBounds = new Rectangle(0, 0, 0, 0);
		initMaps();
	}
	
	public Map<String, Double> getDoubleMap() {
	    initMaps();
	    return myDoubleMap;
	}
	
	public Map<String, String> getStringMap() {
	    initMaps();
	    return myStringMap;
	}
	
	public Map<String, Boolean> getBooleanMap() {
	    initMaps();
	    return myBooleanMap;
	}
	
	private void initMaps() {
	    myStringMap.put(NAME, name);
	    myDoubleMap.put(SCALEX, scaleX/MAX_SCALEX);
	    myDoubleMap.put(SCALEY, scaleY/MAX_SCALEY);
	    myDoubleMap.put(ANGLE, angle/MAX_ANGLE);
	    myDoubleMap.put(ANGULAR_VELOCITY, angularVelocity/MAX_ANGULAR_VELOCITY);
	    myBooleanMap.put(VISIBLE, visible);
	    myDoubleMap.put(ALPHA, alpha/MAX_ALPHA);
	    myDoubleMap.put(FRICTION, friction/MAX_FRICTION);
	    myDoubleMap.put(VELOCITYX, velocity.x/MAX_VELOCITYX*2+.5);
	    myDoubleMap.put(VELOCITYY, velocity.y/MAX_VELOCITYY*2+.5);
	    myDoubleMap.put(GRAVITYX, gravity.x/MAX_GRAVITYX*2+.5);
	    myDoubleMap.put(GRAVITYY, gravity.y/MAX_GRAVITYY*2+.5);
	    myBooleanMap.put(SOLID, solid);
	}
	
	public void setParameterMaps(Map<String, String> strings, Map<String, Double> doubles, Map<String, Boolean> booleans) {
	    myStringMap = strings;
	    myDoubleMap = doubles;
	    myBooleanMap = booleans;
	    name = strings.get(NAME);
	    scaleX = myDoubleMap.get(SCALEX)*MAX_SCALEX;
	    scaleY = myDoubleMap.get(SCALEY)*MAX_SCALEY;
	    angle = myDoubleMap.get(ANGLE)*MAX_ANGLE;
	    angularVelocity = myDoubleMap.get(ANGULAR_VELOCITY)*MAX_ANGULAR_VELOCITY;
	    visible = myBooleanMap.get(VISIBLE);
	    alpha = myDoubleMap.get(ALPHA)*MAX_ALPHA;
	    friction = myDoubleMap.get(FRICTION)*MAX_FRICTION;
	    solid = myBooleanMap.get(SOLID);
	    velocity.x = (myDoubleMap.get(VELOCITYX)-.5)*MAX_VELOCITYX/2;
	    velocity.y = (myDoubleMap.get(VELOCITYY)-.5)*MAX_VELOCITYY/2;
	    gravity.x = (myDoubleMap.get(GRAVITYX)-.5)*MAX_GRAVITYX/2;
	    gravity.y = (myDoubleMap.get(GRAVITYY)-.5)*MAX_GRAVITYY/2;
	    initMaps();
	}

	protected void bindEvent(IDataEvent event, RunAction action) {
		myEvents.put(event, action);
	}

	protected void setSprite(RunSprite sprite) {
		mySprite = sprite;
		myBounds.width(mySprite.getWidth() * scaleX);
		myBounds.height(mySprite.getHeight() * scaleY);
		myBounds.center(mySprite.centerX, mySprite.centerY);
	}

	protected void setInstanceId(long id) {
		myInstanceId = id;
	}
	protected long getInstanceId() {
		return myInstanceId;
	}
	public void setCollisionChecker(ICollisionChecker checker) {
		myCollisionChecker = checker;
	}

	public IRectangle getBounds() {
		myBounds.width(mySprite.getWidth() * scaleX);
		myBounds.height(mySprite.getHeight() * scaleY);
		myBounds.move(x, y);
		myBounds.angle(this.angle);
		return myBounds.getImmutable();
	}

	protected RunObject clone() {
		RunObject clone = new RunObject(name);
		clone.x = this.x;
		clone.y = this.y;
		clone.scaleX = this.scaleX;
		clone.scaleY = this.scaleY;
		clone.angle = this.angle;
		clone.velocity = this.velocity;
		clone.mySprite = this.mySprite;
		clone.visible = this.visible;
		clone.solid = this.solid;
		clone.alpha = this.alpha;
		clone.angularVelocity = this.angularVelocity;
		clone.friction = this.friction;
		clone.gravity = this.gravity;
		clone.myVariables = new HashMap<>(this.myVariables);

		// This is OK because both IDataEvents and RunActions are immutable
		clone.myEvents = new HashMap<>(myEvents);
		return clone;
	}

	public Set<IDataEvent> getEvents(){
		return myEvents.keySet();
	}

	public RunAction getAction(IDataEvent e){
		if(!myEvents.containsKey(e)){
			return null;
		}
		return myEvents.get(e);
	}

	public void draw(IDraw drawListener, RunView view) {
		if (mySprite != null) {
			mySprite.draw(drawListener, view, this);
		}
	}

	public long instance_id() {
		return myInstanceId;
	}

	public void change_sprite(String name, String baseFileName){
		try {
			mySprite = new RunSprite(new DataSprite(name, baseFileName));
		}
		catch (CompileTimeException e) {
			e.printStackTrace();
		}
	}

	public void set_velocity(double direction, double speed, boolean relative) {
		Vector change = new Vector(speed, direction, true);
		if (relative) {
			this.velocity = this.velocity.add(change);
		} else {
			this.velocity = change;
		}
	}

	public void set_velocity(double x, double y, double speed, boolean relative){
		Vector change = (new Vector(x - this.x, y - this.y)).setLength(speed);
		if (relative) {
			this.velocity = this.velocity.add(change);
		} else {
			this.velocity = change;
		}
	}

	public void move_to(double x, double y, boolean relative){
		if (relative) {
			this.x += x;
			this.y += y;
		} else {
			this.x = x;
			this.y = y;
		}
	}

	public double get_variable(String key){
		if(!myVariables.containsKey(key)){
			myVariables.put(key, 0.0);
		}
		return myVariables.get(key);
	}

	public void set_variable(String key, double value, boolean relative){
		if(relative){
			double oldValue = 0;
			if(myVariables.containsKey(key)){
				oldValue = myVariables.get(key);
			}
			myVariables.put(key, (oldValue + value));
		}
		else{
			myVariables.put(key, value);
		}
	}

	public void scale_sprite(double width, double height){

	}

	public void sleep(double time){

	}

	public void wrap_around_room(boolean value){

	}

	public boolean collision_at(double x, double y) {
		if (myCollisionChecker == null) {
			return false;
		} else {
			return myCollisionChecker.collisionAt(x, y, this);
		}
	}

	public boolean collision_with_at(double x, double y, RunObject with) {
		if (myCollisionChecker == null) {
			return false;
		} else {
			return myCollisionChecker.collisionWithAt(x, y, this, with);
		}
	}
	
	public boolean collision_solid_at(double x, double y) {
		if (myCollisionChecker == null) {
			return false;
		} else {
			return myCollisionChecker.collisionSolidAt(x, y, this);
		}
	}
	
	public double get_x_position(){
		return this.x;
	}

	public double get_y_position(){
		return this.y;
	}

    @Override
    public Image getImage () {
        return mySprite.getImage();
    }

}