package gui.mainGame;

import gui.BaseButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class PauseModal extends VBox {

    public PauseModal() {
        super();
        setVisible(false);

        setAlignment(Pos.CENTER);

        setMaxHeight(400);
        setMaxWidth(400);
        setPadding(new Insets(20));
        setSpacing(50);

        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, getInsets())));

        Label header = new Label("Pause...");
        header.setFont(RenderableHolder.getFont(30));

        BaseButton resumeButton = new BaseButton("Resume");
        resumeButton.setOnMouseClicked(e -> {
        	RenderableHolder.clickSound.play();
            GameController.getInstance().setGameRunning(true);
            setVisible(false);
        });

        BaseButton backButton = new BaseButton("Back to Main Menu", 300, 50, 15);
        backButton.setOnMouseClicked(e -> {
        	RenderableHolder.clickSound.play();
            SceneManager.getInstance().setToMainMenu();
            setVisible(false);
        });

        getChildren().addAll(header, resumeButton, backButton);

    }

}
