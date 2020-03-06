package business.controllers.interfaces;

import business.customExceptions.LibrarySystemException;

public interface EmailSenderControllerInterface {

	public void sendEmail(String email, String bookTitle, String isbn, String memberName) throws LibrarySystemException;
}
