package engine.groovy_events;

import structures.run.RunObject;

public class GroovyCollisionEvent implements IGroovyEvent {
	
	public RunObject other;
	
	public GroovyCollisionEvent(RunObject other) {
		this.other = other;
	}
}
