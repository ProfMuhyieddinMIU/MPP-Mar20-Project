package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainWindowController implements Initializable {

	@FXML
	private MenuItem exitMenuItem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void exitApplication(ActionEvent event) {
		System.out.println("Application Exit!");

		System.exit(0);

	}
}
