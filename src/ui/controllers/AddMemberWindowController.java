package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.LibraryMember;
import business.LoginException;
import business.controllers.impl.AdminController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.AdminControllerInterface;
import business.controllers.interfaces.ControllerInterface;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.MemberInvalidDataException;
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
	private Button addButton;
	
	@FXML
	private Button backButton;

	@FXML
	private TextField firstNameTextField;
	
	@FXML
	private TextField lastNameTextField;
	
	@FXML
	private TextField mobileTextField;
	
	@FXML
	private TextField emailTextField;
	
	@FXML
	private TextField streetTextField;
	
	@FXML
	private TextField cityTextField;
	
	@FXML
	private TextField stateTextField;
	
	@FXML
	private TextField zipTextField;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}
	
	public void logout(ActionEvent event) {
		SystemController.currentLoggedInUser = null ;
		WindowController.openPopus("MainWindow", event, this.getClass());
	}

	public void addMember(ActionEvent event) {
		
		try {

			AdminControllerInterface c = new AdminController();

			LibraryMember member = c.addMember(firstNameTextField.getText(), lastNameTextField.getText(), mobileTextField.getText(), emailTextField.getText(), streetTextField.getText(), stateTextField.getText(), cityTextField.getText(), zipTextField.getText());
		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Successed!");
			alert.setContentText("Member created successfully! His ID is "+ member.getMemberId());
			alert.showAndWait();
			

			WindowController.openWindow("ListMembersWindow", event, this.getClass());

		} catch (NumberFormatException | MemberInvalidDataException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Failed!");
			alert.setContentText(e.getMessage());
			alert.show();

		}

	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
	
	public void openAddMember(ActionEvent event) {
		WindowController.openWindow("AddMemberWindow", event, this.getClass());

	}
	
	public void openAddCopy(ActionEvent event) {
		WindowController.openWindow("AddCopyWindow", event, this.getClass());

	}
	
	public void openAddBook(ActionEvent event) {
		WindowController.openWindow("AddBookWindow", event, this.getClass());

	}
	
	public void openListCheckouts(ActionEvent event) {
		WindowController.openWindow("ListCheckoutsWindow", event, this.getClass());

	}
	
	public void openListMembers(ActionEvent event) {
		WindowController.openWindow("ListMembersWindow", event, this.getClass());

	}
	
	public void openListBooks(ActionEvent event) {
		WindowController.openWindow("ListBooksWindow", event, this.getClass());
	}

}
