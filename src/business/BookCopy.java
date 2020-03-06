package business;

import java.io.Serializable;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable 
{
	private static final long serialVersionUID = -63976228084869815L;
	private Book book;
	private int copyNum;
	private boolean isAvailable;
	
	/**
	 * 
	 * @param book Reference of the book
	 * @param copyNum The ID of this copy
	 * @param isAvailable Available for loan
	 */
	BookCopy(Book book, int copyNum, boolean isAvailable) 
	{
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	/**
	 * 
	 * @param book Reference of the book
	 * @param copyNum The ID of this copy
	 */
	BookCopy(Book book, int copyNum) 
	{
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = true; //HusElg 3/5/20:: available by default
	}
	
	/**
	 * 
	 * @return Whether this copy is available for loan
	 */
	public boolean isAvailable() 
	{
		return isAvailable;
	}

	/**
	 * 
	 * @return The ID of this copy
	 */
	public int getCopyNum() 
	{
		return copyNum;
		
	}
	
	/**
	 * 
	 * @return Reference to the book
	 */
	public Book getBook() 
	{
		return book;
		
	}
	
	/**
	 * To be used for CheckIn/CheckOut
	 */
	public void changeAvailability() 
	{
		isAvailable = !isAvailable;
	}
	
	@Override
	public boolean equals(Object ob) 
	{
		if(ob == null) 
			return false;
		if(!(ob instanceof BookCopy)) 
			return false;
		BookCopy copy = (BookCopy)ob;
		return copy.book.getIsbn().equals(book.getIsbn()) && copy.copyNum == copyNum;
	}
	
}
