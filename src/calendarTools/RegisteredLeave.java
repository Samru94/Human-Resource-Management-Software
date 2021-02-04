package calendarTools;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javafx.scene.control.Tooltip;

public class RegisteredLeave {

	int markedDays[][] = new int [2][367];
	
	public RegisteredLeave() {
		
		
	for(int i = 0 ; i< 367 ; i++) {
			markedDays[0][i]=0;
			markedDays[1][i]=0;
		}
	
		markDays();
	
	}
	
	public void printMarkedDays() {
		
		for(int i = 0 ; i< 367 ; i++) {
			System.out.println("Tag: " + (i) + " " + markedDays[0][i] + " ");
			
		}
		
	}
	
	public void markDays() {
		 VacationStorer vac = new VacationStorer();
         
         
        List<LocalDate> StartOfVacation = vac.getStartOfVacation();
     	List<LocalDate> EndOfVacation = vac.getEndOfVacation();
     	LocalDate referenceYear = LocalDate.now();
     	
     	for(int i=0; i < EndOfVacation.size(); i++) {
     		//falls der urlaub über die Jahre geht
			if(StartOfVacation.get(i).getYear() != EndOfVacation.get(i).getYear()) {
				
				for(int j = StartOfVacation.get(i).getDayOfYear() ; j <= 366; j++) {
					markedDays[0][j]= markedDays[0][j]+1;
				}
				

				for(int j = 1 ; j <= EndOfVacation.get(i).getDayOfYear(); j++) {
					markedDays[1][j]= markedDays[1][j]+1;
				}
				//urlaub ist im gleichen jahr
			}else {
					if(StartOfVacation.get(i).getYear()== referenceYear.getYear() ) {
						for(int j = StartOfVacation.get(i).getDayOfYear() ; j <= EndOfVacation.get(i).getDayOfYear(); j++) {
							markedDays[0][j]= markedDays[0][j]+1;
						}
					}else {
							for(int j = StartOfVacation.get(i).getDayOfYear() ; j <= EndOfVacation.get(i).getDayOfYear(); j++) {
								markedDays[1][j]= markedDays[1][j]+1;
							}
						}
						
					
				
				
			}
			
			
			
	}
		
}
	
	public int[][] getMarkedDays() {
 		return markedDays;
 	}
	
	
	
}

