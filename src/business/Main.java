package business;

import java.util.*;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Main 
{

	private static int currentDay = 0;
	
	/**
	 * Moves the system to the next day
	 */
	public static void incrementDay()
	{
		currentDay++;
	}
	
	/**
	 * Go forward a specific number of days
	 * @param d Number of days to go forward
	 */
	public static void addDays(int d)
	{
		currentDay = d;
	}
	
	/**
	 * 
	 * @return A list of all IDs of  LibraryMembers that have an overdue book
	 */
	public static List<String> allHavingOverdueBook() 
	{
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		for(LibraryMember m: members)
		{
			
		}
		List<LibraryMember> od =new ArrayList<>();
		//implement
		throw new NotImplementedException();
		
	}
	
	/*Hus3/5/20:: NotUseful
	public static void main(String[] args) 
	{
		System.out.println(allWhoseZipContains3());
		System.out.println(allHavingOverdueBook());
		System.out.println(allHavingMultipleAuthors());
	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() 
	{
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return null;
	}
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() 
	{
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return null;
	}
	*/
}
