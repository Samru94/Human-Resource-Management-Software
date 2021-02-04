package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import de.progex.main.Main;
import de.progex.model.Employee;
import de.progex.model.Leave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DetailsOfEmployeeController3 {

	@FXML ListView<String> display;
	@FXML ImageView HRimageview;
	@FXML ImageView iconImageView;
	@FXML Label name;
	@FXML Label department;
	@FXML Label id;
	@FXML Label email;
	@FXML Label city;
	@FXML Label street;
	@FXML Label skills;
	@FXML Label salary;

	
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	
	InputStream input2 = getClass().getResourceAsStream("icon.jpeg");
	Image image2 = new Image(input2);
	
	
	@FXML
	public void initialize(){
		

		
		 name.setText(Main.toBeModified.getFirstName()+ " "+ Main.toBeModified.getLastName());
		 department.setText("Department: "+ Main.toBeModified.getDepartment() );
		 id.setText("ID: " + Main.toBeModified.getEmployee_id());
		 email.setText(Main.toBeModified.getEmail());
		 city.setText(Main.toBeModified.getCity());
		 street.setText(Main.toBeModified.getStreetAndNumber());
		 skills.setText(Main.toBeModified.getSkills());
		 double salaryDouble = Main.toBeModified.getSalary();
		 String salaryString = Double.toString(salaryDouble);
		 salary.setText(salaryString);
		
		 
		 HRimageview.setImage(image);
		 iconImageView.setImage(image2);
	}
	
	@FXML void callCreate() throws IOException {
		String menuLocation = "/FXML/Create.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callDelete() throws IOException {
		String menuLocation = "/FXML/Delete.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callUpdate() throws IOException {
		String menuLocation = "/FXML/Update.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callVacManager() throws IOException {
		String menuLocation = "/FXML/VacManager.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callMainMenu() throws IOException {
		String menuLocation = "/FXML/MainWindow.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void callDetailsOfEmployee4() throws IOException {
		String menuLocation = "/FXML/DetailsOfEmployee4.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		input2.close();
		Main.menuManager.showMenu();
	}
	
	
	
}
