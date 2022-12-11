package scenes;

import common.Constant;
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
	private static AnimationTimer mainMenuMusic;

	public MainMenuScene() {

		super(new Pane(), Constant.APP_WIDTH, Constant.APP_HEIGHT);

		root = new StackPane(new ImageView(RenderableHolder.mainMenuGif));
		setRoot(root);

		HelpPane help = new HelpPane();
		ButtonPane btn = new ButtonPane(help);

		mainMenuMusic = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (!RenderableHolder.mainMenuMusic.isPlaying()) {
					RenderableHolder.mainMenuMusic.play(0.5);
				}
			}
		};
		mainMenuMusic.start();

		root.getChildren().addAll(btn, help);
	}

	public void startMusic() {
		mainMenuMusic.start();
	}

	public void stopMusic() {
		mainMenuMusic.stop();

	}

}
