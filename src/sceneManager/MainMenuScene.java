package sceneManager;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class MainMenuScene {
	
	
	//set ฉาก เอา paneทั้งหมดมาลง ButtonPane HelpPane
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	
	
	public MainMenuScene(Stage primaryStage) {
		
		StackPane root = new StackPane();
		Scene scene = new Scene(root , WIDTH , HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("eiei");
		primaryStage.setResizable(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

}
