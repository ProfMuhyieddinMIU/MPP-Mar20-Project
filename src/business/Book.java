package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
final public class Book implements Serializable 
{
	
	private static final long serialVersionUID = 6110690276685962829L;
	private List<BookCopy> copies;//Hus3/5/20
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;
	
	/**
	 * 
	 * @param isbn The ID of the book
	 * @param title The Title of the book
	 * @param maxCheckoutLength The max number of days the book can be checked out each time
	 * @param authors A list of the book authors
	 */
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) 
	{
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		//Hus3/5/20:: first copy of book, id=1,available
		copies = new ArrayList<BookCopy>();
		copies.add(new BookCopy(this, 1, true));
	}
	
	/* Hus3/5/20:: 
	public void updateCopies(BookCopy copy) 
	{
		for(int i = 0; i < copies.length; ++i) 
		{
			BookCopy c = copies[i];
			if(c.equals(copy))
				copies[i] = copy;
		}
	}*/

	/**
	 * 
	 * @return All the copy IDs for the current book
	 */
	public List<Integer> getCopyNums()
	{
		List<Integer> retVal = new ArrayList<>();
		for(BookCopy c : copies)
			retVal.add(c.getCopyNum());
		return retVal;
		
	}
	
	/**
	 * Add a new copy to this book having incremental ID
	 */
	public void addCopy() 
	{//Hus3/5/20:: ArraytoList copies
		copies.add(new BookCopy(this, copies.size() +1, true));
	}
	
	
	@Override
	public boolean equals(Object ob) 
	{
		if(ob == null || ob.getClass() != getClass()) 
			return false;
		Book b = (Book)ob;
		return b.isbn.equals(isbn);
	}
	
	/**
	 * 
	 * @return Whether there is a copy that is available for loan
	 */
	public boolean isAvailable() 
	{//Hus3/5/20 arraytolist copies
		if(copies == null)
			return false;
		for(BookCopy c : copies)
			if(c.isAvailable())
				return true;
		return false;
	}
	
	@Override
	public String toString() 
	{
		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable() +
				", Total number of Copies: " + getNumCopies();
		
		
	}
	
	/**
	 * 
	 * @return The current total number of copies owned by the library
	 */
	public int getNumCopies() 
	{
		return copies.size();
	}
	
	/**
	 * 
	 * @return The title of the book
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * 
	 * @return A list of all available copies
	 */
	public List<BookCopy> getCopies() 
	{
		return copies;
	}
	
	/**
	 * 
	 * @return A list of the book Authors
	 */
	public List<Author> getAuthors() 
	{
		return authors;
	}
	
	/**
	 * 
	 * @return The book ISBN
	 */
	public String getIsbn() 
	{
		return isbn;
	}
	
	/**
	 * 
	 * @return The first available copy, or null if none is available
	 */
	public BookCopy getNextAvailableCopy() 
	{	
		for(BookCopy c : copies)
			if(c.isAvailable())
				return c;
		return null;
	}
	
	/** 
	 *  get first copy of Book that checked out before to be used when book return 
	 *  added by Mohamed Elarian
	 * @return
	 */
	public BookCopy getNextUnAvailableCopy() 
	{	
		for(BookCopy c : copies)
			if( ! c.isAvailable())
				return c;
		return null;
	}
	
	/**
	 * Get a copy by its ID
	 * @param copyNum The copy ID
	 * @return Reference to this copy
	 */
	public BookCopy getCopy(int copyNum) 
	{
		for(BookCopy c : copies)
			if(copyNum == c.getCopyNum())
				return c;
		return null;
	}
	
	/**
	 * 
	 * @return The maximum number of days this book can be checkedOut each time
	 */
	public int getMaxCheckoutLength() 
	{
		return maxCheckoutLength;
	}
	
	public int getNumberOfAvailable() {
		int count = 0 ;
		for(BookCopy c : copies)
			if( c.isAvailable())
				count++;
		return count;
	}
}
