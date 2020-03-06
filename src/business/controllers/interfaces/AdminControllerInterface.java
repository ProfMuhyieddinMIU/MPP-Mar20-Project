package business.controllers.interfaces;

import business.LibraryMember;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.MemberInvalidDataException;

public interface AdminControllerInterface {

	LibraryMember addMember(String firstName, String lastName, String telephone, String email, String street,
			String state, String city, String zip) throws MemberInvalidDataException;

	void addBook(String firstName, String lastName, String phone);

	void addBookCopy(String isbn, int numOfCopies) throws BookNotFoundException;

}
