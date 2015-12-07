package structures.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import engine.front_end.IDraw;
import engine.loop.collisions.ICollisionChecker;
import exceptions.CompileTimeException;
import exceptions.GameRuntimeException;
import structures.data.DataSprite;
import structures.data.interfaces.IDataEvent;
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
	public static final double MAX_VELOCITYX = 100;
	public static final double MAX_VELOCITYY = 100;
	public static final double MAX_GRAVITYX = 40;
	public static final double MAX_GRAVITYY = 40;

	private double x;
	private double y;

	private String name;
	private double scaleX;
	private double scaleY;
	private double angle;
	private double angularVelocity;
	private boolean visible;
	private Vector velocity;
	private Vector gravity;
	private double alpha;
	private double friction;
	private boolean solid;

	private RunSprite mySprite;
	private Map<IDataEvent, RunAction> myEvents;
	private long myInstanceId;

	private Rectangle myBounds;
	private ICollisionChecker myCollisionChecker;

	private Map<String, Object> myVariables;
	private Map<String, Boolean> myBooleanMap;
	private Map<String, String> myStringMap;
	private Map<String, Double> myDoubleMap;
	private Map<String, Boolean> myOriginalBooleanMap;
        private Map<String, String> myOriginalStringMap;
        private Map<String, Double> myOriginalDoubleMap;

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
		myOriginalBooleanMap = new TreeMap<>();
                myOriginalStringMap = new TreeMap<>();
                myOriginalDoubleMap = new TreeMap<>();
		myBounds = new Rectangle(0, 0, 0, 0);
	}

	/*
	 * Live-paramter editing
	 */
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
	    velocity = new Vector((myDoubleMap.get(VELOCITYX)-.5)*MAX_VELOCITYX/2, (myDoubleMap.get(VELOCITYY)-.5)*MAX_VELOCITYY/2);
	    gravity = new Vector((myDoubleMap.get(GRAVITYX)-.5)*MAX_GRAVITYX/2, (myDoubleMap.get(GRAVITYY)-.5)*MAX_GRAVITYY/2);
	    initMaps();
	}

	/*
	 * Protected events for RunObject creation (hidden from Groovy user)
	 */
	protected void bindEvent(IDataEvent event, RunAction action) {
		myEvents.put(event, action);
	}

	public void setSprite(RunSprite sprite) {
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

	public void setCollisionChecker(ICollisionChecker checker) {
		myCollisionChecker = checker;
	}

	/*
	 * Public methods used internally by engine, although open to Groovy user
	 * if they wanted.
	 */
	public IRectangle getBounds() {
		myBounds.width(mySprite.getWidth() * scaleX);
		myBounds.height(mySprite.getHeight() * scaleY);
		myBounds.move(x, y);
		myBounds.angle(this.angle);
		myBounds.center();
		
		return myBounds.getImmutable();
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

	/*
	 * Official methods of the Groovy RunObject API:
	 */
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
	
	public RunSprite get_sprite() {
		return mySprite;
	}
	
	public String get_sprite_name() {
		if (mySprite == null) {
			return "";
		} else {
			return mySprite.name;
		}
	}
	
	public void bounce(double factor) {
		double gravComp = Vector.dot(getGravity().normalize(), getVelocity());
		Vector add = new Vector(gravComp * -(1 + factor), getGravity().direction(), true);
		setVelocity(getVelocity().add(add));
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
	
	public void move_to_free(double angle) {
		double dx = Math.cos(Math.toRadians(angle));
		double dy = Math.sin(Math.toRadians(angle));
		while (collision_solid_at(x, y)) {
			x += dx;
			y += dy;
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
	
	public void propertyMissing(String name, Object value) {
		myVariables.put(name, value);
	}
	
	public Object propertyMissing(String name) throws GameRuntimeException {
		Object result = myVariables.get(name);
		if (result != null) {
			return result;
		} else {
			myVariables.put(name, 0.0);
			return 0.0;
		}
	}

	public void scale_sprite(double width, double height){
		scaleX = width;
		scaleY = height;
	}

	public void sleep(double time){

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
	
	public boolean on_ground() {
		double deltaX, deltaY;
		if (Math.abs(this.getGravity().x) < .00001) {
			deltaX = 0;
		} else {
			deltaX = Math.signum(this.getGravity().x);
		}
		if (Math.abs(this.getGravity().y) < .00001) {
			deltaY = 0;
		} else {
			deltaY = Math.signum(this.getGravity().y);
		}
		return collision_solid_at(this.getX() + deltaX, this.getY() + deltaY);
	}
	
	public void accelerate_capped(double angle, double speed, double maxspeed) {
		setVelocity(getVelocity().add(new Vector(speed, angle, true)));
		Vector para = new Vector(1, angle, true);
		Vector perp = new Vector(1, angle - 90, true);
		double paraComp = Vector.dot(para, getVelocity());
		double perpComp = Vector.dot(perp, getVelocity());
		paraComp = Math.min(paraComp, maxspeed);
		
		double xComp = Vector.dot(para, new Vector(1, 0)) * paraComp;
		double yComp = Vector.dot(perp, new Vector(0, 1)) * perpComp;
		
		setVelocity(new Vector(xComp, yComp));
		
		
	}

	/*
	 * Official getters and setters for Groovy user use:
	 */
	public String name() {
		return this.name;
	}
	
	public double getX() {
		return this.x;
	}
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public double getScaleX() {
		return this.scaleX;
	}
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	
	public double setScaleY() {
		return this.scaleY;
	}
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
	public void scale(double scaleX, double scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	public double getAngle() {
		return this.angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngularVelocity() {
		return this.angularVelocity;
	}
	public void setAngularVelocity(double angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public Vector getGravity() {
		return this.gravity;
	}
	public void setGravity(Vector gravity) {
		this.gravity = gravity;
	}
	
	public double getAlpha() {
		return this.alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	public double getFriction() {
		return this.friction;
	}
	public void setFriction(double friction) {
		this.friction = friction;
	}
	
	public boolean isSolid() {
		return this.solid;
	}
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

    @Override
    public void setOriginalParameterMaps () {
        myOriginalStringMap.put(NAME, name);
        myOriginalDoubleMap.put(SCALEX, scaleX/MAX_SCALEX);
        myOriginalDoubleMap.put(SCALEY, scaleY/MAX_SCALEY);
        myOriginalDoubleMap.put(ANGLE, angle/MAX_ANGLE);
        myOriginalDoubleMap.put(ANGULAR_VELOCITY, angularVelocity/MAX_ANGULAR_VELOCITY);
        myOriginalBooleanMap.put(VISIBLE, visible);
        myOriginalDoubleMap.put(ALPHA, alpha/MAX_ALPHA);
        myOriginalDoubleMap.put(FRICTION, friction/MAX_FRICTION);
        myOriginalDoubleMap.put(VELOCITYX, velocity.x/MAX_VELOCITYX*2+.5);
        myOriginalDoubleMap.put(VELOCITYY, velocity.y/MAX_VELOCITYY*2+.5);
        myOriginalDoubleMap.put(GRAVITYX, gravity.x/MAX_GRAVITYX*2+.5);
        myOriginalDoubleMap.put(GRAVITYY, gravity.y/MAX_GRAVITYY*2+.5);
        myOriginalBooleanMap.put(SOLID, solid);
    }

    @Override
    public Map<String, Double> getOriginalDoubleMap () {
        return myOriginalDoubleMap;
    }

    @Override
    public Map<String, String> getOriginalStringMap () {
        return myOriginalStringMap;
    }

    @Override
    public Map<String, Boolean> getOriginalBooleanMap () {
        return myOriginalBooleanMap;
    }

}