package gui.mainMenu;

import gui.BaseButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
		Text txt = new Text("Tank Game");
		txt.setFont(RenderableHolder.buttonFont);
		txt.setTabSize(50);

		// play button
		initializeStartButton();

		// help button
		initializeHelpButton();

		// exit button
		initializeExitButton();

		this.getChildren().addAll(txt, startButton, helpButton, exitButton);
	}

	public void initializeStartButton() {
		startButton = new BaseButton("Play");
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
		helpButton = new BaseButton("Help");
		helpButton.setOnMouseClicked(arg0 -> {
			RenderableHolder.clickSound.play();
			helpPane.showHelpPane();
		});
	}

	public void initializeExitButton() {
		exitButton = new BaseButton("Exit");
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
