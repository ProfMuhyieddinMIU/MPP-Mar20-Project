package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CheckoutEntry implements Serializable {

	private static final long serialVersionUID = 1765529867202819272L;

	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookcopy;
	private String copyNumber;
	private String ISBN;
	private String isAvailable;

	public CheckoutEntry(BookCopy bk, LocalDate checkoutD, LocalDate dueD) {
		this.bookcopy = bk;
		this.checkoutDate = checkoutD;
		this.dueDate = dueD;
		this.ISBN = bookcopy.getBook().getIsbn();
		this.copyNumber = String.valueOf(bookcopy.getCopyNum());
		this.isAvailable = Boolean.toString(bookcopy.isAvailable());
	}

	public String getNumberOfCopy() {
		return copyNumber;
	}

	public void setDueDate(String nc) {
		this.copyNumber = nc;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getISBN() {
		return bookcopy.getBook().getIsbn();
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BookCopy getBookcopy() {
		return bookcopy;
	}

	public void setBookcopy(BookCopy bookcopy) {
		this.ISBN = bookcopy.getBook().getIsbn();
		this.copyNumber = String.valueOf(bookcopy.getCopyNum());
		this.isAvailable = Boolean.toString(bookcopy.isAvailable());
		this.bookcopy = bookcopy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CheckoutEntry: " + copyNumber + " " + ISBN + " " + isAvailable + " " + copyNumber + " " + '\t'
				+ checkoutDate + '\t' + dueDate + '\t';
	}

	@Override
	public int hashCode() {
		return Objects.hash(ISBN, copyNumber, dueDate);
	}
}
