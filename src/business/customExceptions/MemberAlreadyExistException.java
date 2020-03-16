package business.customExceptions;

import java.io.Serializable;

public class MemberAlreadyExistException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public MemberAlreadyExistException() {
		super();
	}

	public MemberAlreadyExistException(String msg) {
		super(msg);
	}

	public MemberAlreadyExistException(Throwable t) {
		super(t);
	}
}