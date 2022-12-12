package gui.mainGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sharedObject.RenderableHolder;

public class UpgradeBox extends VBox {

    private Text levelText;

    public UpgradeBox(String name, String desc, Image icon, int level, int maxLevel) {
        this(name, desc, icon, level, maxLevel, 50);
    }

    public UpgradeBox(String name, String desc, Image icon, int level, int maxLevel, int size) {
        super();
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setSpacing(15);
        setMaxWidth(220);
        setMaxHeight(200);
        setPrefHeight(200);

        setBackground(new Background(new BackgroundImage(RenderableHolder.box, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(220, 180, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));

        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(size);
        iconView.setFitHeight(size);

        Text title = new Text(name);
        title.setFont(RenderableHolder.getFont(14));

        levelText = new Text(maxLevel == 0 ? "" : "Level " + level + "/" + maxLevel);
        levelText.setFont(RenderableHolder.getFont(10));

        Text descText = new Text(desc);
        descText.setFont(RenderableHolder.getFont(8));
        descText.setWrappingWidth(200);
        descText.setLineSpacing(12);
        descText.setTextAlignment(TextAlignment.CENTER);

        getChildren().addAll(iconView, title, levelText, descText);

        setOnMouseEntered(e -> {
            setBackground(new Background(new BackgroundImage(RenderableHolder.hoveredBox, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(220, 180, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));
            setCursor(Cursor.HAND);

        });

        setOnMouseExited(e -> {
            setBackground(new Background(new BackgroundImage(RenderableHolder.box, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(220, 180, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));
            setCursor(Cursor.DEFAULT);

        });

    }

    public void update(int level, int maxLevel) {
        levelText.setText(maxLevel == 0 ? "" : "Level " + level + "/" + maxLevel);

    }
}
