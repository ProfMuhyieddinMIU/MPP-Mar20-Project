package business.controllers.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.media.sound.InvalidDataException;

import business.Address;
import business.Author;
import business.Book;
import business.LibraryMember;
import business.controllers.interfaces.AdminControllerInterface;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.LibrarySystemException;
import business.customExceptions.MemberInvalidDataException;
import business.customExceptions.MemberNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AdminController implements AdminControllerInterface {

	public static void main(String[] args) throws LibrarySystemException {

		AdminController a = new AdminController();
		// for test AdminController only
		/*
		 * System.out.println("test addMember ==> " + a.addMember("Most", "Moha",
		 * "641-472-2558", "e@f.com", "ss", "iowa", "dd", "11"));
		 */

		EmailSenderController em = new EmailSenderController();
		em.sendEmail("", "White Cards", "28-1147", "");
		/*
		 * try { a.addBookCopy("28-12331", 3); } catch (BookNotFoundException |
		 * BookInvalidDataException e) { // TODO Auto-generated catch block
		 * System.out.println(e.getMessage()); }
		 */

	}

	private void validateMemberData(String firstName, String lastName, String email) throws MemberInvalidDataException {
		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
			throw new MemberInvalidDataException("First Name , Last Name and Email Fields Can not be empty !");
	}

	/**
	 * 
	 * @param isbn
	 * @param numOfCopies
	 * @throws BookInvalidDataException
	 */
	@Override
	public void addBookCopy(String isbn, int numOfCopies) throws BookNotFoundException, BookInvalidDataException {
		validateAddCopyData(isbn);

		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		Book book = searchBookInMap(isbn, map);

		if (book == null)
			throw new BookNotFoundException("Book with ISBN : " + isbn + "  Is not Exist!");
		for (int i = 0; i < numOfCopies; i++)
			book.addCopy();

		da.updateBook(book);
	}

	private void validateAddCopyData(String isbn) throws BookInvalidDataException {
		if (isbn.isEmpty())
			throw new BookInvalidDataException("Isbn Field Cannot be empty !");

	}

	// Hus3/6/20:: Edit Member
	/**
	 * Edit a current library member
	 * 
	 * @param memberID  ID of the member to be edited
	 * @param firstName Member's edited First Name
	 * @param lastName  Member's edited Last Name
	 * @param telephone Member's edited Telephone number
	 * @param email     Member's edited email address
	 * @param street    Member's edited street address
	 * @param state     Member's edited state
	 * @param city      Member's city
	 * @param zip       Member's edited ZipCode
	 * @throws MemberInvalidDataException When the data is not received in the
	 *                                    expected format
	 */
	public void editMember(String memberID, String firstName, String lastName, String telephone, String email,
			String street, String state, String city, String zip)
			throws MemberInvalidDataException, MemberNotFoundException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> mems = da.readMemberMap();
		LibraryMember m = null;
		for (Map.Entry<String, LibraryMember> entry : mems.entrySet())
			if (entry.getKey().equals(memberID))
				m = entry.getValue();
		if (m == null)
			throw new MemberNotFoundException();
		Address address = new Address(street, city, state, zip);
		System.out.println(m);
		m.setAddress(address);
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setTelephone(telephone);
		m.setEmail(email);
		validateMemberData(firstName, lastName, email);

		da.updateMember(m);
	}

	/**
	 * 
	 * @param isbn
	 * @param map
	 * @return
	 */
	private Book searchBookInMap(String isbn, HashMap<String, Book> map) {
		if (map == null)
			return null;
		for (Map.Entry<String, Book> entry : map.entrySet())
			if (entry.getKey().equals(isbn))
				return entry.getValue();
		return null;
	}

	@Override
	public LibraryMember addMember(String firstName, String lastName, String telephone, String email, String street,
			String state, String city, String zip) throws MemberInvalidDataException {

		validateMemberData(firstName, lastName, email);

		Address address = new Address(street, city, state, zip);
		LibraryMember member = new LibraryMember(generateMemberId(), firstName, lastName, telephone, address, email);

		DataAccess da = new DataAccessFacade();
		da.saveNewMember(member);

		return member;
	}

	private String generateMemberId() {
		SystemController systemController = new SystemController();

		int generatedId = systemController.allMemberIds().size() + 1001;
		return "" + generatedId;
	}

	// Hus3/6/20:
	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
			throws InvalidDataException {
		DataAccess da = new DataAccessFacade();
		Book b = new Book(isbn, title, maxCheckoutLength, authors);
		validateBookData(b);
		da.saveNewBook(b);
	}

	// Hus3/6/20:
	private void validateBookData(Book b) throws InvalidDataException {
		if (b.getIsbn().isEmpty() || b.getAuthors().isEmpty() || b.getTitle().isEmpty()
				|| (b.getMaxCheckoutLength() != 21 && b.getMaxCheckoutLength() != 7))
			throw new InvalidDataException();
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> books = da.readBooksMap();
		// if(!books.isEmpty())
		if (searchBookInMap(b.getIsbn(), books) != null)
			throw new InvalidDataException("Book already exists");
	}
}
