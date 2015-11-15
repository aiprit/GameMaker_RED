package engine;

import java.util.List;
import java.util.Queue;
import exceptions.CompileTimeException;
import javafx.scene.input.InputEvent;
import structures.run.RunGame;
import structures.run.RunObject;

public class EventManager {
	
	private RunGame myGame;
	Queue<InputEvent> inputs;
	
	public EventManager(RunGame game, Queue<InputEvent> inputs){
		myGame = game;
		this.inputs = inputs;
	}
	
	void loop() throws CompileTimeException {
		List<RunObject> it = myGame.getCurrentRoom().getObjects();
		step(it);
		checkKeyEvents(it);
		checkMouseEvents(it);
	}
	
	private void step(List<RunObject> it) {
		
	}

	private void checkKeyEvents(List<RunObject> it) {
		for (RunObject obj : it) {
			//Iterate through obj's events, see if there is a matching one in the queue
			
		}
	}

	private void checkMouseEvents(List<RunObject> it) {
		
	}

}
