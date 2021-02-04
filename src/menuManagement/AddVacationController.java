package menuManagement;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import calendarTools.CalendarManipulator;
import de.progex.main.Main;
import de.progex.model.Leave;
public class AddVacationController {

	@FXML DatePicker vacationStart;
	@FXML DatePicker vacationEnd;
	@FXML ChoiceBox reasonChoice;
	Alert informalert = new Alert(AlertType.INFORMATION);
	Alert startNotBeforeEnd = new Alert(AlertType.ERROR);
	Alert maxLeavesReached = new Alert(AlertType.ERROR);
	Alert wrongInput = new Alert(AlertType.ERROR);
	
	@FXML ImageView imView;
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	
	@FXML
	public void initialize(){
	    imView.setImage(image);
	}

	@FXML void callCreate() throws IOException {
		String menuLocation = "/FXML/Create.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callDelete() throws IOException {
		String menuLocation = "/FXML/Delete.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callUpdate() throws IOException {
		String menuLocation = "/FXML/Update.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callVacManager() throws IOException {
		String menuLocation = "/FXML/VacManager.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callMainMenu() throws IOException {
		String menuLocation = "/FXML/MainWindow.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	
		@FXML void setActiveCalendarStart() {
		 CalendarManipulator manipulator = new CalendarManipulator();
		 manipulator.markPeriodInCalendar(vacationStart);
		 
		 
	}
	
		@FXML void setActiveCalendarEnd() {
			 CalendarManipulator manipulator = new CalendarManipulator();
			 manipulator.markPeriodInCalendar(vacationEnd);
			 
		}
	
		@FXML void storeNewVacation() {
			
			CalendarManipulator manipulator = new CalendarManipulator();
			
			if(reasonChoice.getSelectionModel().getSelectedItem() != null & vacationStart.getValue()!= null & vacationEnd.getValue()!=null) {
			if(vacationStart.getValue().isBefore(vacationEnd.getValue()) || vacationStart.getValue().isEqual(vacationEnd.getValue()) )  {
				
			if(manipulator.vacationLimitReached(vacationStart.getValue(), vacationEnd.getValue())){
				
				maxLeavesReached.setTitle("Error Dialog");
				maxLeavesReached.setHeaderText("No employee can take a vacation. Maximum reached!");
				maxLeavesReached.setContentText("Please choose a different period.");

				maxLeavesReached.showAndWait();
				
			}else {

				
				
				Date vacationStartDate = Date.valueOf(vacationStart.getValue());
				Date vacationEndDate = Date.valueOf(vacationEnd.getValue());
				
				Leave le = new Leave(reasonChoice.getSelectionModel().getSelectedItem().toString(), vacationStartDate, vacationEndDate);
				
				Main.db.createLeave(Main.toBeModified.getEmployee_id(), le);
					informalert.setTitle("Information Dialog");
					informalert.setHeaderText("Leave has been added.");
					informalert.setContentText("Leave has been added to "+ Main.toBeModified.getFirstName() + " " + Main.toBeModified.getLastName());

					informalert.showAndWait();
				
				
				}
			}else{
				startNotBeforeEnd.setTitle("Error Dialog");
				startNotBeforeEnd.setHeaderText("The start date is not before the end date.");
				startNotBeforeEnd.setContentText("Please check the input fields.");

				startNotBeforeEnd.showAndWait();
			}
			}else {
				wrongInput.setTitle("Error Dialog");
				wrongInput.setHeaderText("Leave has not been added.");
				wrongInput.setContentText("Please check the input fields.");

				wrongInput.showAndWait();
			}
			
		}
	
	
		@FXML public void setChoiceBox(){
			reasonChoice.setItems(FXCollections.observableArrayList(
				    "holiday", "illness", "business trip"));
		}
			
		
}
