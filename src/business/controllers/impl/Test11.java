package business.controllers.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LocalDateTime currentDate = LocalDateTime.now().plusDays(5) ;// .minusDays(1);
		// LocalDateTime currentDate = LocalDateTime.now().minusDays(5);
		// int days = Days.daysBetween(currentDate, LocalDateTime.now() ).getDays();
		 Duration duration = Duration.between( currentDate , LocalDateTime.now()  );
		 long diff = Math.abs(duration.toDays() );
		    System.out.println("===== currentDate =>" + diff + "   === " + duration.toDays());
		    System.out.println("===== currentDate =>" + currentDate.plusDays(7) );
	}

}
