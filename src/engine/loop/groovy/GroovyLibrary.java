package engine.loop.groovy;

import java.util.Random;
import engine.events.EventManager;
import engine.loop.InputManager;
import exceptions.GameRuntimeException;
import exceptions.LibraryArgumentException;
import exceptions.UnknownResourceException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunSound;
import utils.Point;

public class GroovyLibrary {

	private RunGame myRunGame;
	private EventManager myEventManager;
	private InputManager myInputManager;
	private GroovyGlobals myGlobals;

	public GroovyLibrary(RunGame runGame, EventManager eventManager) {
		myRunGame = runGame;
		myEventManager = eventManager;
		myInputManager = new InputManager(eventManager, false);
		myGlobals = new GroovyGlobals(0.0);
	}

	private void fatalError(String message, Object... args) {
		System.out.println(message);
		System.exit(1);
	}
	
	protected GroovyGlobals getGlobals() {
		return myGlobals;
	}
	
	public void print(String string) {
		System.out.println(string);
	}
	
	public boolean key_down(String keyCode) {
		try {
			return myInputManager.checkKey(keyCode.toUpperCase());
		} catch (LibraryArgumentException e) {
			return false;
		}
	}
	
	public boolean key_up(String keyCode) {
		return !key_down(keyCode);
	}
	
	public boolean global_set(String varName) {
		return myGlobals.isSet(varName);
	}
	
	public double mouse_x() {
		return myInputManager.mouseX();
	}
	
	public double mouse_y() {
		return myInputManager.mouseY();
	}
	
	public boolean mouse_primary() {
		return myInputManager.mousePrimaryDown();
	}
	
	public boolean mouse_secondary() {
		return myInputManager.mouseSecondaryDown();
	}
	

	public double random_number(double max){
		Random rand = new Random();
		return (max * rand.nextDouble());
	}

	public void create_instance(String objectName, double x, double y){
		RunObject runObject = null;
		try {
			runObject = myRunGame.getCurrentRoom().instantiate(objectName, x, y);
		} catch (GameRuntimeException e) {
			fatalError(e.getMessage());
		}
		runObject.setX(x);
		runObject.setY(y);
		myEventManager.onObjectCreate(runObject);
	}

	public void destroy(RunObject deleteThis) {
		myEventManager.onObjectDestroy(deleteThis);
	}

	public void draw_text(String text){
		myEventManager.addStringToDraw(text);
	}

	public void display_message(String message) throws InterruptedException, IllegalMonitorStateException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Message Dialogue");
		alert.setContentText(message);
		alert.show();
		try {
			alert.wait();
		} catch (IllegalMonitorStateException e) {
			// Do nothing
		}
	}

	public String get_room_id(){
		return myRunGame.getCurrentRoom().toString();
	}
	
	public String get_room_name() {
		return myRunGame.getCurrentRoom().getName();
	}

	public double get_high_score(){
		return myRunGame.getHighScore();
	}

	public void set_high_score(double score){
		myRunGame.setHighScore(score);
		myEventManager.setHighScore(score);
	}

	public void go_to_room(int roomNumber) {
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

	public void save_game() {
		myEventManager.onSave();
	}

	public void reset_game() {
		myEventManager.onReset();
	}

	public void wrap(RunObject check){
		double[] newCoordinates = wrapRecursion(check.getX(), check.getY());
		check.setX(newCoordinates[0]);
		check.setY(newCoordinates[1]);
	}

	private double[] wrapRecursion(double x, double y){
		if(x <= myRunGame.getCurrentRoom().getWidth() &&
				x >= 0 &&
				y <= myRunGame.getCurrentRoom().getHeight() &&
				y >= 0){
			double[] ret = new double[2];
			ret[0] = x;
			ret[1] = y;
			return ret;
		}
		if(x > myRunGame.getCurrentRoom().getWidth()){
			x = x - myRunGame.getCurrentRoom().getWidth();
		} else if(x < 0){
			x = x + myRunGame.getCurrentRoom().getWidth();
		} if(y > myRunGame.getCurrentRoom().getHeight()){
			y = y - myRunGame.getCurrentRoom().getHeight();
		} else if(y < 0){
			y = y + myRunGame.getCurrentRoom().getHeight();
		}
		return wrapRecursion(x, y);
	}

	public void set_scroller_x(RunObject object, double xpercentage){
		double currentX = object.getX();
		double currentY = myRunGame.getCurrentRoom().getView().getView().y();
		currentX = currentX - (1 - xpercentage) * myRunGame.getCurrentRoom().getView().getView().width();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

	public void set_scroller_y(RunObject object, double ypercentage){
		double currentX = myRunGame.getCurrentRoom().getView().getView().x();
		double currentY = object.getY();
		currentY = currentY - ypercentage * myRunGame.getCurrentRoom().getView().getView().height();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

	public void set_scroller(RunObject object, double xpercentage, double ypercentage){
		double currentX = object.getX();
		double currentY = object.getY();
		currentX = currentX - (1 - xpercentage) * myRunGame.getCurrentRoom().getView().getView().width();
		currentY = currentY - ypercentage * myRunGame.getCurrentRoom().getView().getView().height();
		Point location = new Point(currentX, currentY);
		myEventManager.setView(location);
	}

}
