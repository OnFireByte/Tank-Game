
package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;
import javafx.scene.Scene;

public class Main extends Application {

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

	public static void main(String[] args) {
		launch(args);
	}
}
