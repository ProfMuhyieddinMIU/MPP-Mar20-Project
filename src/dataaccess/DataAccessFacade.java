package dataaccess;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.CheckOutRecord;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public class DataAccessFacade implements DataAccess
{
	public static final String OUTPUT_DIR = "src/dataaccess/storage/";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	private static final long serialVersionUID = 5399827794066637059L;

	enum StorageType
	{
		BOOKS, MEMBERS, USERS, CHECKOUT;
	}

	// implement: other save operations
	public void saveNewMember(LibraryMember member)
	{
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}
	
	//Hus3/6/20::
	/**
	 * Save updated member
	 * @param member updated member object
	 */
	@Override
	public void updateMember(LibraryMember member)
	{
		HashMap<String, LibraryMember> mems = readMemberMap();
		mems.put(member.getMemberId(), member);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	//Hus3/6/20:: fix if first book
	/**
	 * 
	 * @param book
	 */
	public void saveNewBook(Book book)
	{
		HashMap<String, Book> bks = readBooksMap();
		if(bks == null)
			bks = new HashMap<String, Book>();
		bks.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, bks);
	}

	public void updateBook(Book book)
	{
		HashMap<String, Book> mems = readBooksMap();

		mems.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, mems);
	}

	// implement: other save operations
	public void saveNewCheckOutRecord(CheckOutRecord checkOutRecord)
	{
		HashMap<String, CheckOutRecord> CheckOutRecords = readCheckOutRecordsMap();
		String transId = checkOutRecord.getTransId() + "";
		if (CheckOutRecords == null)
		{
			CheckOutRecords = new HashMap<String, CheckOutRecord>();
		}
		CheckOutRecords.put(transId, checkOutRecord);
		saveToStorage(StorageType.CHECKOUT, CheckOutRecords);
	}

	public void updateCheckOutRecord(CheckOutRecord checkOutRecord)
	{
		HashMap<String, CheckOutRecord> recs = readCheckOutRecordsMap() ;

		recs.put(checkOutRecord.getTransId() + "" , checkOutRecord);
		saveToStorage(StorageType.CHECKOUT , recs);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap()
	{
		// Returns a Map with name/value pairs being
		// isbn -> Book
		Object o = readFromStorage(StorageType.BOOKS);
		if(o == null)
			return new HashMap<String, Book>();
		return (HashMap<String, Book>)o ;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckOutRecord> readCheckOutRecordsMap()
	{
		// Returns a Map with name/value pairs being
		// isbn -> Book
		Object o = readFromStorage(StorageType.CHECKOUT);
		if(o == null)
			return new HashMap<String, CheckOutRecord>();
		return (HashMap<String, CheckOutRecord>)o ;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap()
	{
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		Object o = readFromStorage(StorageType.MEMBERS);
		if(o == null)
			return new HashMap<String, LibraryMember>();
		return (HashMap<String, LibraryMember>)o ;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap()
	{
		// Returns a Map with name/value pairs being
		// userId -> User
		Object o = readFromStorage(StorageType.USERS);
		if(o == null)
			return new HashMap<String, User>();
		return (HashMap<String, User>)o ;
	}

	///// load methods - these place test data into the storage area
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

	static void loadCheckOutRecordMap(List<CheckOutRecord> checkOutRecordList)
	{
		HashMap<String, CheckOutRecord> checkOutRecords = new HashMap<String, CheckOutRecord>();
		checkOutRecordList
				.forEach(checkOutRecord -> checkOutRecords.put(checkOutRecord.getTransId() + "", checkOutRecord));
		saveToStorage(StorageType.USERS, checkOutRecords);
	}

	static void loadMemberMap(List<LibraryMember> memberList)
	{
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void saveToStorage(StorageType type, Object ob)
	{
		ObjectOutputStream out = null;
		try
		{
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}

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
		catch (IOException e)
		{
			//e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{ 
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (Exception e)
				{
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable
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
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
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
	}
}
