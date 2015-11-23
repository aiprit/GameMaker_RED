package engine.loop.groovy;

import engine.events.EventManager;
import structures.run.RunAction;
import structures.run.RunGame;
import structures.run.RunObject;

public class GroovyEngine {
	
	private GroovyLibrary myGroovyLibrary;
	
	public GroovyEngine(RunGame runGame, EventManager eventManager){
		myGroovyLibrary = new GroovyLibrary(runGame, eventManager);
	}
	
	public void runScript(RunObject o, RunAction action, IGroovyEvent event){
		if (action == null) {
			return;
		}
		System.out.println(action.script);
		action.compiled.setProperty("library", myGroovyLibrary);
		action.compiled.setProperty("current", o);
		action.compiled.setProperty("event", event);
		action.compiled.run();
	}

}
