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
import javafx.scene.text.TextAlignment;
import sharedObject.RenderableHolder;

public class UpgradeBox extends VBox {

    private Text levelText;

    private Color bgColor = Color.LIGHTGRAY;
    private Color bgHoverColor = Color.GRAY;

    public UpgradeBox(String name, String desc, Image icon, int level, int maxLevel) {
        super();
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setMaxWidth(100);
        setMaxHeight(250);
        setPrefHeight(275);
        setBackground(new Background(new BackgroundFill(bgColor, null, getInsets())));

        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(50);
        iconView.setFitHeight(50);

        Text title = new Text(name);
        title.setFont(RenderableHolder.getFont(18));

        Text descText = new Text(desc);
        descText.setFont(RenderableHolder.getFont(8));
        descText.setWrappingWidth(200);
        descText.setLineSpacing(12);
        descText.setTextAlignment(TextAlignment.CENTER);

        levelText = new Text(maxLevel == 0 ? "" : "Level " + level + "/" + maxLevel);
        levelText.setFont(RenderableHolder.getFont(14));

        getChildren().addAll(iconView, title, levelText, descText);

        setOnMouseEntered(e -> {
            setBackground(new Background(new BackgroundFill(bgHoverColor, null, getInsets())));
        });

        setOnMouseExited(e -> {
            setBackground(new Background(new BackgroundFill(bgColor, null, getInsets())));
        });

    }

    public void update(int level, int maxLevel) {
        levelText.setText(maxLevel == 0 ? "" : "Level " + level + "/" + maxLevel);

    }
}
