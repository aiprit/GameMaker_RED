package structures.data.actions.params;

import exceptions.ParameterParseException;

public class StringParam implements IParameter {
	
	private String myValue;
	private String myTitle;
	
	public StringParam(String title) {
		myTitle = title;
	}

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
	
	@Override
	public IParameter.type getType() {
		return IParameter.type.STRING;
	}
	
}
