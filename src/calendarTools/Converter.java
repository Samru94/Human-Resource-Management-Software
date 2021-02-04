package calendarTools;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMM/yyyy");
	
	public LocalDate stringToLocalDate (String date) {
		
		 LocalDate localDate = LocalDate.parse(date, formatter);

	      
		
	        return localDate; 
	}
	
	public String localDateToString(LocalDate date) {
		
		String formattedString = date.format(formatter);
		return formattedString;
		
	}
	
	
}
