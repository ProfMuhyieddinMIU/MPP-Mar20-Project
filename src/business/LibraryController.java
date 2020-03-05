package business;

import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class LibraryController implements LibraryInterface {

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		
		DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		books.addAll(da.readBooksMap().values());
		 
		
		return books;
	}
 
	public static void main(String[] args) {
		LibraryController l = new LibraryController();
		System.out.println(l.getAllBooks());
	}
	
	@Override
	public Book getBookByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooskByName(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooskByAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LibraryMember> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibraryMember getMemberById(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibraryMember getMemberByFName(String fname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibraryMember getMemberByLName(String lname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CheckOutBook(String memberId, int copyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
