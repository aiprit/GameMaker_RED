package structures.data.actions.params;

import exceptions.ParameterParseException;

public class CheckboxParam implements IParameter {
	
	private String myTitle;
	private boolean myValue;
	private String myString;
	
	public CheckboxParam(String title) {
		myTitle = title;
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		myValue = Boolean.parseBoolean(string);
		myString = string;
	}

	@Override
	public String getOriginal() {
		return myString;
	}

	@Override
	public String getTitle() {
		return myTitle;
	}
	
	public boolean getValue() {
		return myValue;
	}

}
