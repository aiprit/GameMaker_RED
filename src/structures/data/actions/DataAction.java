package structures.data.actions;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import exceptions.CompileTimeException;
import structures.data.actions.params.IParameter;

/**
 * Multiple Actions can be attached to an Event within an Object.
 * The engine doesn't care about the actionType or anything else,
 * it just pulls the compiled String. The authoring environment
 * does care very much about the type and is very careful about
 * what goes into the argument List.
 */

public class DataAction implements IAction {

	private List<IParameter> myParameters;
    
    public DataAction() {

    }
    
    public void setSyntax(String syntax) {
    }
    
    public String compileSyntax() throws CompileTimeException {
    	/*
    	List
    	
    	Object[] args = myParameters.toArray(new String[myArguments.size()]);
    	try {
    		return String.format(mySyntax, args);
    	} catch (IllegalFormatException ex) {
    		throw new CompileTimeException("Bad arguments for Action '%s': %s", myActionType, myArguments);
    	}*/
    	return "";
    }

	@Override
	public List<IParameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
