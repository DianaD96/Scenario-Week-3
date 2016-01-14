package application;
	
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		try {
			
			Scene scene = new Scene(root,640,480);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		root.setCenter(new RootLayout());
	}
	

	public static void main(String[] args) {
		//Simulation sim = new Simulation();
		//sim.run();
		
  //          	Circuit series = new Circuit();
//				series.run(NodeLink.cell);
            	//System.out.println(NodeLink.cell.getClass());
;
		
		launch(args);
		System.out.println("test");
	}

}
