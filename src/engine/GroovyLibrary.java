package engine;

import engine.events.IObjectModifiedHandler;
import engine.events.IRoomChangedHandler;
import exceptions.GameRuntimeException;
import exceptions.UnknownResourceException;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunSound;

public class GroovyLibrary {

	private RunGame myRunGame;
	IObjectModifiedHandler myOMH;
	IRoomChangedHandler myRCH;
	
	int score = 0;

	public GroovyLibrary(RunGame runGame) {
		myRunGame = runGame;
	}

	public void setObjectModifiedHandler(IObjectModifiedHandler objectModifiedHandler) {
		myOMH = objectModifiedHandler;
	}

	public void setRoomChangedHandler(IRoomChangedHandler roomChangedHandler) {
		myRCH = roomChangedHandler;
	}

	private void fatalError(String message, Object... args) {
		// Do nothing
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
		myOMH.onObjectCreate(runObject);
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
		myOMH.onObjectCreate(runObject);
	}

	public void display_message(String message){

	}
	//	
	//	public void draw_rectangle(double x, double y, double width, double height, String color,
	//			boolean border, double borderWidth){
	//		
	//	}
	//	
	//	public void draw_text(String text){
	//		
	//	}

	//	public void get_mouse_state(){
	//		//use in GetMouseState to see if right or left etc.
	//	}

	public String get_room_id(){
		return myRunGame.getCurrentRoom().toString();
	}

	public int get_score(){
		return score;
	}

	//	public void get_variable(String key){
	//	    
	//		
	//	}

	public void go_to_room(int roomNumber) {
		System.out.println("change room!");
		try {
			myRunGame.setCurrentRoom(roomNumber);
		} catch (GameRuntimeException ex) {
			fatalError(ex.getMessage());
		}	
		myRCH.onRoomChanged(myRunGame.getCurrentRoom());
	}

	public void go_to_room(String name) {
		try {
			myRunGame.setCurrentRoom(myRunGame.getRoom(name));
		} catch (Exception ex) {
			fatalError(ex.getMessage());
		}	
		myRCH.onRoomChanged(myRunGame.getCurrentRoom());
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

	//	public void set_score(double score){
	//		
	//	}
	
	public void change_score(int score){
		this.score += score;
	}
	
	//	
	//	public void set_variable(String key, double value){
	//		
	//	}

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

}
