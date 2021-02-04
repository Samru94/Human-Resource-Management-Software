package menuManagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import de.progex.main.Main;
import de.progex.model.Employee;
import de.progex.model.Leave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DetailsOfEmployeeController4 {


	@FXML ImageView HRimageview;
	@FXML ImageView iconImageview;
	@FXML Label name;
	@FXML Label department;
	@FXML Label id;
	@FXML Button deleteLeave;
	
	@FXML TableView <Leave> leaveTableview;
	@FXML TableColumn reasonCol ;
	@FXML TableColumn startCol;
	@FXML TableColumn endCol;
	TableViewManager tableViewManager = new TableViewManager(leaveTableview);

	final ObservableList<Leave> data =
	        FXCollections.observableArrayList();
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	ButtonType buttonTypeOK = new ButtonType("OK", ButtonData.OK_DONE);
	
	
	

	
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	
	InputStream input2 = getClass().getResourceAsStream("icon.jpeg");
	Image image2 = new Image(input2);
	
	@FXML
	public void initialize(){
		

		
		 name.setText(Main.toBeModified.getFirstName()+ " "+ Main.toBeModified.getLastName());
		 department.setText("Department: "+ Main.toBeModified.getDepartment());
		 id.setText("ID: " + Main.toBeModified.getEmployee_id());
		
		 searchLeavesInTableview();
		 
		 HRimageview.setImage(image);
		 iconImageview.setImage(image2);
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
	
	@FXML void callDetailsOfEmployee3() throws IOException {
		String menuLocation = "/FXML/DetailsOfEmployee3.fxml";
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
	
	

	 void searchLeavesInTableview() {
		Leave[] tmp;
		tmp = Main.db.getLeave(Main.toBeModified.getEmployee_id());
		leaveTableview.setEditable(true);
		tableViewManager.setCellValuesLeave(reasonCol, startCol, endCol);
		displayFoundLeaves(tmp);
			
	}

	protected void displayFoundLeaves(Leave [] setOfLeaves) {
		data.clear(); //alte Daten löschen	
		
		for(int i=0; i< setOfLeaves.length; i++) {
			
			
		     data.add(setOfLeaves[i]);
		     leaveTableview.setItems(data);
			
			
		}
	
	}
	
	@FXML
	void clickRow() {
		deleteLeave.setVisible(true);
	}
	
	@FXML public void deleteLeaves(){
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("You are going to delete this Leave!");
		alert.setContentText("Are you ok with this?");
		alert.getButtonTypes().setAll( buttonTypeCancel, buttonTypeOK);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOK){
		    // ... user chose OK
			Leave toBeDeleted =  leaveTableview.getSelectionModel().getSelectedItem();
			Main.db.deleteLeave(toBeDeleted.getLeave_id(), toBeDeleted.getStartDate(), toBeDeleted.getEndDate());
			searchLeavesInTableview();
			System.out.println(toBeDeleted.getStartDate());
			System.out.println(toBeDeleted.getEndDate());
			System.out.println(toBeDeleted.getLeave_id());
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
		
	}
	
}
