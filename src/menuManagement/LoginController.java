package menuManagement;

import java.io.IOException;
import java.io.InputStream;

import de.progex.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

	@FXML ImageView imView;
	InputStream input = getClass().getResourceAsStream("progEx.png");
	Image image = new Image(input);
	@FXML TextField userName;
	@FXML PasswordField password;
	
	@FXML
	public void initialize(){
	    imView.setImage(image);
	}
	
	@FXML
	void callMainWindow() throws IOException {
		if( userName.getText().equals("admin") && password.getText().equals("admin")) {
		String menuLocation = "/FXML/MainWindow.fxml";
		Main.menuManager.loadMenu(menuLocation);
		input.close();
		Main.menuManager.showMenu();
		}
	}
	
}
