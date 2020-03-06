package business;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}
	
	@Override
	public String toString() 
	{  
		return "transId: " + transId + ", memberId: " + memberId + ", isbn: " + isbn 
				+ ", copyNum: " + copyNum + ", LocalDateTime: " + checkOutDate ;
	}
}
