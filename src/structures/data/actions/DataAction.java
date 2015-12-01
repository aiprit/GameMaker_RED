package structures.data.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;

import exceptions.CompileTimeException;
import structures.data.actions.params.IParameter;
import utils.Utils;

/**
 * Multiple Actions can be attached to an Event within an Object.
 * The engine doesn't care about the actionType or anything else,
 * it just pulls the compiled String. The authoring environment
 * does care very much about the type and is very careful about
 * what goes into the argument List.
 */
public abstract class DataAction implements IAction {

	protected List<IParameter> myParameters;

	protected abstract String getSyntax();

	public List<IParameter> getParameters() {
		return Collections.unmodifiableList(myParameters);
	}

	public String compileSyntax() throws CompileTimeException {
		return compile(getSyntax());
	}

    public String compile(String syntax) throws CompileTimeException {
    	List<Object> params = Utils.transform(getParameters(), e -> e.getValue());
    	try {
    		return String.format(syntax, params.toArray());
    	} catch (IllegalFormatException ex) {
    		throw new CompileTimeException("Bad arguments for Action '%s'", this.getTitle());
    	}
    }

    public void init(IParameter... parameters) {
    	myParameters = new ArrayList<>(Arrays.asList(parameters));
    }

    protected IParameter get(String name) {
    	return Utils.first(myParameters, e -> (e.getTitle() == name), null);
    }


    @Override
    public String toString(){
    	return this.getDescription();
    }
}
