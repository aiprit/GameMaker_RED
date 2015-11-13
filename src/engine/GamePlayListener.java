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
public class GamePlayListener implements IGamePlayListener {

	private Queue<InputEvent> inputs;
	public GamePlayListener(Queue<InputEvent> inputs) {
		this.inputs = inputs;
	}

	@Override
	public void setOnEvent(InputEvent m) {
		inputs.add(m);
	}
	
	public Queue<InputEvent> getEvents() {
		return inputs;
	}
}
