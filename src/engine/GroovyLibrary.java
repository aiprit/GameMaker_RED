package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import engine.events.EventManager;
import engine.events.IObjectModifiedHandler;
import engine.events.IRoomChangedHandler;
import exceptions.GameRuntimeException;
import exceptions.UnknownResourceException;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunSound;
import utils.Point;

public class GroovyLibrary {

	private RunGame myRunGame;
	private EventManager myEventManager;
	private Map<String, Double> myGlobalVariables;

	public GroovyLibrary(RunGame runGame, EventManager eventManager) {
		myRunGame = runGame;
		myEventManager = eventManager;
		myGlobalVariables = new HashMap<>();
	}

	private void fatalError(String message, Object... args) {
		// Do nothing
	}

	public double random_number(double max){
		Random rand = new Random();
		return (max * rand.nextDouble());
	}

	public void create_object(String name, double x, double y){
		RunObject runObject = null;
		try {
			runObject = myRunGame.getCurrentRoom().instantiate(name, x, y);
		} catch (GameRuntimeException e) {
			fatalError(e.getMessage());
		}
		runObject.x = x;
		runObject.y = y;
		myEventManager.onObjectCreate(runObject);
	}

	public void create_object_long(String name, double x, double y, double speed,
			double friction, boolean wraparound) {
		RunObject runObject = null;
		try {
			runObject = myRunGame.getCurrentRoom().instantiate(name, x, y);
		} catch (GameRuntimeException e) {
			fatalError(e.getMessage());
		}
		runObject.x = x;
		runObject.y = y;		
		//runObject.set_speed(speed);
		runObject.set_friction(friction);
		runObject.wrap_around_room(wraparound);
		myEventManager.onObjectCreate(runObject);
	}

	public void destroy(RunObject deleteThis){
		myEventManager.onObjectDestroy(deleteThis);
	}

	//	public void display_message(String message){
	//
	//	}

	public void draw_text(String text){
		myEventManager.addStringToDraw(text);
	}

	//	public void get_mouse_state(){
	//		//use in GetMouseState to see if right or left etc.
	//	}

	public String get_room_id(){
		return myRunGame.getCurrentRoom().toString();
	}

	//keep to make for player score instead of internal
	//game score
	//	public int get_score(){
	//		return null;
	//	}

	//	public void set_score(double score, boolean relative){
	//	}

	public double get_variable(String key){
		if(!myGlobalVariables.containsKey(key)){
			myGlobalVariables.put(key, 0.0);
		}
		return myGlobalVariables.get(key);
	}

	public void go_to_room(int roomNumber) {
		System.out.println("change room!");
		try {
			myRunGame.setCurrentRoom(roomNumber);
		} catch (GameRuntimeException ex) {
			fatalError(ex.getMessage());
		}	
		myEventManager.onRoomChanged(myRunGame.getCurrentRoom());
	}

	public void go_to_room(String name) {
		try {
			myRunGame.setCurrentRoom(myRunGame.getRoom(name));
		} catch (Exception ex) {
			fatalError(ex.getMessage());
		}	
		myEventManager.onRoomChanged(myRunGame.getCurrentRoom());
	}

	public void play_sound(String soundName) {
		RunSound sound = null;
		try {
			sound = myRunGame.fetchSound(soundName);
		} catch (UnknownResourceException ex) {
			fatalError(ex.getMessage());
		}
		sound.play();
	}

	//	public void save_game() {
	//	    
	//	}

	public void set_variable(String key, double value, boolean relative){
		if(relative){
			double oldValue = myGlobalVariables.get(key);
			myGlobalVariables.put(key, (oldValue + value));
		}
		else{
			myGlobalVariables.put(key, value);
		}
	}

	//	public void with(){
	//		//need to figure out
	//	}
	//	
	//	public void with_close(){
	//		//need to figure out
	//	}
	//	
	//	public void with_open(){
	//		//also need to figure this out
	//	}

	public void set_scroller_x(RunObject object, double xpercentage){
		double currentX = object.get_x_position();
		double currentY = myRunGame.getCurrentRoom().getView().getView().y();
		currentX = currentX - (1 - xpercentage) * myRunGame.getCurrentRoom().getView().getView().width();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

	public void set_scroller_y(RunObject object, double ypercentage){
		double currentX = myRunGame.getCurrentRoom().getView().getView().x();
		double currentY = object.get_y_position();
		currentY = currentY - ypercentage * myRunGame.getCurrentRoom().getView().getView().height();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

	public void set_scroller(RunObject object, double xpercentage, double ypercentage){
		double currentX = object.get_x_position();
		double currentY = object.get_y_position();
		currentX = currentX - (1 - xpercentage) * myRunGame.getCurrentRoom().getView().getView().width();
		currentY = currentY - ypercentage * myRunGame.getCurrentRoom().getView().getView().height();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

}
