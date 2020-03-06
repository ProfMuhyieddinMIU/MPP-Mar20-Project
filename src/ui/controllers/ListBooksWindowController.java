package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Book;
import business.controllers.impl.LibrarianController;
import business.controllers.interfaces.LibrarianInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.dataModel.BookDataModel;

public class ListBooksWindowController implements Initializable {
	
	@FXML
	private Button backButton;

	@FXML
	private MenuItem exitMenuItem;

	ObservableList<BookDataModel> booksData = FXCollections.observableArrayList();

	@FXML
	private TableView<BookDataModel> tblBooks;
	@FXML
	private TableColumn<BookDataModel, String> clmISBN;
	@FXML
	private TableColumn<BookDataModel, String> clmTitle;
	@FXML
	private TableColumn<BookDataModel, String> clmAuthors;
	@FXML
	private TableColumn<BookDataModel, String> clmDays;
	@FXML
	private TableColumn<BookDataModel, String> clmCopies;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		clmISBN.setCellValueFactory(new PropertyValueFactory<BookDataModel, String>("isbn"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<BookDataModel, String>("title"));
		clmAuthors.setCellValueFactory(new PropertyValueFactory<BookDataModel, String>("authors"));
		clmDays.setCellValueFactory(new PropertyValueFactory<BookDataModel, String>("maxCheckoutLength"));
		clmCopies.setCellValueFactory(new PropertyValueFactory<BookDataModel, String>("copies"));

		this.getAllBooks();
		
		TableViewLoad(booksData);

	}

	private void TableViewLoad(ObservableList<BookDataModel> booksData) {

		tblBooks.setItems(getBooksData());

	}

	public ObservableList<BookDataModel> getBooksData() {
		return booksData;
	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}

	public void getAllBooks() {
		LibrarianInterface c = new LibrarianController();
		List<Book> books = c.getAllBooks();
		
		for(Book b: books){
			booksData.add(new BookDataModel(b));
		}
	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
	
	

}
