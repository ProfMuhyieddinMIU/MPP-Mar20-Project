package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

//Hus3/5/20:: see all warnings
public class DataAccessFacade implements DataAccess
{
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	private static final long serialVersionUID = 5399827794066637059L;
	
	/**
	 * Books, Members, or Users
	 *
	 */
	enum StorageType 
	{
		BOOKS, MEMBERS, USERS;
	}

	//implement: other save operations
	
	public void saveNewMember(LibraryMember member) 
	{
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}
	
	/**
	 * @return Map with name/value pairs being: isbn -> Book
	 */
	public  HashMap<String,Book> readBooksMap() 
	{
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	/**
	 * @returnMap Map with name/value pairs being: memberId -> LibraryMember
	 */
	public HashMap<String, LibraryMember> readMemberMap() 
	{
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}
	
	/**
	 * @return Map with name/value pairs being: userId -> User
	 */
	public HashMap<String, User> readUserMap() 
	{
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	/*
	 * Hus3/5/20:: Pause Rubbish loads (not real data
	 * 
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
	static void loadBookMap(List<Book> bookList) 
	{
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	
	static void loadUserMap(List<User> userList) 
	{
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) 
	{
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}*/
	
	/**
	 * 
	 * @param type Type of storage (Book, Member or User)
	 * @param ob
	 */
	static void saveToStorage(StorageType type, Object ob) 
	{
		ObjectOutputStream out = null;
		try 
		{
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if(out != null) 
			{
				try 
				{
					out.close();
				} 
				catch(Exception e) 
				{//Hus3/5/20::was catching without displaying the error or solving it
					System.err.println("Error Closing Output Stream");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	static Object readFromStorage(StorageType type) 
	{
		ObjectInputStream in = null;
		Object retVal = null;
		try 
		{
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if(in != null) 
			{
				try 
				{
					in.close();
				} 
				catch(Exception e) 
				{}
			}
		}
		return retVal;
	}
	
	/*Hus3/5/20::StopUnknown
	final static class Pair<S,T> implements Serializable
	{
		S first;
		T second;
		Pair(S s, T t) 
		{
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) 
		{
			if(ob == null) 
				return false;
			if(this == ob) 
				return true;
			if(ob.getClass() != getClass()) 
				return false;

			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() 
		{
			return first.hashCode() + 5 * second.hashCode();
		}
		
		@Override
		public String toString() 
		{
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
	}*/
}
