package structures.data.actions.params;

import org.codehaus.groovy.control.CompilationFailedException;

import exceptions.ParameterParseException;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyParam implements IParameter {
	
	private String myScript;
	private String myTitle;
	private Script myCompiled;
	
	public GroovyParam(String title) {
		myTitle = title;
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		GroovyShell shell = new GroovyShell();
		try {
			myCompiled = shell.parse(string);
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
	public Script getValue() {
		return myCompiled;
	}

	@Override
	public type getType() {
		return IParameter.type.GROOVY;
	}

}
