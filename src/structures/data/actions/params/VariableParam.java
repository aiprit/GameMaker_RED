package structures.data.actions.params;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ParameterParseException;

public class VariableParam implements IParameter {

	private final boolean isGlobal;
	private final String myName;
	
	public VariableParam(String name, boolean global) throws ParameterParseException {
		isGlobal = global;
		myName = name;
		
		Pattern regex = Pattern.compile("[^0-9a-zA-Z_]");
		Matcher matcher = regex.matcher(name);
		if (matcher.find()) {
			throw new ParameterParseException("Invalid variable name: '%s'", name);
		}
		
	}
	
	public boolean isGlobal() {
		return isGlobal;
	}
	
	public String getName() {
		return myName;
	}

}
