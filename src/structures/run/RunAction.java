package structures.run;

import org.codehaus.groovy.control.CompilationFailedException;

import exceptions.CompileTimeException;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public final class RunAction {
	
	public final String script;
	public final Script compiled;
	
	public RunAction(String script) throws CompileTimeException {
		this.script = script;
		GroovyShell shell = new GroovyShell();
		try {
			compiled = shell.parse(script);
		} catch (CompilationFailedException ex ) {
			throw new CompileTimeException("Compile error in script: %s", script);
		}
	}

}
