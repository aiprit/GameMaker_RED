package structures.data.actions.params;

import org.codehaus.groovy.control.CompilationFailedException;

import exceptions.ParameterParseException;
import groovy.lang.GroovyShell;

public class GroovyParam implements IParameter {
	
	private String myScript;
	private String myTitle;
	
	public GroovyParam(String title) {
		myTitle = title;
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		GroovyShell shell = new GroovyShell();
		try {
			shell.parse(string);
			myScript = string;
		} catch (CompilationFailedException ex ) {
			throw new ParameterParseException("Compile error in script: " + ex);
		}
	}

	@Override
	public String getOriginal() {
		return myScript;
	}

	@Override
	public String getTitle() {
		return myTitle;
	}

	@Override
	public String getValue() {
		return myScript;
	}

	@Override
	public type getType() {
		return IParameter.type.GROOVY;
	}

}
