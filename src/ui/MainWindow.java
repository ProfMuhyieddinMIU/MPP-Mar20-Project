package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application{

	public static void main(String[] args)
	{
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/ui/scenebuilder/MainWindow.fxml"));
 
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            
            
         
        } catch(Exception e) {
            e.printStackTrace();
        }
		
	}

}
