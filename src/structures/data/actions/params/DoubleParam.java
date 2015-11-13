package structures.data.actions.params;

public class DoubleParam implements IDouble {
	
	private final double myValue;
	
	public DoubleParam(double num) {
		myValue = num;
	}

	@Override
	public double getDouble() {
		return myValue;
	}
	
	
}
