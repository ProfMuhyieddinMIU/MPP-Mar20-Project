package business.customExceptions;

import java.io.Serializable;

public class BookInvalidDataException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public BookInvalidDataException() {
		super();
	}

	public BookInvalidDataException(String msg) {
		super(msg);
	}

	public BookInvalidDataException(Throwable t) {
		super(t);
	}
}