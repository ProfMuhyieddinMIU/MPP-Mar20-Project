package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import business.LoginException;
import business.controllers.impl.AdminController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.AdminControllerInterface;
import business.controllers.interfaces.ControllerInterface;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
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

public class AddCopyWindowController implements Initializable {

	@FXML
	private Button backButton;
	
	@FXML
	private TextField isbnTextField;

	@FXML
	private TextField countTextField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public void logout(ActionEvent event) {
		SystemController.currentLoggedInUser = null ;
		WindowController.openPopus("MainWindow", event, this.getClass());
	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}

	public void addCopy(ActionEvent event) {

		try {

			AdminControllerInterface c = new AdminController();

			c.addBookCopy(isbnTextField.getText(), Integer.parseInt(countTextField.getText()));
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Successed!");
			alert.setContentText("You added "+ countTextField.getText() + " copy/ies to Book with ISBN # "+ isbnTextField.getText());
			alert.showAndWait();

			WindowController.openWindow("ListBooksWindow", event, this.getClass());

		} catch (NumberFormatException | BookNotFoundException | BookInvalidDataException e) {

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
