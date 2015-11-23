package engine.loop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import engine.events.EventManager;
import engine.events.IInputHandler;
import engine.loop.groovy.GroovyClickEvent;
import exceptions.LibraryArgumentException;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.events.IDataEvent;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Point;

public class InputManager implements IInputHandler {
	
	private Queue<InputEvent> myInputQueue;
	private Map<KeyCode, Boolean> myKeyMap;
	
	public InputManager(EventManager eventManager) {
		myInputQueue = new LinkedList<>();
		myKeyMap = new HashMap<>();
		
		eventManager.addUserInputInterface(this);
	}
	
	/**
	 * Calls each InputEvent on the correct corresponding
	 * RunObject, and then clears the events.
	 * 
	 */
	public void processInputEvents(IGameEventHandler gameHandler) {
		InputEvent e;
		while ((e = myInputQueue.poll()) != null) {	

			List<IDataEvent> runEvents = InputEventFactory.getEvents(e);
			for (IDataEvent runEvent : runEvents){
				GroovyClickEvent event = new GroovyClickEvent(runEvent);
				if (event.hasXY()){
					event.setCoordinates(correctForView(gameHandler.getCurrentRoom(), InputEventFactory.getCoordinates(e)));
				}
				List<RunObject> os = gameHandler.getRegistered(runEvent);
				for (RunObject o : os){
					if (event.getLocalCheck()){
						if (o.getBounds().contains(event.getCoordinates())){
							gameHandler.fire(o, runEvent, event);
						}
					} else {
						gameHandler.fire(o, runEvent, event);
					}
				}
			}
		}
	}
	
	private Point correctForView(RunRoom room, Point p) {
		return new Point(	p.x + room.getView().getView().x(),
							p.y + room.getView().getView().y());
	}
	
	public boolean checkKey(KeyCode code) {
		return myKeyMap.getOrDefault(code, false);
	}
	
	public boolean checkKey(String keyCode) throws LibraryArgumentException {
		KeyCode code = KeyCode.getKeyCode(keyCode);
		if (code == null) {
			throw new LibraryArgumentException("Unknown KeyCode: '%s'", keyCode);
		}
		return checkKey(code);
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		myInputQueue.add(event);
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			if (!checkKey(event.getCode())) {
				myInputQueue.add(event);
			}
			myKeyMap.put(event.getCode(), true);
		} else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
			if (checkKey(event.getCode())) {
				myInputQueue.add(event);
			}
			myKeyMap.put(event.getCode(), false);
		}
	}
}
