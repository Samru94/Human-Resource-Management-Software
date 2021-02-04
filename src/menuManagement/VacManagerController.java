package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import de.progex.main.Main;
import de.progex.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VacManagerController {

	@FXML TextField searchTextfield;
	@FXML TableView <Employee> vacationTableview;
	@FXML TableColumn nameCol ;
	@FXML TableColumn IDCol;
	@FXML TableColumn firstNameCol;
	@FXML TableColumn emailCol;
	@FXML Button vacButton;
	TableViewManager tableViewManager = new TableViewManager(vacationTableview);
	
	final ObservableList<Employee> data =
	        FXCollections.observableArrayList();
	
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
	
	@FXML void callAddVacationMenu() throws IOException {
		String menuLocation = "/FXML/AddVacation.fxml";
		Main.toBeModified =  vacationTableview.getSelectionModel().getSelectedItem();
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	
	}
	
	

	@FXML void searchEmployeeInTableview() {
		Employee[] tmp;
		tmp = Main.db.searchEmployee(searchTextfield.getText());
		vacationTableview.setEditable(true);
		tableViewManager.setCellValues(IDCol, firstNameCol, nameCol, emailCol);
		displayFoundEmployees(tmp);
			
	}

	protected void displayFoundEmployees(Employee [] setOfEmployees) {
		data.clear(); //alte Daten löschen	
		
		for(int i=0; i< setOfEmployees.length; i++) {
			
			
		     data.add(setOfEmployees[i]);
		     vacationTableview.setItems(data);
			
			
		}
		
	}
	
	@FXML
	void clickRow() {
		vacButton.setVisible(true);
	}


}
