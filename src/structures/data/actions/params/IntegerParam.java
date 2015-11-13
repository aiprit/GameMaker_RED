package structures.data.actions.params;

public class IntegerParam implements IInteger {
	
	public final int myValue;
	
	public IntegerParam(int num) {
		myValue = num;
	}

	@Override
	public int getInteger() {
		return myValue;
	}

}
