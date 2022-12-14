package scenes;

import common.Constant;
import gui.mainMenu.HelpPane;
import gui.mainMenu.MainMenuPane;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class MainMenuScene extends Scene {

	private static AnimationTimer mainMenuMusic;
	private StackPane root;

	public MainMenuScene() {

		super(new Pane(), Constant.APP_WIDTH, Constant.APP_HEIGHT);

		root = new StackPane(new ImageView(RenderableHolder.mainMenuGif));
		setRoot(root);

		HelpPane help = new HelpPane();
		MainMenuPane btn = new MainMenuPane(help);

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
