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

public class LibrarianController implements LibraryInterface {

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
		
		System.out.println("test getAllMembers ==> " + l.getAllMembers());
		 
		System.out.println("test getMemberById(String memberId) ==> " + l.getMemberById("1004")); 
		
		System.out.println("test getMemberById(String memberId) ==> " + l.getMemberByFName("Sarah")); 
		
		System.out.println("test getMemberById(String memberId) ==> " + l.getMemberByLName("Eagleton")); 
		 
		
	}
	
	@Override
	public Book getBookByIsbn(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> books =new  HashMap<>();
		books.putAll(da.readBooksMap());
		Book bookById = books.get(isbn);
		return bookById ;
	}

	public List<Book> getBooksByAttribute(String AttributeName ,Object AttributeValue) {
		DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		books.addAll(da.readBooksMap().values());
		
		List<Book> booksResult = new ArrayList<>();
		for (Book book : books) {
			if (AttributeName.equalsIgnoreCase("Title")) {
					if ( AttributeValue.toString().equalsIgnoreCase( book.getTitle() ) ) {
						booksResult.add(book);
					}
			}
			else if (AttributeName.equalsIgnoreCase("Author")) {
				
				for (Author author : book.getAuthors() ) {
					/*if ( AttributeValue.equalsIgnoreCase( author.getFirstName()  ) 
						|| 	 AttributeValue.equalsIgnoreCase( author.getLastName() )
						|| AttributeValue.equalsIgnoreCase( author.getFirstName() + " " +author.getLastName() )	) {
						booksResult.add(book);
					} */
					
					if (  AttributeValue.equals(author) ) {
							booksResult.add(book);
						}
				}
				
		    }
			
		} 
		return booksResult;
	}
	
	@Override
	public List<Book> getBooskByName(String title) {
		/*DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		books.addAll(da.readBooksMap().values());
		
		List<Book> booksResult = new ArrayList<>();
		for (Book book : books) {
			if ( title.equalsIgnoreCase( book.getTitle() ) ) {
				booksResult.add(book);
			}
		} 
		return booksResult;*/
		return getBooksByAttribute("Title" , title) ;
	}

	@Override
	public List<Book> getBooskByAuthor(Author author) {

		return getBooksByAttribute("Author" , author) ;
	}

	
	public List<LibraryMember> getMembersByAttribute(String AttributeName ,Object AttributeValue) {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> libraryMembers = new ArrayList<>();
		libraryMembers.addAll(da.readMemberMap().values());
		
		List<LibraryMember> membersResult = new ArrayList<>();
		for (LibraryMember libraryMember : libraryMembers) {
			if (AttributeName.equalsIgnoreCase("FirstName")) {
					if ( AttributeValue.toString().equalsIgnoreCase( libraryMember.getFirstName() ) ) {
						membersResult.add(libraryMember);
					}
			}
			else if (AttributeName.equalsIgnoreCase("LastName")) {
				if ( AttributeValue.toString().equalsIgnoreCase( libraryMember.getLastName() ) ) {
					membersResult.add(libraryMember);
				}
			}
			else if (AttributeName.equalsIgnoreCase("MemberId")) {
					if ( AttributeValue.toString().equalsIgnoreCase( libraryMember.getMemberId() ) ) {
						membersResult.add(libraryMember);
					}	
	    	}
			 
		}
		return membersResult;
	}
	
	@Override
	public List<LibraryMember> getAllMembers() {
		
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> members = new ArrayList<>();
		members.addAll(da.readMemberMap().values());
		 
		return members;		
	 
	}

	@Override
	public LibraryMember getMemberById(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,LibraryMember> libraryMembers =new  HashMap<>();
		libraryMembers.putAll(da.readMemberMap());
		LibraryMember memberById = libraryMembers.get(memberId);
		return memberById ; 
	}

	@Override
	public  List<LibraryMember> getMemberByFName(String fname) {
		return  getMembersByAttribute("FirstName" ,fname ) ;
	}

	@Override
	public  List<LibraryMember> getMemberByLName(String lname) {
		return  getMembersByAttribute("LastName" ,lname ) ;
	}

	@Override
	public boolean CheckOutBook(String memberId, int copyId) {
		// TODO Auto-generated method stub
		return false;
	}

	 

}
