package structures.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.IDraw;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import structures.data.DataObject;
import structures.data.events.IDataEvent;
import utils.Point;
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
	}
	
	protected void bindEvent(IDataEvent event, RunAction action) {
		myEvents.put(event, action);
	}
	
	protected void setSprite(RunSprite sprite) {
		mySprite = sprite;
	}
	
	protected void setInstanceId(long id) {
		myInstanceId = id;
	}
	protected long getInstanceId() {
		return myInstanceId;
	}
	
	protected Rectangle getBounds() {
		
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
		clone.myEvents = new HashMap<IDataEvent, RunAction>(myEvents);
		return clone;
	}
	
	public Set<IDataEvent> getEvents(){
		return myEvents.keySet();
	}
	
	public void doAction(IDataEvent e){
		if(!myEvents.containsKey(e)){
			return;
		}
		RunAction act = myEvents.get(e);
		Binding binding = new Binding();
		binding.setProperty("current", this);
		GroovyShell shell = new GroovyShell(binding);
		System.out.println(act.script);
		shell.evaluate(act.script);
	}
	
	public void draw(IDraw drawInterface, RunView view) {
		if (mySprite != null) {
			System.out.println(name +  "drawing at " + new Point(x, y));
			mySprite.draw(drawInterface, view, this);
		}
	}
	
	public long instance_id() {
		return myInstanceId;
	}
	
	public void trigger(IDataEvent event) {
		// TODO: Groovy run event
	}
	
	public DataObject toData() {
		// TODO: What the hell is this method for?
		return null;
	}
	
	public void change_sprite(){
		//parameters?
	}
	
	public void destroy(){
		
	}
	
	public void movement_angle(double angle, double acceleration, boolean relative){
		
	}
	
	public void movement_towards(double x, double y, double acceleration, boolean relative){
		
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
		System.out.println("moved to: " + this.x + " " + this.y);
	}
	
	public void move_to_random(){
		//TODO: is this a parameter or do we calculate it here?
	}
	
	public void run_script(String script){
		
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

}