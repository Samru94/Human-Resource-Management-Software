package menuManagement;


import java.applet.Applet;
import java.applet.AudioClip;
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

public class MainWindowController {

	@FXML TextField searchTextfield;
	@FXML TableView <Employee> mainTableview;
	@FXML TableColumn nameCol ;
	@FXML TableColumn IDCol;
	@FXML TableColumn firstNameCol;
	@FXML TableColumn emailCol;
	@FXML TableColumn streetCol;
	@FXML Button showDetails;
	
	@FXML ImageView imView;
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	
	@FXML
	public void initialize(){
	    imView.setImage(image);
	    searchEmployeeInTableview();
	    
	}
	
	
	TableViewManager tableViewManager = new TableViewManager(mainTableview);
	
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
	
	@FXML void callDetailsMenu() throws IOException {
		Main.toBeModified =  mainTableview.getSelectionModel().getSelectedItem();
		String menuLocation = "/FXML/DetailsOfEmployee3.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
	}
	
	

	 public void fillMainTable() {
		Employee[] tmp;
		tmp = Main.db.getAllEmployee();
		mainTableview.setEditable(true);
		tableViewManager.setCellValues(IDCol, firstNameCol, nameCol, emailCol);
		displayFoundEmployees(tmp);
	}
	
	@FXML void searchEmployeeInTableview() {
		
		Employee[] tmp;
		tmp = Main.db.searchEmployee(searchTextfield.getText());
		mainTableview.setEditable(true);
		tableViewManager.setCellValues(IDCol, firstNameCol, nameCol, emailCol);
		displayFoundEmployees(tmp);
			
	}

	protected void displayFoundEmployees(Employee [] setOfEmployees) {
		
		data.clear(); //alte Daten löschen	
		
		for(int i=0; i< setOfEmployees.length; i++) {
			
			
		     data.add(setOfEmployees[i]);
		     mainTableview.setItems(data);
			
			
		}
		
	}
	
	@FXML
	void clickRow() {
		showDetails.setVisible(true);
	}

	
	
	
}
