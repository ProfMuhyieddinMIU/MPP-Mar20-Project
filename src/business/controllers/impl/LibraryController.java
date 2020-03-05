package business.controllers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.LibraryMember;
import business.controllers.interfaces.LibraryInterface;
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
		// for test LibraryController only 
		LibraryController l = new LibraryController();
		System.out.println("test getAllBooks ==> " + l.getAllBooks());
		
		System.out.println("test getBookByIsbn ==> " + l.getBookByIsbn("28-12331").toString());
		
		System.out.println("test getBooskByName ==> " + l.getBooskByName( "test"));
		
	}
	
	@Override
	public Book getBookByIsbn(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> books =new  HashMap<>();
		books.putAll(da.readBooksMap());
		Book bookById = books.get(isbn);
		return bookById ;
	}

	@Override
	public List<Book> getBooskByName(String title) {
		DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		books.addAll(da.readBooksMap().values());
		
		List<Book> booksResult = new ArrayList<>();
		for (Book book : books) {
			if ( title.equalsIgnoreCase( book.getTitle() ) ) {
				booksResult.add(book);
			}
		} 
		return booksResult;
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
