package business.controllers.interfaces;
//created by Mohamed Elarian

import java.util.List;

import business.Author;
import business.Book;
import business.LibraryMember;

public interface LibrarianInterface {
 	
	public List<Book> getAllBooks();	
	public Book getBookByIsbn(String isbn);
	public List<Book> getBooskByName(String title);
	public List<Book> getBooskByAuthor(Author author);
	
	public List<LibraryMember> getAllMembers();
	public LibraryMember getMemberById(String memberId);
	public  List<LibraryMember> getMemberByFName(String  fname);
	public  List<LibraryMember> getMemberByLName(String  lname);

	public boolean CheckOutBook (String memberId , int copyId) ; 
	
}
