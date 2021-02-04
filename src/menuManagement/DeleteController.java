package menuManagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import de.progex.main.Main;
import de.progex.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DeleteController {
	
	@FXML TextField searchTextfield;
	@FXML TableView <Employee> deleteTableview;
	@FXML TableColumn nameCol ;
	@FXML TableColumn IDCol;
	@FXML TableColumn firstNameCol;
	@FXML TableColumn emailCol;
	@FXML Button deleteButton;
	TableViewManager tableViewManager = new TableViewManager(deleteTableview);
	Alert alert = new Alert(AlertType.CONFIRMATION);
	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType buttonTypeOK = new ButtonType("OK", ButtonData.OK_DONE);
	
	
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
	
	@FXML void searchEmployeeInTableview() {
		Employee[] tmp;
		tmp = Main.db.searchEmployee(searchTextfield.getText());
		deleteTableview.setEditable(true);
		tableViewManager.setCellValues(IDCol, firstNameCol, nameCol, emailCol);
		displayFoundEmployees(tmp);
			
	}

	protected void displayFoundEmployees(Employee [] setOfEmployees) {
		data.clear(); //alte Daten löschen	
		
		for(int i=0; i< setOfEmployees.length; i++) {
			
			
		     data.add(setOfEmployees[i]);
		     deleteTableview.setItems(data);
			
			
		}
		
	}
	
	@FXML
	void clickRow() {
		deleteButton.setVisible(true);
	}

	@FXML protected void deleteChosenEmployee(){
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are going to delete this Employee!");
		alert.setContentText("Are you ok with this?");
		alert.getButtonTypes().setAll( buttonTypeCancel, buttonTypeOK);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOK){
		    // ... user chose OK
			Employee toBeDeleted =  deleteTableview.getSelectionModel().getSelectedItem();
			Main.db.deleteEmployee(toBeDeleted.getEmployee_id());
			searchEmployeeInTableview();
			
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
		
	}
}
