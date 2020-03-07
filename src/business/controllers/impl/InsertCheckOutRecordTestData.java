package business.controllers.impl;

import java.time.LocalDateTime;

import business.customExceptions.LibrarySystemException;

public class InsertCheckOutRecordTestData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibrarianController l = new LibrarianController();
		
		System.out.println("test getAllBooks ==> " + l.getAllBooks()); 
		
		System.out.println("test getAllMembers ==> " + l.getAllMembers());
		
		LocalDateTime currentDate = LocalDateTime.now().minusDays(10); 
		try {
			System.out.println("test checkOutBook  ==> " + l.checkOutBook ("1003" , "33-12345" ,currentDate) );
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("test getAllCheckOutRecords  ==> " + l. getAllCheckOutRecords()); 
	}

}
