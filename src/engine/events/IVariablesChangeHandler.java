package engine.events;

import java.util.Map;

public interface IVariablesChangeHandler {

	public void updateGlobalVariables(Map<String, Double> globalVars);
	public void addLocalVariablesMap(long l, Map<String, Double> localVars);
	public void removeLocalVariablesMap(long l);
	public void clearLocalVariables();
	public void clearGlobalVariables();
	
}
