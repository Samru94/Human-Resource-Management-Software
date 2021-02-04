package calendarTools;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.progex.main.Main;
import de.progex.model.Leave;

public class VacationStorer {
	
	List<LocalDate> StartOfVacation = new ArrayList<LocalDate>();
	List<LocalDate> EndOfVacation = new ArrayList<LocalDate>();
	Leave[] leaveArr = Main.db.getAllLeaveByDate(getStartDate(), getEndDate());
	
	public VacationStorer() {
		
	for(int i = 0; i< leaveArr.length; i++) {
		if(leaveArr[i] != null) {
		StartOfVacation.add(leaveArr[i].getStartDate().toLocalDate());
		EndOfVacation.add(leaveArr[i].getEndDate().toLocalDate());
		}
	}
	
	
	
	
	}
	
	public List<LocalDate> getStartOfVacation(){
		return StartOfVacation;
	}
	
	
	public List<LocalDate> getEndOfVacation(){
		return EndOfVacation;
	}
	
	public Date getStartDate() {
		LocalDate dummy = LocalDate.now();
		LocalDate startDate = LocalDate.ofYearDay(dummy.getYear(), 1);
		Date vacationStartDate = Date.valueOf(startDate);
		return vacationStartDate;
	}
	
	public Date getEndDate() {
		LocalDate dummy = LocalDate.now().plusYears(1);
		LocalDate EndDate;
		if(dummy.isLeapYear()) {
			 EndDate = LocalDate.ofYearDay(dummy.getYear(), 366);
		}else {
			 EndDate = LocalDate.ofYearDay(dummy.getYear(), 365);
		}
			
		Date vacationEndDate = Date.valueOf(EndDate);
		return vacationEndDate;
		
		
	}
	
	public Leave[] getLeaveArr() {
		return leaveArr;
		
	}
	
}
