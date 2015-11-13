/**
 * 
 */
package engine;

import java.util.Queue;

import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author loganrooper
 *
 */
public class GamePlayHandler implements IGamePlayHandler {

		
	private Queue<InputEvent> inputs;
	private MouseEvent mouseState;
	
	public GamePlayHandler(Queue<InputEvent> inputs) {
		this.inputs = inputs;
	}

	@Override
	public void setOnEvent(InputEvent m) {
		inputs.add(m);
		if (m instanceof MouseEvent)
			mouseState = (MouseEvent) m;
	}
	
	public Queue<InputEvent> getEvents() {
		return inputs;
	}
	
	public MouseEvent getMouseState() {
		return mouseState;
	}
}
