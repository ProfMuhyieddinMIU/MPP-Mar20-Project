package business.controllers.interfaces;

import java.util.List;

import com.sun.media.sound.InvalidDataException;

import business.Author;
import business.LibraryMember;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.MemberInvalidDataException;

public interface AdminControllerInterface {
	LibraryMember addMember(String firstName, String lastName, String telephone, String email, String street,
			String state, String city, String zip) throws MemberInvalidDataException;

	void addBookCopy(String isbn, int numOfCopies) throws BookNotFoundException, BookInvalidDataException;

	// Hus3/6/20::addBook correct parameters
	void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) throws InvalidDataException;

}
