package ui;

import java.time.LocalDate;
import java.util.List;

import business.Book;
import business.CheckoutEntry;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckoutBookWindow extends Stage implements LibWindow {

	public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	@FXML
	private TextField txtMemberID;
	@FXML
	private TextField txtISBN;
	@FXML
	private Button btnCheckout;
	@FXML
	private Button btnBack;
	@FXML
	private Label lblMessage;
	@FXML
	private TableView<CheckoutEntry> tblCheckoutRecord;
	@FXML
	private TableColumn<CheckoutEntry, String> copyNumberCol;
	@FXML
	private TableColumn<CheckoutEntry, LocalDate> checkoutDateCol;
	@FXML
	private TableColumn<CheckoutEntry, LocalDate> dueDateCol;
	@FXML
	private TableColumn<CheckoutEntry, String> isbnCol;
	@FXML
	private Label lblMemberID;
	@FXML
	private Label lblISBN;

	private LibraryMember member;
	private Book book;

	public void init() {
		try {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getResource("/ui/CheckoutWindow.fxml"));
			Scene scene = new Scene(fxmloader.load());
			scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
			CheckoutBookWindow.INSTANCE.setScene(scene);
			CheckoutBookWindow.INSTANCE.setTitle("Checkout");
			CheckoutBookWindow.INSTANCE.setResizable(false);
			CheckoutBookWindow.INSTANCE.show();

		} catch (Exception ea) {
			ea.printStackTrace();
		}
	}

	public void find(ActionEvent e) {
		try {
			ControllerInterface controller = new SystemController();

			if (txtMemberID.getText().isEmpty() || txtMemberID.getText().length() == 0)

				lblMessage.setText("Enter Member ID");

			else
				member = controller.searchMember(txtMemberID.getText());

			if (!txtISBN.getText().isEmpty() || txtISBN.getText().length() != 0)

				book = controller.searchBook(txtISBN.getText());

			else

				lblMessage.setText("Enter ISBN");

			if (member == null) {
				lblMessage.setText("Member ID doesn't exist");

			} else if (book == null || !book.isAvailable()) {

				lblMessage.setText("The book is not available");

			} else if (member != null && book != null && book.isAvailable()) {

				controller.makeCheckout(member, book);

				lblMemberID.setText("Member ID: " + txtMemberID.getText());
				lblISBN.setText("ISBN: " + txtISBN.getText());
				txtMemberID.setText("");
				txtISBN.setText("");

				List<CheckoutEntry> entries = member.getCheckoutRecord().getEntryList(); // get checkOut record

		
				copyNumberCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("copyNumber"));

				checkoutDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, LocalDate>("checkoutDate"));

				dueDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, LocalDate>("dueDate"));

				isbnCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("ISBN"));

				tblCheckoutRecord.setItems(FXCollections.observableArrayList(entries)); // 14 set Items

			}

		} catch (Exception ex) {
			System.out.println("Error! " + ex.getMessage());
		}

	}

	public void back(ActionEvent e) {
		CheckoutBookWindow.INSTANCE.hide();
	}

}
