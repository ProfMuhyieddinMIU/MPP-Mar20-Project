package business.controllers.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// LocalDateTime currentDate = LocalDateTime.now().plusDays(1/24) ;// .minusDays(1);
		// LocalDateTime currentDate = LocalDateTime.now().minusDays(5);
		 LocalDateTime currentDate = LocalDateTime.now()  ; 
	//	LocalDateTime currentDate = LocalDateTime.now().plusHours(5);
		// int days = Days.daysBetween(currentDate, LocalDateTime.now() ).getDays();
		 Duration duration = Duration.between( currentDate , LocalDateTime.now()  );
		 long diff = Math.abs(duration.toDays() );
		    System.out.println("===== diff =>" + diff + "   === " + duration.toDays());
		    System.out.println("===== currentDate =>" + currentDate  );
		    
		    if (currentDate.toLocalDate().isAfter( LocalDateTime.now().toLocalDate().minusDays(1) )  ) {
		    	System.out.println("===== after"   );
			}else {
				System.out.println("===== bbefore"   );
			}
		    
	}

}
