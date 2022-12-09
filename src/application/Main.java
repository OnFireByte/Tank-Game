
package application;
	


import gui.ButtonPane;
import gui.HelpPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		
		StackPane root = new StackPane(new ImageView(RenderableHolder.mainMenuGif));
		
		
		//-------------------------เดียวเอาไปใส่แยกใน main menu scene-------------------
		HelpPane help = new HelpPane();
		ButtonPane btn = new ButtonPane();
		btn.btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				RenderableHolder.clickSound.play();
				help.showHelpPane();
			}
		});
		//------------------------------------------------------------------------
		AnimationTimer music = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (!RenderableHolder.mainMenuMusic.isPlaying())
					RenderableHolder.mainMenuMusic.play();
			}
		};
		music.start();
		//--------------------------เดียวเอาไปใส่แยกใน main menu scene------------------------
		
		
		
		root.getChildren().addAll(btn,help);
		Scene scene = new Scene(root, 1000, 600);
		
		primaryStage.setScene(scene); 
		primaryStage.setTitle("NoProgOnlyMeth"); 
		primaryStage.getIcons().add(RenderableHolder.iconGif);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
