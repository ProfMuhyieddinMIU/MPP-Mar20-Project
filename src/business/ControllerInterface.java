package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addMember(LibraryMember member);
	public LibraryMember searchMember(String memberID);
	public void editMember(LibraryMember member);
	public Book searchBook(String ISBN);
	public BookCopy searchBookCopy(String ISBN);
	public void makeCheckout(LibraryMember member, Book book);
}
