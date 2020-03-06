package ui.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowController {

	public static void openWindow(String windowName, ActionEvent event, Class c){
		 try {
	        	Parent root = FXMLLoader.load(c.getResource("/ui/scenebuilder/"+windowName+".fxml"));
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.show();
	            // Hide this current window (if this is what you want)
	            ((Node)(event.getSource())).getScene().getWindow().hide();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
