package structures.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.front_end.IDraw;
import engine.loop.collisions.ICollisionChecker;
import exceptions.CompileTimeException;
import javafx.scene.paint.Color;
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

	private Map<String, Double> myVariables;

	private boolean highlight;

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
		myVariables = new HashMap<>();
		myLastX = 0.0;
		myLastY = 0.0;
		this.highlight = false;

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
			if (this.highlight) {
				drawListener.drawRectangle(getBounds(), view, Color.INDIANRED);
			}
			this.highlight = false;
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
		myLastX = this.x;
		myLastY = this.y;
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

	public void set_random_number_and_choose(double odds){
		//TODO: I don't know how to make this work
	}

	public void sleep(double time){

	}

	public void wrap_around_room(boolean value){

	}

	public void block(RunObject other, double slipFactor) {
		other.highlight = true;
		if (this.highlight) {
			return;
		}
		if (!collision_at(this.x, this.y)) {
			return;
		}

		double desiredX = this.x;
		double desiredY = this.y;

		double currentX = myLastX;
		double currentY = myLastY;

		this.x = currentX;
		this.y = currentY;

		if (Math.abs(x - myLastX) > Math.abs(y - myLastY)) {
			stepX(currentX, desiredX);
			stepY(currentY, desiredY);
		} else {
			stepY(currentY, desiredY);	
			stepX(currentX, desiredX);
		}

		if (Math.abs(this.x - desiredX) > .5) {
			velocity = velocity.setX(0.0);
		}
		if (Math.abs(this.y - desiredY) > .5) {
			velocity = velocity.setY(0.0);
		}
		this.highlight = true;


	}

	public void stepX(double currentX, double desiredX) {
		if (desiredX > currentX) {
			for (double i = currentX; i <= desiredX; i += .5) {
				if (collision_at(i, this.y)) {
					break;
				}
				this.x = i;
			}
		} else {
			for (double i = currentX; i >= desiredX; i -= .5) {
				if (collision_at(i, this.y)) {
					break;
				}
				this.x = i;
			}			
		}
	}

	public void stepY(double currentY, double desiredY) {
		if (desiredY > currentY) {
			for (double i = currentY; i <= desiredY; i += .5) {
				if (collision_at(this.x, i)) {
					break;
				}
				this.y = i;
			}
		} else {
			for (double i = currentY; i >= desiredY; i -= .5) {
				if (collision_at(this.x, i)) {
					break;
				}
				this.y = i;
			}			
		}
	}

	public void block2(RunObject other, double slipFactor) {


		if (!collision_at(this.x, this.y)) {
			return;
		}		

		Point testPoint = this.getBounds().centerPoint();
		System.out.println(other.getBounds().quadrantOfPoint(testPoint));
		switch (other.getBounds().quadrantOfPoint(testPoint)) {
		case TOP:
			if (this.velocity.y > 0) {
				this.velocity = this.velocity.setY(0.0);
			}
			while (collision_with_at(this.x, this.y, other)) {
				this.y--;
			}
			break;
		case BOTTOM:
			if (this.velocity.y < 0) {
				this.velocity = this.velocity.setY(0.0);
			}
			while (collision_with_at(this.x, this.y, other)) {
				this.y++;
			}
			break;
		case LEFT:
			if (this.velocity.x > 0) {
				this.velocity = this.velocity.setX(0.0);
			}
			while (collision_with_at(this.x, this.y, other)) {
				this.x--;
			}
			break;
		case RIGHT:
			if (this.velocity.x < 0) {
				this.velocity = this.velocity.setX(0.0);
			}
			while (collision_with_at(this.x, this.y, other)) {
				this.x++;
			}
			break;
		}
	}

	public void block3(RunObject other, double slipFactor) {
		other.highlight = true;

		if (!collision_at(this.x, this.y)) {
			return;
		}
		if (collision_at(myLastX, myLastY)) {
			System.out.println(String.format("!current: (%.1f, %.1f), was: (%.1f, %.1f)", this.x, this.y, myLastX, myLastY));
		} else {
			System.out.println(String.format("current: (%.1f, %.1f), was: (%.1f, %.1f)", this.x, this.y, myLastX, myLastY));
		}
		List<Point> points = Bresenham.interpolate((int)myLastX, (int)myLastY, (int)this.x, (int)this.y);
		Point testPoint = this.getBounds().centerPoint();

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
		pivot = (end - start) / 2 + start;
		test = points.get(pivot);

		double shiftX = test.x - this.x;
		double shiftY = test.y - this.y;
		this.x = test.x;
		this.y = test.y;
		myLastX = this.x;
		myLastY = this.y;
		System.out.println("Collides on " + other.getBounds().quadrantOfPoint(testPoint));
		switch (other.getBounds().quadrantOfPoint(testPoint)) {
		case TOP:
			if (this.velocity.y > 0) {
				this.velocity = this.velocity.setY(0.0);
			}
			this.x -= shiftX;
			/*while (collision_at(this.x, this.y)) {
					this.y--;
					System.out.println("TTT");
				}*/
			break;
		case BOTTOM:
			if (this.velocity.y < 0) {
				this.velocity = this.velocity.setY(0.0);
			}
			this.x -= shiftX;
			break;
		case LEFT:
			if (this.velocity.x > 0) {
				this.velocity = this.velocity.setX(0.0);
			}
			this.y -= shiftY;
			break;
		case RIGHT:
			if (this.velocity.x < 0) {
				this.velocity = this.velocity.setX(0.0);
			}
			this.y -= shiftY;
			break;
		}
		System.out.println(String.format("moved back to (%.1f, %.1f)", this.x, this.y));
	}

	public boolean collision_at(double x, double y) {
		if (myCollisionChecker == null) {
			return false;
		} else {
			return myCollisionChecker.collisionAt(x, y, this);
		}
	}

	public boolean collision_with_at(double x, double y, RunObject with) {
		return myCollisionChecker.collisionWithAt(x, y, this, with);
	}
	public double get_x_position(){
		return this.x;
	}

	public double get_y_position(){
		return this.y;
	}

}