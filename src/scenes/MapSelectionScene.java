package scenes;

import common.Constant;
import gui.BaseButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
        VBox maps = new VBox();
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

        root.getChildren().addAll(maps);
    }

}
