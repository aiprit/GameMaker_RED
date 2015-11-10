package structures.data;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import exceptions.CompileTimeException;

/**
 * Multiple Actions can be attached to an Event within an Object.
 * The engine doesn't care about the actionType or anything else,
 * it just pulls the compiled String. The authoring environment
 * does care very much about the type and is very careful about
 * what goes into the argument List.
 *
 */
public class DataAction {

	private final String myActionType;
	private String mySyntax;
    private List<String> myArguments;
    
    public DataAction(String actionType) {
    	myActionType = actionType;
    	myArguments = new ArrayList<String>();
    }
    
    public void setSyntax(String syntax) {
    	mySyntax = syntax;
    }
    
    public void setArguments(List<String> arguments) {
    	myArguments = arguments;
    }
    
    public List<String> getArguments() {
    	return myArguments;
    }
    
    public String compileSyntax() throws CompileTimeException {
    	Object[] args = myArguments.toArray(new String[myArguments.size()]);
    	try {
    		return String.format(mySyntax, args);
    	} catch (IllegalFormatException ex) {
    		throw new CompileTimeException("Bad arguments for Action '%s': %s", myActionType, myArguments);
    	}
    }
}
