package business.controllers.impl;

import java.time.LocalDateTime;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LocalDateTime currentDate = LocalDateTime.now();
		    System.out.println("===== currentDate =>" + currentDate);
		    System.out.println("===== currentDate =>" + currentDate.plusDays(7) );
	}

}
