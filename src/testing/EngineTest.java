package testing;

import exceptions.ParameterParseException;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.IParameter.type;

public class EngineTest {
	public static void main(String[] args) {
		
		
		IAction action = new MoveTo();
		for (IParameter param : action.getParameters()) {
			if (param.getType() == type.DOUBLE) {
				
				try {
					param.parse("3.14159");
				} catch (ParameterParseException e) {
					
					// This will actually be a useful message:
					System.out.println(e.getMessage());
				}
				
			}
		}
		
		
	}
}
