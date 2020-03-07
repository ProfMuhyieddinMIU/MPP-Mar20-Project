package ui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.media.sound.InvalidDataException;

import business.Author;
import business.Book;
import business.LibraryMember;
import business.LoginException;
import business.controllers.impl.AdminController;
import business.controllers.impl.EmailSenderController;
import business.controllers.impl.LibrarianController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.AdminControllerInterface;
import business.controllers.interfaces.ControllerInterface;
import business.controllers.interfaces.EmailSenderControllerInterface;
import business.customExceptions.BookInvalidDataException;
import business.customExceptions.BookNotFoundException;
import business.customExceptions.LibrarySystemException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.dataModel.BookDataModel;
import ui.dataModel.CheckoutDataModel;
import ui.dataModel.MemberDataModel;

public class DetailsCheckoutRecordWindowController implements Initializable {

	@FXML
	private Button checkInButton;
	
	@FXML
	private Button cancelButton;

	@FXML
	private Label lblTitle ; 
	
	@FXML
	private Label lblISBN ; 
	
	@FXML
	private Label lblBookTitle ; 
	
	@FXML
	private Label lblMemberID ; 
	
	@FXML
	private Label lblMemberName ; 


	@FXML
	private ImageView imgBook;
	
	
	public static Book book ; 
	
	@FXML
	private ImageView imgMember;
	
	
	public static LibraryMember member ; 
	
	public static String recordsStatus = "" ; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblMemberID.setText(member.getMemberId());
		lblMemberID.setStyle("-fx-background-color: black");
		
		lblMemberName.setText(member.getFirstName()+" "+member.getLastName());
		lblMemberName.setStyle("-fx-background-color: black");
		
		URL urlUser = this.getClass().getResource("/ui/scenebuilder/images/users/"+member.getMemberId()+".jpg");
		if(urlUser != null) {
			Image image = new Image(urlUser.toString());
	        imgMember.setImage(image);
		}
		
		lblBookTitle.setText(book.getTitle());
		lblBookTitle.setStyle("-fx-background-color: black");
		
		lblISBN.setText(book.getIsbn());
		lblISBN.setStyle("-fx-background-color: black");
		
		URL urlBook = this.getClass().getResource("/ui/scenebuilder/images/books/"+book.getIsbn()+".jpg");
		if(urlBook != null) {
			Image image = new Image(urlBook.toString());
	        imgBook.setImage(image);
		}
		
		if(recordsStatus.equals("Overdue!")) {
			checkInButton.setText(recordsStatus);
			checkInButton.setStyle("-fx-background-color: red");
		}else {
			checkInButton.setStyle("-fx-background-color: green");
		}
		
        
	}

	public void exitApplication(ActionEvent event) {
		System.exit(0);

	}
	
	public void cancel(ActionEvent event) {

		((Node)(event.getSource())).getScene().getWindow().hide();

	}
	
	public void checkIn(ActionEvent event) {

		
		try {
		
			if(recordsStatus.equals("Overdue!")) {
				EmailSenderController c = new EmailSenderController();
				c.sendEmail(member.getEmail(), book.getTitle(), book.getIsbn(), member.getFirstName());
				((Node)(event.getSource())).getScene().getWindow().hide();
				
			}else {
				LibrarianController c = new LibrarianController();
				c.bookReturn(null, member.getMemberId(), book.getIsbn());
				((Node)(event.getSource())).getScene().getWindow().hide();
			}
			
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	

}
