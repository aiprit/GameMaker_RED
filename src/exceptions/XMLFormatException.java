package exceptions;

public class XMLFormatException extends FormattedException {

	private static final long serialVersionUID = -7890928188812257631L;

	public XMLFormatException(String message, Object... args) {
		super(message, args);
	}

}
