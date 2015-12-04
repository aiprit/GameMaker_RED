package authoring_environment.room;

public class InvalidConfigureException extends Exception {
	private String myBadInput;
	
	public InvalidConfigureException(String badInput) {
		super(badInput);
		myBadInput = badInput;
	}
	
	public String getBadInput() {
		return myBadInput;
	}
}
