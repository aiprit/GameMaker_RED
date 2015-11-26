package exceptions;

public class LibraryArgumentException extends GameRuntimeException {

	private static final long serialVersionUID = 8487792863241516299L;

	public LibraryArgumentException(String message, Object... args) {
		super(message, args);
	}

}
