package structures.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.IDraw;
import engine.collisions.ICollisionChecker;
import exceptions.CompileTimeException;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.events.IDataEvent;
import utils.Bresenham;
import utils.Point;
import utils.Vector;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

public class RunObject {
	
	public double x;
	public double y;
	
	public final String name;
	public double scaleX;
	public double scaleY;
	public double angle;
	public double angularVelocity;
	public boolean visible;
	public Vector velocity;
	public Vector gravity;
	public double alpha;
	public double friction;
	
	private RunSprite mySprite;
	private Map<IDataEvent, RunAction> myEvents;
	private long myInstanceId;
	
	private double myLastX, myLastY;
	
	private Rectangle myBounds;
	private ICollisionChecker myCollisionChecker;
	
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
		myInstanceId = 0L;
		myEvents = new HashMap<IDataEvent, RunAction>();
		myLastX = 0.0;
		myLastY = 0.0;
		
		myBounds = new Rectangle(0, 0, 0, 0);
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
			//drawListener.drawRectangle(getBounds(), view, Color.INDIANRED);
		}
	}
	
	public long instance_id() {
		return myInstanceId;
	}
	
	public DataObject toData() {
		// TODO: What the hell is this method for?
		return null;
	}
	
	public void change_sprite(String name, String baseFileName){
	        try {
                mySprite = new RunSprite(new DataSprite(name, baseFileName));
                }
                catch (CompileTimeException e) {
                    e.printStackTrace();
                }
	}
	
	public void movement_angle(double angle, double acceleration, boolean relative){
		//need physics engine
	}
	
	public void movement_towards(double x, double y, double acceleration, boolean relative){
		//need physics engine
	}

	public void move_to(double x, double y, boolean relative){
		if (relative) {
			myLastX = x;
			myLastY = y;
			this.x += x;
			this.y += y;
		} else {
			this.x = x;
			this.y = y;
		}
	}
	
//	public void run_script(String script){
//		
//	}
	
	public void scale_sprite(double width, double height){
		
	}
	
	public void set_random_number_and_choose(double odds){
		//TODO: I don't know how to make this work
	}
	
	public void sleep(double time){
		
	}
	
	public void wrap_around_room(boolean value){
		
	}
	
	public void block(RunObject other, double slipFactor) {
		if (!collision_at(this.x, this.y) || collision_at(myLastX, myLastY)) {
			return;
		}
		List<Point> points = Bresenham.interpolate((int)myLastX, (int)myLastY, (int)this.x, (int)this.y);
		
		// Find the first free block
		int pivot = 0;
		int start = 0;
		int end = points.size();
		Point test = null;
		while (end - start > 1) {
			pivot = (end - start) / 2 + start;
			test = points.get(pivot);
			if (collision_at(test.x, test.y)) {
				end = pivot;
			} else {
				start = pivot;
			}
		}
		
		this.x = test.x;
		this.y = test.y;
		
		switch (other.getBounds().quadrantOfPoint(new Point(this.x, this.y))) {
			case TOP:
			case BOTTOM:
				this.velocity = this.velocity.setY(0.0);
				break;
			case LEFT:
			case RIGHT:
				this.velocity = this.velocity.setX(0.0);
				break;
		}
	}
	
	public boolean collision_at(double x, double y) {
		if (myCollisionChecker == null) {
			return false;
		} else {
			return myCollisionChecker.collisionAt(x, y, this);
		}
	}
	public double get_x_position(){
		return this.x;
	}
	
	public double get_y_position(){
		return this.y;
	}

}