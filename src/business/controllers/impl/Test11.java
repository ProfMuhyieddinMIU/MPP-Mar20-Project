package business.controllers.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LocalDateTime currentDate = LocalDateTime.now().minusDays(1);
		// int days = Days.daysBetween(currentDate, LocalDateTime.now() ).getDays();
		 Duration duration = Duration.between(LocalDateTime.now(), currentDate);
		 long diff = Math.abs(duration.toDays() );
		    System.out.println("===== currentDate =>" + diff);
		    System.out.println("===== currentDate =>" + currentDate.plusDays(7) );
	}

}
