package business.controllers.interfaces;
//created by Mohamed Elarian

import java.time.LocalDateTime;
import java.util.List;

import business.Author;
import business.Book;
import business.CheckOutRecord;
import business.LibraryMember;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.LibrarySystemException;
import business.customExceptions.MemberNotFoundException;

public interface LibrarianInterface {
 	
	public List<Book> getAllBooks();	
	public Book getBookByIsbn(String isbn);
	public List<Book> getBooskByName(String title);
	public List<Book> getBooskByAuthor(Author author);
	
	public List<LibraryMember> getAllMembers();
	public LibraryMember getMemberById(String memberId);
	public  List<LibraryMember> getMemberByFName(String  fname);
	public  List<LibraryMember> getMemberByLName(String  lname);
	public List<CheckOutRecord> getAllCheckOutRecords();
	public boolean checkOutBook(String memberId, String isbn ,LocalDateTime checkOutDate ) throws MemberNotFoundException, BookNotFoundException , LibrarySystemException ;
	public boolean checkOutBook(String memberId,  String isbn) throws MemberNotFoundException , BookNotFoundException  , LibrarySystemException; 
	public List<Book> getBooksFromAllCheckOutRecordsByMemberId(String memberID);
	public List<LibraryMember> getMembersFromAllCheckOutRecordsByIsbn(String isbn);
	
	public boolean bookReturn(String transId ,String memberId, String isbn );
}
