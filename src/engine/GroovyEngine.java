package engine;

import engine.events.EventManager;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import structures.run.RunAction;
import structures.run.RunGame;
import structures.run.RunObject;

public class GroovyEngine {
	
	private GroovyLibrary myGroovyLibrary;
	private GroovyShell myShell;
	private Binding myBinding;
	
	
	public GroovyEngine(RunGame runGame, EventManager eventManager){
		myGroovyLibrary = new GroovyLibrary(runGame, eventManager);
		myBinding = new Binding();
		myBinding.setProperty("library", myGroovyLibrary);
		myShell = new GroovyShell(myBinding);
	}
	
	public void runScript(RunObject o, RunAction action, GroovyEvent event){
		if(action == null){
			return;
		}
		System.out.println(action.script);
		action.compiled.setProperty("library", myGroovyLibrary);
		action.compiled.setProperty("current", o);
		action.compiled.setProperty("event", event);
		action.compiled.run();
	}
	
	public void runScript(RunObject o, RunAction action){
		if(action == null){
			return;
		}
		System.out.println(action.script);
		action.compiled.setProperty("library", myGroovyLibrary);
		action.compiled.setProperty("current", o);
		action.compiled.run();
	}

}
