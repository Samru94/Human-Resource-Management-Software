package menuManagement;



import java.io.IOException;

import de.progex.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MenuManager {

	private AnchorPane containsCurrentMenu ;
	
	public void loadMainMenu(String menuLocation) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(menuLocation));
		containsCurrentMenu =(AnchorPane) loader.load();
	}
	
	public void loadMenu(String menuLocation) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(menuLocation));
		containsCurrentMenu =(AnchorPane) loader.load();
	}
	public void showMenu() {
		Scene currentMenue = new Scene(containsCurrentMenu);
		currentMenue.setFill(Color.TRANSPARENT);
		Main.menuStage.setScene(currentMenue);
		Main.menuStage.show();
	}
	
}
