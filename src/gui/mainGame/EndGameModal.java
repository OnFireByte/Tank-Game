package gui.mainGame;

import gui.BaseButton;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class EndGameModal extends GridPane {

    public EndGameModal() {
        super();
        setVisible(false);
        setVgap(20);
        setHgap(20);
        setPadding(new Insets(50));
        setAlignment(Pos.CENTER);

        setMaxHeight(400);
        setMaxWidth(800);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, getInsets())));

        Label header = new Label("Game Over...");
        header.setFont(RenderableHolder.getFont(30));
        HBox buttonList = new HBox();
        buttonList.setSpacing(50);
        buttonList.setPadding(new Insets(20));
        BaseButton restartButton = new BaseButton("Restart");
        restartButton.setOnMouseClicked(e -> {
            GameController.getInstance().reset();
            setVisible(false);
        });

        BaseButton exitButton = new BaseButton("Go to Main Menu", 12);
        exitButton.setOnMouseClicked(e -> {
            SceneManager.getInstance().setToMainMenu();
            setVisible(false);
        });

        buttonList.getChildren().addAll(restartButton, exitButton);

        add(header, 0, 0);
        add(buttonList, 0, 1);
        setHalignment(header, HPos.CENTER);
    }

}
