package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Book;
import business.CheckOutRecord;
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
import ui.dataModel.CheckoutDataModel;

public class ListCheckoutsWindowController implements Initializable {
	
	@FXML
	private Button backButton;

	@FXML
	private MenuItem exitMenuItem;

	ObservableList<CheckoutDataModel> checkoutRecordsData = FXCollections.observableArrayList();

	@FXML
	private TableView<CheckoutDataModel> tblCheckoutRecords;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmISBN;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmBookTitle;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmMemberID;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmMemberName;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmOutDate;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmDueDate;
	@FXML
	private TableColumn<CheckoutDataModel, String> clmStatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		clmISBN.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("isbn"));
		clmBookTitle.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("bookTitle"));
		clmMemberID.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("memberId"));
		clmMemberName.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("memberName"));
		clmOutDate.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("checkoutDate"));
		clmDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("dueDate"));
		clmStatus.setCellValueFactory(new PropertyValueFactory<CheckoutDataModel, String>("satus"));

		this.getAllCheckoutRecords();
		
		TableViewLoad(checkoutRecordsData);

	}

	private void TableViewLoad(ObservableList<CheckoutDataModel> checkoutRecordsData) {

		tblCheckoutRecords.setItems(getCheckoutRecordsData());

	}

	public ObservableList<CheckoutDataModel> getCheckoutRecordsData() {
		return checkoutRecordsData;
	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}

	public void getAllCheckoutRecords() {
		LibrarianInterface c = new LibrarianController();
		List<CheckOutRecord> checkoutRecords = c.getAllCheckOutRecords();
		
		for(CheckOutRecord cr: checkoutRecords){
			checkoutRecordsData.add(new CheckoutDataModel(cr));
		}
	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
	
	

}
