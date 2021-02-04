package menuManagement;

import de.progex.model.Employee;
import de.progex.model.Leave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewManager {

	ObservableList <TableColumn> columns = FXCollections.observableArrayList();
	TableView table = new TableView();
	
	TableViewManager(TableView table){
		this.table = table;
	};
	
	public void setCellValues( TableColumn IDCol,TableColumn firstNameCol, TableColumn nameCol, TableColumn emailCol ) {
		
		IDCol.setCellValueFactory(
			    new PropertyValueFactory<Employee,String>("employee_id")
			);
		nameCol.setCellValueFactory(
			    new PropertyValueFactory<Employee,String>("lastName")
			);
			firstNameCol.setCellValueFactory(
			    new PropertyValueFactory<Employee,String>("firstName")
			);
			emailCol.setCellValueFactory(
			    new PropertyValueFactory<Employee,String>("email")
			);
	}
	
public void setCellValuesLeave( TableColumn reasonCol,TableColumn startCol, TableColumn endCol) {
		
		reasonCol.setCellValueFactory(
			    new PropertyValueFactory<Leave,String>("status")
			);
		startCol.setCellValueFactory(
			    new PropertyValueFactory<Leave,String>("startDate")
			);
			endCol.setCellValueFactory(
			    new PropertyValueFactory<Leave,String>("endDate")
			);
			
			
	}
	
}
