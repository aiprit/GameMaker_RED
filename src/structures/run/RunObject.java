package structures.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.IDraw;
import exceptions.CompileTimeException;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.events.IDataEvent;
import utils.IRectangle;
import utils.Rectangle;
import utils.Vector;

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
	public double alpha;
	
	private RunSprite mySprite;
	private Map<IDataEvent, RunAction> myEvents;
	private long myInstanceId;
	
	private Rectangle myBounds;
	
	private Map<String, Double> myVariables;
	
	public RunObject(String name) {
		this.name = name;
		this.x = 0.0;
		this.y = 0.0;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.angle = 0.0;
		this.velocity = Vector.ZERO;
		this.angularVelocity = 0.0;
		this.visible = true;
		this.alpha = 1.0;
		myInstanceId = 0L;
		myEvents = new HashMap<IDataEvent, RunAction>();
		myVariables = new HashMap<>();
		
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
		double xOffset = 0;
		double yOffset = 0;
		if(relative){
			xOffset = this.x;
			yOffset = this.y;
		}
		this.x = xOffset + x;
		this.y = yOffset + y;
	}
	
	public double get_variable(String key){
		if(!myVariables.containsKey(key)){
			myVariables.put(key, 0.0);
		}
		return myVariables.get(key);
	}
	
	public void set_variable(String key, double value, boolean relative){
		if(relative){
			double oldValue = myVariables.get(key);
			myVariables.put(key, (oldValue + value));
		}
		else{
			myVariables.put(key, value);
		}
	}
	
	public void scale_sprite(double width, double height){
		
	}
	
	public void set_acceleration(double acceleration){
		
	}
	
	public void set_friction(double friction){
		
	}
	
	public void set_random_number_and_choose(double odds){
		//TODO: I don't know how to make this work
	}
	
	public void sleep(double time){
		
	}
	
	public void wrap_around_room(boolean value){
		
	}
	
	public double get_x_position(){
		return this.x;
	}
	
	public double get_y_position(){
		return this.y;
	}

}