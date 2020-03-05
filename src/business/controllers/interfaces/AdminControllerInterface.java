package business.controllers.interfaces;

import business.Book;
import business.LibraryMember;
import business.customExceptions.BookNotFoundException;

public interface AdminControllerInterface {

	void addMember(LibraryMember member);

	void addBook(Book book);

	void addBookCopy(String isbn) throws BookNotFoundException;

}
