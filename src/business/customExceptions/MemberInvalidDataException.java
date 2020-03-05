package business.customExceptions;
import java.io.Serializable;

public class MemberInvalidDataException extends LibrarySystemException implements Serializable {
	private static final long serialVersionUID = 3326915348398932420L;

	public MemberInvalidDataException () {
		super();
	}

	public MemberInvalidDataException (String msg) {
		super(msg);
	}

	public MemberInvalidDataException (Throwable t) {
		super(t);
	}
}