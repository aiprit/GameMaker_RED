package engine.loop.groovy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import engine.events.EventManager;
import groovy.GroovyObjectInstantiator;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import structures.run.RunAction;
import structures.run.RunGame;
import structures.run.RunObject;

public class GroovyEngine {
	
	private GroovyLibrary myGroovyLibrary;
	private Deque<Object> myCurrentStack;
	private Closure<?> myCurrentClosure;
	private Object myGroovyEngineAccess;
	
	private static double avg = 0.0;
	private static long times = 1;
	
	public GroovyEngine(RunGame runGame, EventManager eventManager){
		myGroovyLibrary = new GroovyLibrary(runGame, eventManager);
		myCurrentStack = new ArrayDeque<>();
		
		// Allow access to .current() and .with() from Groovy
		GroovyShell shell = new GroovyShell();
		shell.setProperty("engine", this);
		myCurrentClosure = (Closure<?>) shell.evaluate("{ -> engine.current() }");
		myGroovyEngineAccess = GroovyObjectInstantiator.instantiate("GroovyEngineAccess", this);
	}
	
	public void runScript(RunObject o, RunAction action, IGroovyEvent event) {
		long now = System.currentTimeMillis();
		if (action == null) {
			return;
		}
		myCurrentStack.clear();
		myCurrentStack.push(o);
		action.compiled.setProperty("library", myGroovyLibrary);
		action.compiled.setProperty("globals", myGroovyLibrary.getGlobals());
		action.compiled.setProperty("current", myCurrentClosure);
		action.compiled.setProperty("engine", myGroovyEngineAccess);	
		action.compiled.setProperty("event", event);
		try {
			action.compiled.run();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(action.script);
		}
		double diff = System.currentTimeMillis() - now;
		if (avg == 0.0) {
			avg = diff;
		} else {
			avg = (diff + times * avg) / (double)(times + 1);
		}
		if (times % 120 == 0) {
			System.out.println(String.format("Engine avg period: %.2f ms", (double)diff));
		}
		times++;
	}
	
	public Object current() {
		return myCurrentStack.peek();
	}
	
	public Object with() {
		Object with = null;
		with = myCurrentStack.peek();
		myCurrentStack.push(with);
		return with;
	}
	
	public Object with(Object with) {
		myCurrentStack.push(with);
		return with;
	}
	
	public void end() {
		myCurrentStack.pop();
	}
}
