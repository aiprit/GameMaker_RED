package testing;

import exceptions.ParameterParseException;
import structures.data.actions.RunScript;
import utils.Bresenham;
import utils.Utils;

public class CustomTests {
	public static void main(String[] args) throws Exception {
		
		interpolationTest();
		groovyTests();
	}
	
	public static void interpolationTest() {
		System.out.println(Bresenham.interpolate(0, 0, 5, -3));
	}
	
	public static void groovyTests() throws ParameterParseException {
		RunScript action = new RunScript();
		
		action.getParameters().get(0).parse("23.7776");
	}
}
