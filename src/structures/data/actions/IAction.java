package structures.data.actions;

import java.util.List;

import exceptions.CompileTimeException;
import structures.data.actions.params.IParameter;

public interface IAction {
	public List<IParameter> getParameters();
	public String getName();
	public String compileSyntax() throws CompileTimeException;
}
