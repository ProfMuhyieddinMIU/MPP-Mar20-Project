package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Book;
import business.LibraryMember;
import business.LoginException;
import business.controllers.impl.LibrarianController;
import business.controllers.impl.SystemController;
import business.controllers.interfaces.ControllerInterface;
import business.controllers.interfaces.LibrarianInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.dataModel.BookDataModel;
import ui.dataModel.MemberDataModel;

public class ListMembersWindowController implements Initializable {
	
	@FXML
	private Button backButton;

	@FXML
	private MenuItem exitMenuItem;

	ObservableList<MemberDataModel> membersData = FXCollections.observableArrayList();

	@FXML
	private TableView<MemberDataModel> tblMembers;
	@FXML
	private TableColumn<MemberDataModel, String> clmID;
	@FXML
	private TableColumn<MemberDataModel, String> clmFirstName;
	@FXML
	private TableColumn<MemberDataModel, String> clmLastName;
	@FXML
	private TableColumn<MemberDataModel, String> clmEmail;
	@FXML
	private TableColumn<MemberDataModel, String> clmTelephone;
	@FXML
	private TableColumn<MemberDataModel, String> clmAddress;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clmID.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("memberId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("lastName"));
		clmEmail.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("email"));
		clmTelephone.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("telephone"));
		clmAddress.setCellValueFactory(new PropertyValueFactory<MemberDataModel, String>("address"));

		this.getAllMembers();

		TableViewLoad(membersData);

	}

	private void TableViewLoad(ObservableList<MemberDataModel> membersData) {

		tblMembers.setItems(getMembersData());
		
		tblMembers.setRowFactory(tv -> {
			TableRow<MemberDataModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					MemberDataModel rowData = row.getItem();
					
					LibrarianController c = new LibrarianController();
					DetailsMemberWindowController.member = c.getMemberById(rowData.getMemberId());
					
					WindowController.openPopup("DetailsMemberWindow", this.getClass());
					
				}
			});
			return row;
		});

	}
	
	public void logout(ActionEvent event) {
		SystemController.currentLoggedInUser = null ;
		WindowController.openPopus("MainWindow", event, this.getClass());
	}

	public ObservableList<MemberDataModel> getMembersData() {
		return membersData;
	}

	public void getAllMembers() {
		LibrarianInterface c = new LibrarianController();
		List<LibraryMember> members = c.getAllMembers();

		for (LibraryMember m : members) {
			membersData.add(new MemberDataModel(m));
		}
	}

	public void exitApplication(ActionEvent event) {

		System.exit(0);

	}
	
	public void back(ActionEvent event) {

		WindowController.openWindow("AdminWindow", event, this.getClass());

	}
}
