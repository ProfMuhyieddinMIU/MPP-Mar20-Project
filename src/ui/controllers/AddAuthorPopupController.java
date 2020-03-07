package ui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import business.Address;
import business.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAuthorPopupController implements Initializable {

	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private TextField firstNameTextField;
	
	@FXML
	private TextField lastNameTextField;
	
	@FXML
	private TextArea bioTextArea;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	

	public void ok(ActionEvent event) {

		Author a = new Author(firstNameTextField.getText(), lastNameTextField.getText(), "", new Address("", "", "", ""), bioTextArea.getText() , "");
		AddBookWindowController.authorsData.add(a);
		AddBookWindowController.authors.add(a);
		((Node)(event.getSource())).getScene().getWindow().hide();

	}
	
	public void cancel(ActionEvent event) {

		((Node)(event.getSource())).getScene().getWindow().hide();

	}
	

}
