package playas;

public class PlayaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayaException() {
		super();
	}

	public PlayaException(String mensaje) {
		super(mensaje);
	}
}
