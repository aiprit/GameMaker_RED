package structures.data.actions.params;

public interface IString extends IParameter {
	public String getString();
	
	// What is our default implementation?
	public static IString create(String str) {
		return new StringParam(str);
	}
}
