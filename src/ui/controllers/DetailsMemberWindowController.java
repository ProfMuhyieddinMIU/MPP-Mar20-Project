package ui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.media.sound.InvalidDataException;

import business.Author;
import business.LibraryMember;
import business.LoginException;
import business.controllers.impl.AdminController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.AdminControllerInterface;
import business.controllers.interfaces.ControllerInterface;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.MemberInvalidDataException;
import business.customExceptions.MemberNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.dataModel.BookDataModel;
import ui.dataModel.CheckoutDataModel;
import ui.dataModel.MemberDataModel;

public class DetailsMemberWindowController implements Initializable {

	@FXML
	private Button saveButton;
	
	@FXML
	private Button cancelButton;

	@FXML
	private Label lblTitle ; 
	
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
	
	public static LibraryMember member ; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblTitle.setText("Member # " + member.getMemberId());
		firstNameTextField.setText(member.getFirstName());
		lastNameTextField.setText(member.getLastName());
		mobileTextField.setText(member.getTelephone());
		emailTextField.setText(member.getEmail());
		streetTextField.setText(member.getAddress().getStreet());
		cityTextField.setText(member.getAddress().getStreet());
		stateTextField.setText(member.getAddress().getState());
		zipTextField.setText(member.getAddress().getZip());

	}

	public void exitApplication(ActionEvent event) {
		System.exit(0);

	}

	public void updateMember(ActionEvent event) {

		
		try {
			
			AdminController c = new AdminController();
			c.editMember(member.getMemberId(), firstNameTextField.getText(), lastNameTextField.getText(), mobileTextField.getText(), 
					emailTextField.getText(), streetTextField.getText(), stateTextField.getText(), cityTextField.getText(), zipTextField.getText());
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Successed!");
			alert.setContentText("Info of Member # "+ member.getMemberId() + " have been updated Successfully" );
			alert.show();
			
			WindowController.openWindow("ListMembersWindow", event, this.getClass());
			
		} catch (MemberInvalidDataException | MemberNotFoundException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Failed!");
			alert.setContentText(e.getMessage());
			alert.show();
		}

	}
	
	public void cancel(ActionEvent event) {

		((Node)(event.getSource())).getScene().getWindow().hide();

	}
	

}
