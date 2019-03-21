package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
		Parent root = loader.load();
		
		PacManController pc = loader.getController();
		pc.setStage(stage);
		
       	Scene scene= new Scene(root);
       	stage.setTitle("PacMan");
       	stage.setScene(scene);
       	stage.show();
		
	}
}
