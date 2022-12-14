
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setOnCloseRequest((WindowEvent e) -> {
			System.exit(0);
		});

		SceneManager.injectStage(primaryStage);

		Scene scene = SceneManager.getInstance().getMainMenuScene();

		primaryStage.setScene(scene);
		primaryStage.setTitle("NoProgOnlyMeth");
		primaryStage.getIcons().add(RenderableHolder.iconGif);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
