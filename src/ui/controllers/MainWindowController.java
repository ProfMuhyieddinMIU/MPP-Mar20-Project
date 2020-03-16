package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.LoginException;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.ControllerInterface;
import dataaccess.Auth;
import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MainWindowController implements Initializable {

	@FXML
	private MenuItem exitMenuItem;

	@FXML
	private Button loginButton;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;
	
	@FXML
	private Label errorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}

	public void exitApplication(ActionEvent event) {
		System.exit(0);

	}

	public void login(ActionEvent event) {
		
		try {
			
			ControllerInterface c = new SystemController();
			c.login(usernameTextField.getText(), passwordTextField.getText());
			String auth = SystemController.currentLoggedInUser.getAuthorization().name();
			
			if(auth.equals(Auth.LIBRARIAN.name())) {
				WindowController.openWindow("LibrarianWindow", event, this.getClass());
			} else if(auth.equals(Auth.ADMIN.name())) {
				WindowController.openWindow("AdminWindow", event, this.getClass());
			} else {
				WindowController.openWindow("BothWindow", event, this.getClass());
			}
			
		} catch (LoginException e) {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid Credentionals!");
			alert.setContentText(e.getMessage());
			alert.show();

		}

	}
	

}
