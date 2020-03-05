package business.customExceptions;

import java.io.Serializable;

public class BookNotFoundException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public BookNotFoundException() {
		super();
	}

	public BookNotFoundException(String msg) {
		super(msg);
	}

	public BookNotFoundException(Throwable t) {
		super(t);
	}
}