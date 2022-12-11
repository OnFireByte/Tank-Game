package gui.mainGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
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
        this(name, desc, icon, level, maxLevel, 50);
    }

    public UpgradeBox(String name, String desc, Image icon, int level, int maxLevel, int size) {
        super();
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setMaxWidth(100);
        setMaxHeight(250);
        setPrefHeight(275);
        setBackground(new Background(new BackgroundFill(bgColor, null, new Insets(0))));

        setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));

        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(size);
        iconView.setFitHeight(size);

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
            setBackground(new Background(new BackgroundFill(bgHoverColor, null, Insets.EMPTY)));
            setCursor(Cursor.HAND);

        });

        setOnMouseExited(e -> {
            setBackground(new Background(new BackgroundFill(bgColor, null, Insets.EMPTY)));
            setCursor(Cursor.DEFAULT);

        });

    }

    public void update(int level, int maxLevel) {
        levelText.setText(maxLevel == 0 ? "" : "Level " + level + "/" + maxLevel);

    }
}
