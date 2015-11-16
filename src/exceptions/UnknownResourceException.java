package exceptions;

public class UnknownResourceException extends FormattedException {

	private static final long serialVersionUID = 1088850472311283552L;

	public UnknownResourceException(String message, Object... args) {
		super(message, args);
	}
	
	public UnknownResourceException(String message) {
		super(message);
	}
}
