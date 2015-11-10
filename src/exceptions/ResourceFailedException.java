package exceptions;

public class ResourceFailedException extends FormattedException {

	private static final long serialVersionUID = -1972252724670814972L;

	public ResourceFailedException(String string) {
		super(string);
	}
	public ResourceFailedException(String message, Object... args) {
		super(message, args);
	}
	
}
