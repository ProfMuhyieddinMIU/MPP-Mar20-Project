package business.controllers.impl;

import java.util.HashMap;
import java.util.Map;

import business.Address;
import business.Book;
import business.LibraryMember;
import business.controllers.interfaces.AdminControllerInterface;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.MemberInvalidDataException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AdminController implements AdminControllerInterface {

	public static void main(String[] args) throws MemberInvalidDataException {

		AdminController a = new AdminController();
		// for test AdminController only
		System.out.println("test addMember ==> "
				+ a.addMember("Most", "Moha", "641-472-2558", "e@f.com", "ss", "iowa", "dd", "11"));

		try {
			a.addBookCopy("28-12331");
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private void validateMemberData(LibraryMember member) throws MemberInvalidDataException {
		if (member.getMemberId().isEmpty() || member.getFirstName().isEmpty() || member.getLastName().isEmpty())
			throw new MemberInvalidDataException(" Member Id , First Name and Last Name Fields Can not be empty !");

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
			throw new BookNotFoundException("No Book Found With ISBN : " + isbn);

		book.addCopy();
		da.updateBook(book);

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

	@Override
	public LibraryMember addMember(String firstName, String lastName, String telephone, String email, String street,
			String state, String city, String zip) throws MemberInvalidDataException {

		DataAccess da = new DataAccessFacade();
		Address address = new Address(street, city, state, zip);

		LibraryMember member = new LibraryMember(generateMemberId(), firstName, lastName, telephone, address, email);
		validateMemberData(member);
		da.saveNewMember(member);
		return member;
	}

	private String generateMemberId() {
		SystemController systemController = new SystemController();

		int generatedId = systemController.allMemberIds().size() + 1001;
		return "" + generatedId;
	}

	@Override
	public void addBook(String firstName, String lastName, String phone) {
		DataAccess da = new DataAccessFacade();
		// validateBookData(book);
		// da.saveNewBook(book);

	}

	private void validateBookData(Book book) {

	}

}
