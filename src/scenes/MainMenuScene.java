package scenes;

import common.AppConstant;
import gui.mainMenu.ButtonPane;
import gui.mainMenu.HelpPane;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class MainMenuScene extends Scene {

	private StackPane root;

	public MainMenuScene() {

		super(new Pane(), AppConstant.APP_WIDTH, AppConstant.APP_HEIGHT);
		
		root = new StackPane(new ImageView(RenderableHolder.mainMenuGif));
		setRoot(root);

		HelpPane help = new HelpPane();
		ButtonPane btn = new ButtonPane(help);

		AnimationTimer music = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (!RenderableHolder.mainMenuMusic.isPlaying()) {
					// RenderableHolder.mainMenuMusic.play();
				}
			}
		};

		music.start();

		root.getChildren().addAll(btn, help);
	}

}
