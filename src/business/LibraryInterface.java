package business;
//created by Mohamed Elarian

import java.util.List;

public interface LibraryInterface {
 	
	public List<Book> getAllBooks();	
	public Book getBookByIsbn(String isbn);
	public List<Book> getBooskByName(String title);
	public List<Book> getBooskByAuthor(Author author);
	
	public List<LibraryMember> getAllMembers();
	public LibraryMember getMemberById(String memberId);
	public LibraryMember getMemberByFName(String  fname);
	public LibraryMember getMemberByLName(String  lname);

	public boolean CheckOutBook (String memberId , int copyId) ; 
	
}
