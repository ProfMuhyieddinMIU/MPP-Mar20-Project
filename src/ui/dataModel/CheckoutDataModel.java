package ui.dataModel;

import java.util.List;

import business.Author;
import business.Book;
import business.CheckOutRecord;

public class CheckoutDataModel {

	private String isbn;
	private String bookTitle;
	private String memberId;
	private String memberName;
	private String checkoutDate;
	private String dueDate;
	private String satus;
	
	public CheckoutDataModel(CheckOutRecord c) {
		
		this.isbn = c.getIsbn();
		this.bookTitle = c.getBookTitle();
		this.memberId = c.getMemberId();
		this.memberName = c.getMemberName();
		this.checkoutDate = c.getCheckOutDateAsDate().toString();
		this.dueDate = c.getDueDateAsString();
		this.satus = c.isOverDueDate()? "Overdue!" : "";
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getSatus() {
		return satus;
	}

	public void setSatus(String satus) {
		this.satus = satus;
	}

}
