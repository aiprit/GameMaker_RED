package exceptions;

public class CompileTimeException extends FormattedException {

	private static final long serialVersionUID = -8041964662117083073L;

	public CompileTimeException(String message) {
		super(message);
	}
	public CompileTimeException(String message, Object... args) {
		super(message, args);
	}

}
