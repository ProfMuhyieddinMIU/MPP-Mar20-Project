package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.LoginException;
import business.controllers.impl.LibrarianController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.ControllerInterface;
import business.controllers.interfaces.LibrarianInterface;
import business.customExceptions.LibrarySystemException;
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

public class CheckoutWindowController implements Initializable {

	@FXML
	private Button checkoutButton;

	@FXML
	private TextField memberIdTextField;

	@FXML
	private TextField isbnTextField;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}

	public void checkout(ActionEvent event) {
		
		try {
			
			LibrarianInterface c = new LibrarianController();
			c.checkOutBook(memberIdTextField.getText(), isbnTextField.getText());
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Successed!");
			alert.setContentText("Check out is done successfully!");
			alert.showAndWait();
			

			WindowController.openWindow("ListCheckoutsWindow", event, this.getClass());
			
		} catch (LibrarySystemException e) {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Failed!");
			alert.setContentText(e.getMessage());
			alert.show();

		}

	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("LibrarianWindow", event, this.getClass());

	}

}
