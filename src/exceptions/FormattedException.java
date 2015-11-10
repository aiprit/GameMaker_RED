package exceptions;

public class FormattedException extends Exception {

	private static final long serialVersionUID = -1097443745370147801L;

	public FormattedException(String message) {
		super(message);
	}
	
	public FormattedException(String message, Object... args) {
		this(String.format(message, args));
	}

}
