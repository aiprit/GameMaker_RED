package structures.data.actions.params;

import exceptions.ParameterParseException;

public interface IParameter {
	public void parse(String string) throws ParameterParseException;
	public String getOriginal();
	public String getTitle();
	public Object getValue();
	
	public enum type {
		DOUBLE,
		INTEGER,
		STRING,
		CHECKBOX
	}
}
