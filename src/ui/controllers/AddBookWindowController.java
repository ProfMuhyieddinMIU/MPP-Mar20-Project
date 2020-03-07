package ui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.media.sound.InvalidDataException;

import business.Author;
import business.LoginException;
import business.controllers.impl.AdminController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.AdminControllerInterface;
import business.controllers.interfaces.ControllerInterface;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class AddBookWindowController implements Initializable {

	@FXML
	private Button backButton;
	
	@FXML
	private Button addAuthorButton;
	
	@FXML
	private RadioButton rb7Days;
	
	@FXML
	private RadioButton rb21Days;
	
	@FXML
	private TextField isbnTextField;
	
	@FXML
	private TextField titleTextField;
	
	@FXML
	private MenuItem exitMenuItem;

	public static ObservableList<Author> authorsData = FXCollections.observableArrayList();

	@FXML
	private TableView<Author> tblAuthors;
	
	@FXML
	private TableColumn<Author, String> clmAuthors;
	
	@FXML
	private TableColumn<Author, String> clmLastName;
	
	@FXML
	private TableColumn<Author, String> clmBio;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		authorsData = FXCollections.observableArrayList();
		
		clmAuthors.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
		clmBio.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));
		
		TableViewLoad(authorsData);

	}
	
	private void TableViewLoad(ObservableList<Author> authorsData) {

		tblAuthors.setItems(getAuthorssData());

	}

	public ObservableList<Author> getAuthorssData() {
		return authorsData;
	}

	public void exitApplication(ActionEvent event) {
		System.exit(0);

	}

	public void addBook(ActionEvent event) {

		try {

			AdminControllerInterface c = new AdminController();

			System.out.println(isbnTextField.getText());
			System.out.println(titleTextField.getText());
			System.out.println(rb7Days.isSelected()?7:21);
			System.out.println(authorsData.size());
			
			c.addBook(isbnTextField.getText(), titleTextField.getText(), rb7Days.isSelected()?7:21, authorsData);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Success!");
			alert.setContentText("Book "+titleTextField.getText()+" has been added successfully!");
			alert.show();


		} catch (NumberFormatException | InvalidDataException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Failed!");
			alert.setContentText(e.getMessage());
			alert.show();

		}

	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
	
	public void openAddAuthor(ActionEvent event) {
		WindowController.openPopus("AddAuthorPopup", event, this.getClass());
	}

}
