package business.customExceptions;

import java.io.Serializable;

public class BookAlreadyExistException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public BookAlreadyExistException() {
		super();
	}

	public BookAlreadyExistException(String msg) {
		super(msg);
	}

	public BookAlreadyExistException(Throwable t) {
		super(t);
	}
}