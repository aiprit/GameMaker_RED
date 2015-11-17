package exceptions;

public class GameRuntimeException extends FormattedException {

	private static final long serialVersionUID = 4641373559726109589L;

	public GameRuntimeException(String message, Object... args) {
		super(message, args);
	}

}
