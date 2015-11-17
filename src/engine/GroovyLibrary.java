package engine;

import exceptions.UnknownResourceException;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunSound;

public class GroovyLibrary {
    
        private RunGame myRunGame;
        IObjectModifiedHandler myOMH;
        IRoomChangedHandler myRCH;
    
        public GroovyLibrary(RunGame runGame) {
            myRunGame = runGame;
        }
        
        public void setObectModifiedHandler(IObjectModifiedHandler objectModifiedHandler) {
            myOMH = objectModifiedHandler;
        }
        
        public void setRoomChangedHandler(IRoomChangedHandler roomChangedHandler) {
            myRCH = roomChangedHandler;
        }

	public void create_object(String id, double x, double y){
	    RunObject runObject = new RunObject(id);
	    runObject.x = x;
	    runObject.y = y;
	    myOMH.onObjectCreate(runObject);
	    myRunGame.getCurrentRoom().getObjects().add(runObject);
	}
	
	public void create_object_long(String id, double x, double y, double acceleration,
			double friction, boolean wraparound){
	    RunObject runObject = new RunObject(id);
            runObject.x = x;
            runObject.y = y;
            runObject.set_acceleration(acceleration);
            runObject.set_friction(friction);
            runObject.wrap_around_room(wraparound);
            myOMH.onObjectCreate(runObject);
            myRunGame.getCurrentRoom().getObjects().add(runObject);
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
	
//	public void get_score(){
//	    
//	}
	
//	public void get_variable(String key){
//	    
//		
//	}
	
	public void go_to_room(int roomNumber){
		myRunGame.setCurrentRoom(roomNumber);
		myRCH.onRoomChanged(myRunGame.getCurrentRoom());
	}
	
//	public void open_webpage(String path){
//		
//	}
	
	public void play_sound(String soundName) throws UnknownResourceException{
	    RunSound sound = myRunGame.getSound(soundName);
	    sound.play();
	}
	
//	public void save_game() {
//	    
//	}
	
//	public void set_score(double score){
//		
//	}
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
