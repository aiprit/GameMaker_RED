package testing;

import XML.XMLEditor;
import exceptions.ParameterParseException;
import structures.run.RunObjectProxy;
import structures.TestGame2;
import structures.data.actions.script.RunScript;
import utils.Bresenham;
import utils.Utils;

public class CustomTests {
	public static void main(String[] args) throws Exception {
		
		groovyClassTests();
		XMLEditor e = new XMLEditor();
		//e.writeXML((new TestGame2()).getTestGame(directory), filename);
		//interpolationTest();
		//groovyTests();
	}
	
	public static void interpolationTest() {
		System.out.println(Bresenham.interpolate(0, 0, 5, -3));
	}
	
	public static void groovyTests() throws ParameterParseException {
		RunScript action = new RunScript();
		
		action.getParameters().get(0).parse("23.7776");
	}
	
	public static void groovyClassTests() {
		RunObjectProxy proxy = new RunObjectProxy();
	}
}
