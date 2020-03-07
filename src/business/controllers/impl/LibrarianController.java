package business.controllers.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckOutRecord;
import business.LibraryMember;
import business.controllers.interfaces.LibrarianInterface;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.LibrarySystemException;
import business.customExceptions.MemberNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class LibrarianController implements LibrarianInterface  {

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
		LibrarianController l = new LibrarianController();
		System.out.println("test getAllBooks ==> " + l.getAllBooks());
		
		System.out.println("test getBookByIsbn ==> " + l.getBookByIsbn("28-12331").toString());
		
		System.out.println("test getBooskByName ==> " + l.getBooskByName( "test"));
		
		System.out.println("test getAllMembers ==> " + l.getAllMembers());
		 
		System.out.println("test getMemberById(String memberId) ==> " + l.getMemberById("1004")); 
		 
		System.out.println("test getMemberByFName ==> " + l.getMemberByFName("Sarah")); 
		
		System.out.println("test getMemberByLName  ==> " + l.getMemberByLName("Eagleton")); 
		
		System.out.println("test getMembersFromAllCheckOutRecordsByIsbn(isbn)  ==> " + l.getMembersFromAllCheckOutRecordsByIsbn( "28-12331")); 
		
		System.out.println("test getBooksFromAllCheckOutRecordsByMemberId(String memberID)  ==> " + l.getBooksFromAllCheckOutRecordsByMemberId("1004")); 
		
	//	System.out.println("test bookReturn  ==> " + ( l.bookReturn( "11" ,  "",  "" ) ? "True" : "False"  ) ; 
		System.out.println("test bookReturn  ==> " +  l.bookReturn( "11" ,  "",  "" )    ) ; 
		
		try {
			System.out.println("test checkOutBook  ==> " + l.checkOutBook ("1004" , "28-12331" ) );
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test getAllCheckOutRecords  ==> " + l. getAllCheckOutRecords()); 
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
	public List<CheckOutRecord> getAllCheckOutRecords() { 
		DataAccess da = new DataAccessFacade();
		List<CheckOutRecord> checkOutRecords = new ArrayList<>();
		HashMap<String, CheckOutRecord> currentCheckOutRecords  = da.readCheckOutRecordsMap() ;
		if (currentCheckOutRecords != null ) {
			checkOutRecords.addAll(   da.readCheckOutRecordsMap().values());
		}
		
		 
		
		return checkOutRecords;
	}
	

	@Override
	public List<Book> getBooksFromAllCheckOutRecordsByMemberId(String memberID) { 
		DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		for (CheckOutRecord checkOutRecord : da.readCheckOutRecordsMap().values()  ) {
			if (checkOutRecord.getMemberId().equalsIgnoreCase(memberID)) {
				Book book = getBookByIsbn(checkOutRecord.getIsbn()) ;
				books.add(book) ;
			}
		}
		 
		 
		
		return books;
	}
	
	
	@Override
	public List<LibraryMember> getMembersFromAllCheckOutRecordsByIsbn(String isbn) { 
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> members = new ArrayList<>();
		for (CheckOutRecord checkOutRecord : da.readCheckOutRecordsMap().values()  ) {
			if (checkOutRecord.getIsbn() != null 
				&& checkOutRecord.getIsbn().equalsIgnoreCase(isbn)) {
				LibraryMember member = getMemberById(checkOutRecord.getMemberId()) ;
				members.add(member) ;
			}
		}
		 
		 
		
		return members;
	}
	
	/**
	 * 
	 * @param memberId
	 * @param isbn
	 * @param copyNum
	 * @return
	 */
	private CheckOutRecord saveCheckOutBook(String memberId, String isbn , int copyNum ,LocalDateTime checkOutDate  ) {
		
		/* CheckOutRecord checkOutRecord = new CheckOutRecord();
		checkOutRecord.setMemberId(memberId);
		checkOutRecord.setIsbn(isbn);
		checkOutRecord.setCopyNum(copyNum);
		
		checkOutRecord.setCheckOutDate(checkOutDate);    
		 */   
		DataAccess da = new DataAccessFacade(); 
		long dummyTransId = -1 ;
		if (da.readCheckOutRecordsMap() == null ) {
			dummyTransId = 1 ;
		}else {
			dummyTransId = da.readCheckOutRecordsMap().size() + 1 ;
		}
		
		// checkOutRecord.setTransId( dummyTransId );
		CheckOutRecord checkOutRecord = new CheckOutRecord (dummyTransId , memberId , isbn ,  copyNum ,  checkOutDate ) ;
		da.saveNewCheckOutRecord(checkOutRecord);
		return checkOutRecord;
	}
	
	
	 
	@Override
	public boolean checkOutBook(String memberId, String isbn ,LocalDateTime checkOutDate ) throws MemberNotFoundException, BookNotFoundException , LibrarySystemException {
		// validateCheckOutData( memberId,    isbn) ;
		LibraryMember member = getMemberById( memberId);
		if (member == null || member.getMemberId() == null ) {
			throw new MemberNotFoundException();
		}
		
		Book book = getBookByIsbn(isbn) ;
		if (book == null || book.getTitle() == null ) {
			throw new BookNotFoundException();
		} 
		
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckOutRecord> checkOutRecords  = da.readCheckOutRecordsMap() ;
		if (checkOutRecords != null && checkOutRecords.size() > 0 ) {
			
		
		for (CheckOutRecord checkOutRecord :checkOutRecords.values()  ) {
			if (checkOutRecord.getIsbn() != null && checkOutRecord.getMemberId() != null 
				&& checkOutRecord.getIsbn().equalsIgnoreCase(isbn)
				&& checkOutRecord.getMemberId().equalsIgnoreCase(memberId)
					) {
				  throw new LibrarySystemException("This member already has a copy from this book amd can't get another copy") ;
			}
		}
		}
		int copyNum = -1 ;
		if(book.isAvailable())
			copyNum = book.getNextAvailableCopy().getCopyNum() ;
	 
		
	/*	for (BookCopy  bookCopy : book.getCopies()) {
			if (bookCopy.isAvailable()) {
				copyNum = bookCopy.getCopyNum();
				break ;
			}
		}  */
		if (copyNum < 0) {
            throw new LibrarySystemException("The copy that checked out is unavailable") ;
		}
		
		//Hus3/6/20:: Next copy is not available
		book.getNextAvailableCopy().changeAvailability();
		updateBookMap(book);		
		LocalDateTime currentDate = LocalDateTime.now(); 
		saveCheckOutBook( memberId, isbn , copyNum , currentDate );
		return true;
	}

	
	 
		@Override
		public boolean checkOutBook(String memberId, String isbn) throws MemberNotFoundException, BookNotFoundException , LibrarySystemException {
			 
			LocalDateTime currentDate = LocalDateTime.now(); 

			return checkOutBook( memberId,  isbn , currentDate ) ;
		}

		
	private void updateBookMap(Book book) {
		DataAccess da = new DataAccessFacade();
		da.updateBook(book);
		
	}
	
	
	private void setCheckOutRecordReturnDate (CheckOutRecord checkOutRecord) {
		 checkOutRecord.setBookReturnDate(LocalDateTime.now());
		 DataAccess da = new DataAccessFacade();
			da.updateCheckOutRecord(checkOutRecord);
		 
	}
	
	@Override
	public boolean bookReturn(String transId , String memberId, String isbn ) {
		DataAccess da = new DataAccessFacade();
		 
		for (CheckOutRecord checkOutRecord : da.readCheckOutRecordsMap().values()  ) {
			if (transId != null && checkOutRecord.getTransId() == Long.parseLong(transId) ) {
				    setCheckOutRecordReturnDate ( checkOutRecord) ;
				    Book book = getBookByIsbn(checkOutRecord.getIsbn());
				    BookCopy bookCopy = book.getNextUnAvailableCopy() ; 
				    if (bookCopy != null) {
				    	bookCopy.changeAvailability();
				    	updateBookMap(book);
					}
					
				    return true ;
				}
			
			if (checkOutRecord.getIsbn() != null 
					&& checkOutRecord.getIsbn().equalsIgnoreCase(isbn)
					&& checkOutRecord.getMemberId().equalsIgnoreCase(memberId)
					&& checkOutRecord.getBookReturnDate() == null ) {
					setCheckOutRecordReturnDate ( checkOutRecord) ;
					Book book = getBookByIsbn(checkOutRecord.getIsbn());
					   BookCopy bookCopy = book.getNextUnAvailableCopy() ; 
					    if (bookCopy != null) {
					    	bookCopy.changeAvailability();
					    	updateBookMap(book);
						}
				    return true ;
				}
 
		}
		return false ; // didn't find checkOutRecord to update
	}
	
	
	
}
