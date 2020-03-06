package business.controllers.impl;

import java.util.HashMap;
import java.util.Map;

import business.Book;
import business.LibraryMember;
import business.controllers.interfaces.AdminControllerInterface;
import business.customExceptions.BookNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AdminController implements AdminControllerInterface {

	/**
	 * 
	 * @param member
	 */
	@Override
	public void addMember(LibraryMember member) {
		DataAccess da = new DataAccessFacade();
		validateMemberData(member);
		da.saveNewMember(member);

	}

	private void validateMemberData(LibraryMember member) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param book
	 */
	@Override
	public void addBook(Book book) {
		DataAccess da = new DataAccessFacade();
		validateBookData(book);
		da.saveNewBook(book);

	}

	private void validateBookData(Book book) {

	}

	/**
	 * 
	 * @param isbn
	 */
	@Override
	public void addBookCopy(String isbn) throws BookNotFoundException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		Book book = searchBookInMap(isbn, map);

		if (book == null)
			throw new BookNotFoundException();

		book.addCopy();

	}

	/**
	 * 
	 * @param isbn
	 * @param map
	 * @return
	 */
	private Book searchBookInMap(String isbn, HashMap<String, Book> map) {

		for (Map.Entry<String, Book> entry : map.entrySet()) {
			if (entry.getKey().equals(isbn))
				return entry.getValue();
		}
		return null;

	}

}
