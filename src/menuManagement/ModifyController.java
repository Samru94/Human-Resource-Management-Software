package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import de.progex.main.Main;
import de.progex.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModifyController {

	@FXML TextField nameTextfield;
	@FXML TextField firstNameTextfield;
	@FXML TextField streetNumbTextfield;
	@FXML TextField emailTextfield;
	@FXML TextField cityTextfield;
	@FXML TextField skillsTextfield;
	@FXML TextField departmentTextfield;
	@FXML TextField salaryTextfield;
	@FXML Button confirmButton;
	@FXML Button setValueButton;
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
	@FXML public void setValues() {
		UpdateController.getEmployeetoBeModified();
		nameTextfield.setText(Main.toBeModified.getLastName());
		firstNameTextfield.setText(Main.toBeModified.getFirstName());
		streetNumbTextfield.setText(Main.toBeModified.getStreetAndNumber());
		emailTextfield.setText(Main.toBeModified.getEmail());
		cityTextfield.setText(Main.toBeModified.getCity());
		skillsTextfield.setText(Main.toBeModified.getSkills());
		departmentTextfield.setText(Main.toBeModified.getDepartment());
		String salaryAsText = String.valueOf(Main.toBeModified.getSalary());
		salaryTextfield.setText(salaryAsText);
		
	}
	
@FXML void updateChosenEmployee() throws IllegalAccessException {
		
		double salary = 0;
		
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
			informalert.setHeaderText("Employee has been updated.");
			informalert.showAndWait();	
			Main.toBeModified.copyEmployee(emp1);
			Main.db.updateEmployee(Main.toBeModified.getEmployee_id(), Main.toBeModified);
		}else {
			
		erroralert.setTitle("Error Dialog");
		erroralert.setHeaderText("Employee has not been updated.");
		erroralert.setContentText("Please check the input fields: " + verifyInput(emp1));

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

}
