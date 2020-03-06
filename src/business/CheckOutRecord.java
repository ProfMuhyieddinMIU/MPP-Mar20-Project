package business;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import business.controllers.impl.LibrarianController;
import business.controllers.interfaces.LibrarianInterface;

public class CheckOutRecord  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long transId ;
	private String memberId ;
	private String isbn ;
	private int copyNum;
	private LocalDateTime checkOutDate ;
	private LocalDateTime bookReturnDate ;
	
	
	public long getTransId() {
		return transId;
	}
	public void setTransId(long transId) {
		this.transId = transId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}
	
	public LocalDate getCheckOutDateAsDate() {
		return checkOutDate.toLocalDate() ;
	}
	
	public String getCheckOutDateAsDateAsString() {
		return ( checkOutDate == null ? "" : checkOutDate.toLocalDate().toString() ) ;
	}
	
	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}
	
	public LocalDateTime getBookReturnDate() {
		return bookReturnDate;
	}
	public void setBookReturnDate(LocalDateTime bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}
	public String getMemberName() {
		LibrarianInterface librarianController = new LibrarianController();
		LibraryMember  member = librarianController.getMemberById(getMemberId()) ;
		return member.getFirstName() + " " +member.getLastName() ;
	}
	
	public String getBookTitle() {
		String bookTitle = "" ;
		 
		try {
			LibrarianInterface librarianController = new LibrarianController();
			Book book = librarianController.getBookByIsbn(getIsbn());
			bookTitle = book.getTitle() ;
		} catch (Exception e) {
			//e.printStackTrace();
            System.out.println("couldn't get book name from isbn");
		}
		return bookTitle    ;
	}
	
	public LocalDate getDueDate() {
		LocalDate dueDate = null ; 
		try {
		LibrarianInterface librarianController = new LibrarianController();
		Book  book = librarianController.getBookByIsbn(getIsbn()) ;
		long checkOutLength = 0 ;
		if (book != null ) {
			checkOutLength = book.getMaxCheckoutLength() ;
		}
		 
		dueDate = getCheckOutDate().plusDays( (checkOutLength == 0 ? 7 :  checkOutLength  ) ).toLocalDate() ;
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("couldn't get dueDate : getCheckOutDate()=" + getCheckOutDate()  + "   dueDate = " + dueDate);
		}
		return   dueDate  ;
	}
	
	public String getDueDateAsString() {
		return ( getDueDate() == null ? "" : getDueDate().toString()  )  ;
	}
	
	
	public boolean isOverDueDate() {
        
		if (getDueDate().isAfter( LocalDateTime.now().toLocalDate().minusDays(1)  )  ) {
			return false ;
		}
         return true ;
	}
	
	@Override
	public String toString() 
	{  
		return "transId: " + transId + ", memberId: " + memberId + ", isbn: " + isbn 
				+ ", copyNum: " + copyNum + ", LocalDateTime: " + checkOutDate 
				+ ", getBookTitle(): " + getBookTitle()
				+ ", getMemberName: " + getMemberName()
				+ ", getDueDateAsString: " + getDueDateAsString()
		+ ", getCheckOutDateAsDateAsString: " + getCheckOutDateAsDateAsString()
		+ ", isOverDueDate(): " + ( isOverDueDate() ? "True"  : "False" );
		
		
		
	}
}
