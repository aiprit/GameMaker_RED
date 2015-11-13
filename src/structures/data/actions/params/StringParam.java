package structures.data.actions.params;

public class StringParam implements IString {
	
	public final String myValue;
	
	public StringParam(String str) {
		myValue = str;
	}

	@Override
	public String getString() {
		return myValue;
	}
}
