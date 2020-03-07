package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Author;
import business.Book;
import business.controllers.impl.LibrarianController;
import business.controllers.interfaces.LibrarianInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.dataModel.BookDataModel;

public class DetailsBookWindowController implements Initializable {

	@FXML
	private Button okButton;
	
	@FXML
	private Label lblTitle;
	
	
	@FXML
	private ImageView imgBook;

	@FXML
	private TextField availableTextField;
	
	@FXML
	private TextField maxTextField;
	
	@FXML
	private TextField totalTextField;

	ObservableList<Author> authorsData = FXCollections.observableArrayList();

	@FXML
	private TableView<Author> tblAuthors;
	
	@FXML
	private TableColumn<Author, String> clmFirstName;
	
	@FXML
	private TableColumn<Author, String> clmLastName;
	
	@FXML
	private TableColumn<Author, String> clmBio;
	
	public static Book book ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblTitle.setText(book.getTitle());

		clmFirstName.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
		clmBio.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));
		
		authorsData.addAll(book.getAuthors());
		TableViewLoad(authorsData);
		
		availableTextField.setText(book.getNumberOfAvailable()+"");
		maxTextField.setText(book.getMaxCheckoutLength()+"");
		totalTextField.setText(book.getNumCopies()+"");

		
		
		URL url = this.getClass().getResource("/ui/scenebuilder/images/books/"+book.getIsbn()+".jpg");
		if(url != null) {
			Image image = new Image(url.toString());
			imgBook.setImage(image);
		}

	}

	private void TableViewLoad(ObservableList<Author> booksData) {

		tblAuthors.setItems(getAuthorsData());


	}

	public ObservableList<Author> getAuthorsData() {
		return authorsData;
	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}


	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
	
	public void ok(ActionEvent event) {

		((Node)(event.getSource())).getScene().getWindow().hide();

	} 

}
