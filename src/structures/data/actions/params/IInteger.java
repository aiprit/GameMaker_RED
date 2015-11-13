package structures.data.actions.params;

import exceptions.ParameterParseException;

public interface IInteger extends IParameter {
	
	public int getInteger();

	// What is our default implementation?
	public static IInteger create(int num) {
		return new IntegerParam(num);
	}
	
	public static IInteger parse(String input) throws ParameterParseException {
		try {
			int num = Integer.parseInt(input);
			return create(num);
		} catch (NumberFormatException ex) {
			 throw new ParameterParseException("Invalid integer input: '%s'", input);
		}
	}
}
