package gui.mainGame;

import java.lang.System.Logger.Level;

import common.Updatable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class UpgradeBox extends VBox {

    private Text levelText;

    public UpgradeBox(String name, Image icon, int level, int maxLevel) {
        super();
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setMaxWidth(100);
        setBackground(new Background(new BackgroundFill(Color.GRAY, null, getInsets())));

        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(75);
        iconView.setFitHeight(75);

        Text title = new Text(name);
        title.setFont(RenderableHolder.getFont(18));

        levelText = new Text("Level " + level + "/" + maxLevel);
        levelText.setFont(RenderableHolder.getFont(18));

        getChildren().addAll(iconView, title, levelText);
    }

    public void update(int level, int maxLevel) {
        levelText.setText("Level " + level + "/" + maxLevel);

    }
}
