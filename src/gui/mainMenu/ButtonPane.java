package gui.mainMenu;

import gui.BaseButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import scenes.MainMenuScene;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class ButtonPane extends VBox {

	private BaseButton startButton;
	private BaseButton helpButton;
	private BaseButton exitButton;

	private HelpPane helpPane;

	public ButtonPane(HelpPane helpPane) {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(30);
		this.setPadding(new Insets(200));

		this.helpPane = helpPane;

		// Name
		Button txt = new Button("Tank Game");
		txt.setFont(RenderableHolder.getFont(20));
		ImageView t = new ImageView(RenderableHolder.txtFrame);
		txt.setBackground(null);
		txt.setPadding(Insets.EMPTY);
		txt.setContentDisplay(ContentDisplay.CENTER);
		txt.setGraphic(t);

		txt.setFont(RenderableHolder.getFont(40));

		// play button
		initializeStartButton();

		// help button
		initializeHelpButton();

		// exit button
		initializeExitButton();

		this.getChildren().addAll(txt, startButton, helpButton, exitButton);
	}

	public void initializeStartButton() {
		startButton = new BaseButton("Play", 190, 50);
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// play audio click
				RenderableHolder.clickSound.play();

				SceneManager.getInstance().setToMainGame();
				GameController.getInstance().setGameRunning(true);
				RenderableHolder.mainMenuMusic.stop();
				MainMenuScene.mainMenuMusic.stop();
			}
		});
	}

	public void initializeHelpButton() {

		helpButton = new BaseButton("Help", 190, 50);
		helpButton.setOnMouseClicked(arg0 -> {
			RenderableHolder.clickSound.play();
			helpPane.showHelpPane();
		});
	}

	public void initializeExitButton() {
		exitButton = new BaseButton("Exit", 190, 50);

		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// play audio click
				RenderableHolder.clickSound.play();
				// exit
				System.exit(0);
			}
		});
	}

	public BaseButton getStartButton() {
		return startButton;
	}

	public BaseButton getHelpButton() {
		return helpButton;
	}

	public BaseButton getExitButton() {
		return exitButton;
	}

}
