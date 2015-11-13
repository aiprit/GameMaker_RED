package exceptions;

public class ParameterParseException extends FormattedException {
	private static final long serialVersionUID = -1979482724670814972L;

	public ParameterParseException(String string) {
		super(string);
	}
	public ParameterParseException(String message, Object... args) {
		super(message, args);
	}
}
