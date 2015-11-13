package structures.data.actions.params;

import exceptions.ParameterParseException;

public interface IDouble extends IParameter {
	
	public double getDouble();
	
	// What is our default implementation?
	public static IDouble create(double num) {
		return new DoubleParam(num);
	}
	
	public static IDouble parse(String input) throws ParameterParseException {
		try {
			Double num = Double.parseDouble(input);
			return create(num);
		} catch (NumberFormatException ex) {
			 throw new ParameterParseException("Invalid number input: '%s'", input);
		}
	}
}
