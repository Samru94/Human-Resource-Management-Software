package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import menuManagement.TableViewManager;
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

public class UpdateController {
	@FXML TextField searchTextfield;
	@FXML TableView <Employee> updateTableview;
	@FXML TableColumn nameCol ;
	@FXML TableColumn IDCol;
	@FXML TableColumn firstNameCol;
	@FXML TableColumn emailCol;
	@FXML Button modifyButton;
	TableViewManager tableViewManager = new TableViewManager(updateTableview);
	
	@FXML ImageView imView;
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	
	@FXML
	public void initialize(){
	    imView.setImage(image);
	}

	final ObservableList<Employee> data =
	        FXCollections.observableArrayList();

	
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
	

	@FXML void callModifyMenu() throws IOException {
		String menuLocation = "/FXML/ModifyEmployee.fxml";
		Main.toBeModified =  updateTableview.getSelectionModel().getSelectedItem();
		
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	@FXML void searchEmployeeInTableview() {
		Employee[] tmp;
		tmp = Main.db.searchEmployee(searchTextfield.getText());
		updateTableview.setEditable(true);
		tableViewManager.setCellValues(IDCol, firstNameCol, nameCol, emailCol);
		displayFoundEmployees(tmp);
			
	}

	protected void displayFoundEmployees(Employee [] setOfEmployees) {
		data.clear(); //alte Daten löschen	
		
		for(int i=0; i< setOfEmployees.length; i++) {
			
			
		     data.add(setOfEmployees[i]);
		     updateTableview.setItems(data);
			
			
		}
		
	}
	
	@FXML
	void clickRow() {
		modifyButton.setVisible(true);
	}
	
	public static Employee getEmployeetoBeModified() {
		return Main.toBeModified;
	}
}
