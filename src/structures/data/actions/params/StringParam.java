package structures.data.actions.params;

import exceptions.ParameterParseException;

public class StringParam implements IParameter {
	
	private String myValue;
	private String myTitle;

	@Override
	public void parse(String string) throws ParameterParseException {
		myValue = string;
	}

	@Override
	public String getOriginal() {
		return myValue;
	}
	
	@Override
	public String getTitle() {
		return myTitle;
	}

	@Override
	public String getValue() {
		return myValue;
	}
	
}
