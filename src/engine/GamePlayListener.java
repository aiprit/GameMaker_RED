/**
 * 
 */
package engine;

import java.util.LinkedList;
import java.util.Queue;

import engine.events.IGamePlayHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author loganrooper
 *
 */
public class GamePlayListener implements IGamePlayHandler {

	private Queue<InputEvent> inputs;
	private MouseEvent mouseState;
	
	public GamePlayListener() {
		this.inputs = new LinkedList<InputEvent>();
	}

	@Override
	public void setOnEvent(InputEvent m) {
		inputs.add(m);
	}
	
	public Queue<InputEvent> getEvents() {
		return inputs;
	}
	
	public MouseEvent getMouseState() {
		return mouseState;
	}
}
