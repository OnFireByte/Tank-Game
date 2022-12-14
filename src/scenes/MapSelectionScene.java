package scenes;

import common.Constant;
import gui.BaseButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class MapSelectionScene extends Scene {
    private StackPane root;

    public MapSelectionScene() {
        super(new Pane(), Constant.APP_WIDTH, Constant.APP_HEIGHT);

        root = new StackPane(new ImageView(RenderableHolder.mainMenuGif));
        setRoot(root);
        HBox maps = new HBox();
        maps.setSpacing(20);
        maps.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            final int j = i;
            ImageView mv = new ImageView(RenderableHolder.getMapPreview(i + 1));
            mv.setFitWidth(100 * 3);
            mv.setFitHeight(60 * 3);
            mv.setOnMouseClicked(e -> {
                GameController.getInstance().setCurrentMapId(j + 1);
                SceneManager.getInstance().setToMainGame();
                RenderableHolder.mainMenuMusic.stop();
            });
            mv.setOnMouseEntered(e -> setCursor(Cursor.HAND));
            mv.setOnMouseExited(e -> setCursor(Cursor.DEFAULT));
            maps.getChildren().addAll(mv);
        }
       VBox all = new VBox();
       all.setAlignment(Pos.TOP_CENTER);
       all.setPadding(new Insets(40));
       all.setSpacing(130);

       Button txt = new Button("Map Select");
       txt.setFont(RenderableHolder.getFont(40));
       ImageView t = new ImageView(RenderableHolder.txtFrame);
       txt.setBackground(null);
       txt.setPadding(Insets.EMPTY);
       txt.setContentDisplay(ContentDisplay.CENTER);
       txt.setGraphic(t);

       BaseButton back = new BaseButton("Back");
       back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				RenderableHolder.clickSound.play();
				SceneManager.getInstance().setToMainMenu();
			}
		});
       all.getChildren().addAll(txt , maps , back);

       root.getChildren().addAll(all);
    }

}
