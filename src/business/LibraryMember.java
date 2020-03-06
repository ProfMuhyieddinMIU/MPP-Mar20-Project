package business;

import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable
{
	private String memberId;
	private static final long serialVersionUID = -2226197306790714013L;
	
	/**
	 * 
	 * @param memberId ID
	 * @param fname First Name
	 * @param lname Last Name
	 * @param tel Telephone Number
	 * @param add Address
	 * @param em Email
	 */
	public LibraryMember(String memberId, String fname, String lname, String tel, Address add, String em) 
	{
		super(fname,lname, tel, add, em);
		this.memberId = memberId;		
	}
	
	public String getMemberId() 
	{
		return memberId;
	}

	@Override
	public String toString() 
	{
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}
}
