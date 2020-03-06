package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.LoginException;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.ControllerInterface;
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

public class AddMemberWindowController implements Initializable {

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
		System.out.println("Application Exit!");

		System.exit(0);

	}

	public void addMember(ActionEvent event) {
		
		try {
			
			ControllerInterface c = new SystemController();
			c.login(usernameTextField.getText(), passwordTextField.getText());
			System.out.println(SystemController.currentLoggedInUser.getAuthorization());
		} catch (LoginException e) {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid Credentionals!");
			alert.setContentText(e.getMessage());
			alert.show();

		}

	}

}
