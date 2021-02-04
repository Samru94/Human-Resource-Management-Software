package calendarTools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class CalendarManipulator {
	
	RegisteredLeave ListOfLeaves = new RegisteredLeave(); 
    int[][] markedDays = ListOfLeaves.getMarkedDays();

	public void markPeriodInCalendar( DatePicker Calendar ) {
		 
			 Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
		     Calendar.setDayCellFactory(dayCellFactory);
		  }
	
	

	 private Callback<DatePicker, DateCell> getDayCellFactory() {
		 
	        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
	 
	            @Override
	            public DateCell call(final DatePicker datePicker) {
	                return new DateCell() {
	                    @Override
	                    public void updateItem(LocalDate item, boolean empty) {
	                        super.updateItem(item, empty);
	                        
	                       
	                        
	                       LocalDate referenceYear = LocalDate.now();
	                       
	                        if(item.isBefore(referenceYear)) {
	                        	setDisable(true);
	                        }
	                        
	                        //aktuelles Jahr
	            			for(int j = 0 ; j < markedDays.length ; j++) {
	            				  if (markedDays[0][item.getDayOfYear()] == 1 && referenceYear.getYear() == item.getYear()) {
	            					  setStyle("-fx-background-color: #F8E58B ");
	            					  setTooltip(new Tooltip("1 Mitarbeiter im Urlaub"));
	            					  }
	            				  else if(markedDays[0][item.getDayOfYear()] == 2 && referenceYear.getYear() == item.getYear()) {
         							 setStyle("-fx-background-color: #F8E58B");
	            					  	 setTooltip(new Tooltip("2 Mitarbeiter im Urlaub"));
	            					  	// setDisable(true);
         						}
	            				  	
	            				  else if(markedDays[0][item.getDayOfYear()] == 3 && referenceYear.getYear() == item.getYear()) {
	            							 setStyle("-fx-background-color: #F3605A");
	   	            					  	 setTooltip(new Tooltip("3 Mitarbeiter im Urlaub"));
	   	            					  	// setDisable(true);
	            						}
	            				}
	            			
	            			//nächstes Jahr
	            			for(int j = 0 ; j < markedDays.length ; j++) {
	            				  if (markedDays[1][item.getDayOfYear()] == 1 && referenceYear.getYear()+1 == item.getYear()) {
	            					  setStyle("-fx-background-color: #F8E58B ");
	            					  setTooltip(new Tooltip("1 Mitarbeiter im Urlaub"));
	            					  }
	            				  else if(markedDays[1][item.getDayOfYear()] == 2 && referenceYear.getYear()+1 == item.getYear()) {
       							 setStyle("-fx-background-color: #F8E58B");
	            					  	 setTooltip(new Tooltip("2 Mitarbeiter im Urlaub"));
	            					  	// setDisable(true);
       						}
	            				  	
	            				  else if(markedDays[1][item.getDayOfYear()] == 3 && referenceYear.getYear()+1 == item.getYear()) {
	            							 setStyle("-fx-background-color: #F3605A");
	   	            					  	 setTooltip(new Tooltip("3 Mitarbeiter im Urlaub"));
	   	            					  	// setDisable(true);
	            						}
	            				}
	            			
	            			
	                    	
	                    }
	                };
	            }
	        };
	       
	        return dayCellFactory;
	        
	    }
	
	 
	 // Funktion muss überarbeitet werden weil markedDays jetzt 2 jahre hat ansonsten arrayoutofboundsError
	 public boolean vacationLimitReached (LocalDate StartOfVacation, LocalDate EndOfVacation) {
		 
		 	if(StartOfVacation.getYear() != EndOfVacation.getYear()) {
		 		
		 		for(int j = StartOfVacation.getDayOfYear() ; j <= 366; j++) {
					if(markedDays[0][j]== 3) {
						return true;
					}
				}
				

				for(int j = 1 ; j <= EndOfVacation.getDayOfYear(); j++) {
					if(markedDays[1][j] == 3) {
						return true;
					}
				}
		 		
		 		//hier muss inhalt hinzugefügt werden kann ähnlich gemacht werden wie markDays() aus regitseredLeaves
		 	
		 	}else {
		 
		 
			for(int i = StartOfVacation.getDayOfYear() ; i <= EndOfVacation.getDayOfYear() ; i++) {
				if(markedDays[0][i] == 3) {
				return true;
					}
				}
		 	}
			
			return false;
			}
			

		}
	 

