package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable
{
	private String memberId;
	private static final long serialVersionUID = -2226197306790714013L;
	//Hus3/5/20::
	private List<Notification> notifications;
	private List<Notification> urgentNotifications;
	
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
		super(fname, lname, tel, add, em);
		this.memberId = memberId;
		//Hus3/5/20::
		notifications = new ArrayList<Notification>();
		urgentNotifications  = new ArrayList<Notification>();
	}
	
	public String getMemberId() 
	{
		return memberId;
	}
	
	/**
	 * Sends A notification to the library member
	 * @param n The notification body
	 * @param u Whether it is urgent
	 */
	public void sendNotification(String n, boolean u)
	{
		if(u)
			urgentNotifications.add(new Notification(n, u));
		else
			notifications.add(new Notification(n, u));
	}
	
	/**
	 * Clears all the current notifications for the member
	 */
	public void clearNotifications()
	{
		notifications.clear();
	}
	
	private String getNotifications()
	{
		String s = "Urgent::\n";
		int cnt = 0;
		for(Notification u : urgentNotifications)
			s += ++cnt + ") " + u.getMessage() + "\n";
		cnt = 0;
		s += "Other:\n";
		for(Notification n : notifications)
			s += ++cnt + ") " + n.getMessage() + "\n";
		return s;
	}

	@Override
	public String toString() 
	{
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}
}
