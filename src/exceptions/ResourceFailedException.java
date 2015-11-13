package exceptions;

public class ResourceFailedException extends Exception {
	private static final long serialVersionUID = -1972252724670814972L;

	public ResourceFailedException(String resource) {
		System.err.println(String.format("Resource not found: %s", resource));
	}
}
