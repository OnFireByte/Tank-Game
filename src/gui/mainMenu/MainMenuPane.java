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
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class MainMenuPane extends VBox {

	private BaseButton startButton;
	private BaseButton helpButton;
	private BaseButton exitButton;

	private HelpPane helpPane;

	public MainMenuPane(HelpPane helpPane) {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(30);
		this.setPadding(new Insets(200));

		this.helpPane = helpPane;

		// Name
		Button txt = new Button("Tank Game");
		txt.setFont(RenderableHolder.getFont(40));
		ImageView t = new ImageView(RenderableHolder.txtFrame);
		txt.setBackground(null);
		txt.setPadding(Insets.EMPTY);
		txt.setContentDisplay(ContentDisplay.CENTER);
		txt.setGraphic(t);

		// play button
		initializeStartButton();

		// help button
		initializeHelpButton();

		// exit button
		initializeExitButton();

		this.getChildren().addAll(txt, startButton, helpButton, exitButton);
	}

	public BaseButton getExitButton() {
		return exitButton;
	}

	public BaseButton getHelpButton() {
		return helpButton;
	}

	public BaseButton getStartButton() {
		return startButton;
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

	public void initializeHelpButton() {

		helpButton = new BaseButton("Help", 190, 50);
		helpButton.setOnMouseClicked(arg0 -> {
			RenderableHolder.clickSound.play();
			helpPane.showHelpPane();
		});
	}

	public void initializeStartButton() {
		startButton = new BaseButton("Play", 190, 50);
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// play audio click
				RenderableHolder.clickSound.play();

				SceneManager.getInstance().setToMapSelection();
			}
		});
	}

}
