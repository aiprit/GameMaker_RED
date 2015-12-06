package engine.loop.groovy;

import java.util.HashMap;
import java.util.Map;

import exceptions.GameRuntimeException;


public class GroovyGlobals {
	
	private Double myDefaultValue;
	private boolean myErrorOnDefault = true;
	private Map<String, Double> myVariables;
	
	public GroovyGlobals() {
		myVariables = new HashMap<>();
	}
	
	public GroovyGlobals(Double defaultValue) {
		this();
		myDefaultValue = defaultValue;
		myErrorOnDefault = false;
	}
	
	public Double get_variable(String name){
		return myVariables.get(name);
	}
	
	public void put_variable(String name, Double value){
		myVariables.put(name, value);
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
