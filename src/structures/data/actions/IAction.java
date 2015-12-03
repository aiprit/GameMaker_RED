package structures.data.actions;

import java.util.List;

import exceptions.CompileTimeException;
import structures.data.actions.params.IParameter;

/**
 * An Object has multiple Events, which all have a List of these.
 * An IAction in turn has multiple IParameters.
 * 
 * NOTE: All the IParameters in .getParameters() must have .parse()
 * called on them first, before .compileSyntax() or .getDescription()
 * can be called.
 * 
 * @author Austin
 *
 */
public interface IAction {
	
	/**
	 * Our IParameters as a read-only List. Make sure
	 * to call .parse() on these.
	 * 
	 * @return
	 */
	List<IParameter> getParameters();
	
	/**
	 * The title
	 * 
	 * @return
	 */
	String getTitle();
	
	/**
	 * The description of the IAction. In most cases, it updates
	 * itself to reflect the values of the parameters.
	 * @return
	 */
	String getDescription();
	
	/**
	 * Returns the Groovy code using a template and the values of
	 * the parsed arguments. Only used by the Engine. 
	 * 
	 * @return
	 * @throws CompileTimeException
	 */
	String compileSyntax() throws CompileTimeException;
	
}
