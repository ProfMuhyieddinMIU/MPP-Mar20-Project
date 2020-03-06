package ui.dataModel;

import java.util.List;

import business.Author;
import business.Book;

public class BookDataModel {

	private String copies;
	private String authors;
	private String isbn;
	private String title;
	private String maxCheckoutLength;
	
	
	public BookDataModel(Book b) {
		this.copies = b.getNumCopies()+"";
		this.authors = this.getAuthorsName(b.getAuthors());
		this.isbn = b.getIsbn();
		this.title = b.getTitle();
		this.maxCheckoutLength = b.getMaxCheckoutLength()+"";
	}
	
	public String getCopies() {
		return copies;
	}
	public void setCopies(String copies) {
		this.copies = copies;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	public void setMaxCheckoutLength(String maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
	
	public String getAuthorsName(List<Author> authors) {
		if(authors.size() == 0) 
			return "";
		
		if(authors.size() ==1)
			return authors.get(0).getFirstName()+" "+authors.get(0).getLastName();
		
		String authorsName = "";
		for(Author a: authors) {
			authorsName += ", "+ a.getFirstName(); 
		}
		
		return authorsName.substring(2);
	}
	
}
