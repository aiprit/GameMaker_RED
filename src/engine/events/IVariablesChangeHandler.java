package engine.events;

import java.util.Map;

public interface IVariablesChangeHandler {

	void updateGlobalVariables(Map<String, Double> globalVars);
	void addLocalVariablesMap(long l, Map<String, Double> localVars);
	void removeLocalVariablesMap(long l);
	void clearLocalVariables();
	void clearGlobalVariables();
	
}
