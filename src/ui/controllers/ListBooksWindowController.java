package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Book;
import business.controllers.impl.LibrarianController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.LibrarianInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
		
		tblBooks.setRowFactory(tv -> {
			TableRow<BookDataModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					BookDataModel rowData = row.getItem();
					
					LibrarianController c = new LibrarianController();
					DetailsBookWindowController.book = c.getBookByIsbn(rowData.getIsbn());
					
					WindowController.openPopup("DetailsBookWindow", this.getClass());
				}
			});
			return row;
		});

	}

	public ObservableList<BookDataModel> getBooksData() {
		return booksData;
	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}
	
	public void logout(ActionEvent event) {
		SystemController.currentLoggedInUser = null ;
		WindowController.openPopus("MainWindow", event, this.getClass());
	}

	public void getAllBooks() {
		LibrarianInterface c = new LibrarianController();
		List<Book> books = c.getAllBooks();

		for (Book b : books) {
			booksData.add(new BookDataModel(b));
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
