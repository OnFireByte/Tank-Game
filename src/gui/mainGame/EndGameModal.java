package gui.mainGame;

import gui.BaseButton;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

        setMaxHeight(300);
        setMaxWidth(700);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, new Insets(0))));
        setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));

        Label header = new Label("Game Over...");
        header.setFont(RenderableHolder.getFont(30));
        HBox buttonList = new HBox();
        buttonList.setSpacing(50);
        buttonList.setPadding(new Insets(20));
        BaseButton restartButton = new BaseButton("Restart");
        restartButton.setOnMouseClicked(e -> {
            GameController.getInstance().reset();
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        BaseButton exitButton = new BaseButton("Go to Main Menu", 300, 50, 15);
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
