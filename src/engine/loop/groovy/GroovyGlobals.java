package engine.loop.groovy;

import java.util.HashMap;
import java.util.Map;

import engine.events.EventManager;
import exceptions.GameRuntimeException;

public class GroovyGlobals {
	
	private Double myDefaultValue;
	private boolean myErrorOnDefault = true;
	private Map<String, Double> myVariables;
	private EventManager myEventManager;
	
	public GroovyGlobals() {
		myVariables = new HashMap<>();
	}
	
	public GroovyGlobals(Double defaultValue, EventManager eventManager) {
		this();
		myEventManager = eventManager;
		myDefaultValue = defaultValue;
		myErrorOnDefault = false;
	}
	
	public GroovyGlobals(Map<String, Double> globalVariables, EventManager eventManager){
		myVariables = globalVariables;
		myEventManager = eventManager;
		myDefaultValue = 0.0;
		myErrorOnDefault = false;
	}
	
	public Map<String, Double> getGlobalVariableMap(){
		return myVariables;
	}
	
	public Double get_variable(String name){
		if(!myVariables.containsKey(name)){
			myVariables.put(name, 0.0);
		}
		return myVariables.get(name);
	}
	
	public void put_variable(String name, Double value){
		myVariables.put(name, value);
		myEventManager.globalVariableUpdate();
	}
	
	public boolean isSet(String name) {
		return myVariables.containsKey(name);
	}
	
	public void propertyMissing(String name, Double value) {
		myVariables.put(name, value);
	}
	
	public Object propertyMissing(String name) throws GameRuntimeException {
		Object result = myVariables.get(name);
		if (result != null) {
			return result;
		} else if (myErrorOnDefault) {
			throw new GameRuntimeException("Unknown global variable: '%s'", name);
		} else {
			myVariables.put(name, myDefaultValue);
			return myDefaultValue;
		}
	}
}
