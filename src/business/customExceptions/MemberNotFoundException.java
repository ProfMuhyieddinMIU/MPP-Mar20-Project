package business.customExceptions;

import java.io.Serializable;

public class MemberNotFoundException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public MemberNotFoundException() {
		super("Member not found");
	}

	public MemberNotFoundException(String msg) {
		super(msg);
	}

	public MemberNotFoundException(Throwable t) {
		super(t);
	}
}