package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import de.progex.main.Main;
import de.progex.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateController {
	
	@FXML TextField nameTextfield;
	@FXML TextField firstNameTextfield;
	@FXML TextField streetNumbTextfield;
	@FXML TextField emailTextfield;
	@FXML TextField cityTextfield;
	@FXML TextField skillsTextfield;
	@FXML TextField departmentTextfield;
	@FXML TextField salaryTextfield;
	@FXML Button confirmButton;
	Alert informalert = new Alert(AlertType.INFORMATION);
	Alert erroralert = new Alert(AlertType.ERROR);
	
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
	
	@FXML void storeEmployeeDatabase() throws IllegalAccessException, IOException {
		
		double salary = 0 ;
		
		try
	    {
	      salary =  Double.parseDouble(salaryTextfield.getText());
	        // Valid double
	    }
	    catch (NullPointerException | NumberFormatException ex)
	    {
	        // Not valid double
	    }
		
		
		
		
		Employee emp1 = new Employee(firstNameTextfield.getText(), nameTextfield.getText(), emailTextfield.getText(), streetNumbTextfield.getText(), cityTextfield.getText(), skillsTextfield.getText(), departmentTextfield.getText(), salary);
		if(emp1.isVerified() && salary != 0) {
		
		informalert.setTitle("Information Dialog");
		informalert.setHeaderText("Employee has been added.");
			//alert.setContentText("I have a great message for you!");

		informalert.showAndWait();	
		Main.db.createEmployee(emp1);
		callCreate();
		}else {
			
		erroralert.setTitle("Error Dialog");
		erroralert.setHeaderText("Employee has not been added.");
		erroralert.setContentText("Please check the input fields:" + verifyInput(emp1));

		erroralert.showAndWait();
		}
		
		
		
	}
	
	
	public static String verifyInput(Employee emp1) throws IllegalAccessException {
		//Funktion öffnet sich nur, wenn es einen Eingabefehler gibt.
		String errorMessage = "";

		if (emp1.getFirstName() == null) {
			errorMessage = errorMessage + " First Name ";
		}
		
		if (emp1.getLastName() == null) {
			errorMessage = errorMessage + " Last Name ";
		}
		

		if (emp1.getEmail() == null) {
			errorMessage = errorMessage + " EMail ";
		}
		
		
		if (emp1.getStreetAndNumber() == null) {
			errorMessage = errorMessage + " Street and Number ";
		}
		
		if (emp1.getCity() == null) {
			errorMessage = errorMessage + " City ";
		}
		
		if (emp1.getSkills() == null) {
			errorMessage = errorMessage + " Skill ";
		}
		
		if (emp1.getDepartment() == null) {
			errorMessage = errorMessage + " Department ";
		}
		
		

		if (emp1.getSalary() == 0) {
			errorMessage = errorMessage + " Salary ";
		}
		
		
		//Die "addierten" Strings werden zurückgegeben (kein else if verwenden)
		return errorMessage; 	
	}
	
	/*
	 * String firstName, String lastName, String email, String streetAndNumber, String city, String skills,
			String department, double salary
	 * 
	 */
	
	
}
