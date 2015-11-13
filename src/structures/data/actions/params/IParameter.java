package structures.data.actions.params;

import exceptions.ParameterParseException;

public interface IParameter {
	
	public enum type {
		DOUBLE,
		INTEGER,
		STRING
	}
	
	public static IParameter parse(IParameter.type type, String input) throws ParameterParseException {
		switch (type) {
		case DOUBLE:
			return IDouble.parse(input);
		case INTEGER:
			return IInteger.parse(input);
		case STRING:
			return IString.create(input);
		default:
			throw new ParameterParseException("Invalid Parameter type: " + type);
		}			
	}
	
}
