package engine;

import engine.events.EventManager;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import structures.run.RunAction;
import structures.run.RunGame;
import structures.run.RunObject;

public class GroovyEngine {
	
	//starts the first room loop
	GroovyLibrary myGroovyLibrary;
	
	public GroovyEngine(RunGame runGame, EventManager eventManager){
		myGroovyLibrary = new GroovyLibrary(runGame, eventManager);
	}
	
	public void runScript(RunObject o, RunAction action, GroovyEvent event){
		if(action == null){
			return;
		}
		System.out.println(action.script);
		Binding binding = new Binding();
		binding.setProperty("current", o);
		binding.setProperty("library", myGroovyLibrary);
		binding.setProperty("event", event);
		GroovyShell shell = new GroovyShell(binding);
		shell.evaluate(action.script);
	}
	
	public void runScript(RunObject o, RunAction action){
		if(action == null){
			return;
		}
		System.out.println(action.script);
		Binding binding = new Binding();
		binding.setProperty("current", o);
		binding.setProperty("library", myGroovyLibrary);
		GroovyShell shell = new GroovyShell(binding);
		shell.evaluate(action.script);
	}

}
