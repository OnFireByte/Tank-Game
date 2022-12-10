package gui.mainGame;

import common.Constant;
import common.Updatable;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.RenderableHolder;

public class UpgradeModal extends HBox implements Updatable {
    UpgradeBox sizeBox;
    UpgradeBox speedBox;
    UpgradeBox hpBox;
    UpgradeBox fireRateBox;

    public UpgradeModal() {
        super();
        // setVisible(true);
        sizeBox = new UpgradeBox("Size", RenderableHolder.Tank2,
                GameController.getInstance().getPlayer().getSizeLevel(), Constant.MAX_UPGRADE_LEVEL);

        speedBox = new UpgradeBox("Speed", RenderableHolder.Tank2,
                GameController.getInstance().getPlayer().getSpeedLevel(), Constant.MAX_UPGRADE_LEVEL);
        hpBox = new UpgradeBox("HP", RenderableHolder.Tank2,
                GameController.getInstance().getPlayer().getMaxHpLevel(), Constant.MAX_UPGRADE_LEVEL);
        fireRateBox = new UpgradeBox("Fire Rate", RenderableHolder.Tank2,
                GameController.getInstance().getPlayer().getShootCoolDownLevel(), Constant.MAX_UPGRADE_LEVEL);

        setBackground(new Background(new BackgroundFill(Color.GRAY, null, getInsets())));
        setMaxHeight(400);
        setMaxWidth(500);

        sizeBox.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().addSizeLevel(1);
        });

        speedBox.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().addSpeedLevel(1);
        });

        hpBox.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().addMaxHpLevel(1);
        });

        fireRateBox.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().addShootCoolDownLevel(1);
        });

        getChildren().addAll(sizeBox, speedBox, hpBox, fireRateBox);
    }

    @Override
    public void update() {
        sizeBox.update(GameController.getInstance().getPlayer().getSizeLevel(), Constant.MAX_UPGRADE_LEVEL);
        speedBox.update(GameController.getInstance().getPlayer().getSpeedLevel(), Constant.MAX_UPGRADE_LEVEL);
        hpBox.update(GameController.getInstance().getPlayer().getMaxHpLevel(), Constant.MAX_UPGRADE_LEVEL);
        fireRateBox.update(GameController.getInstance().getPlayer().getShootCoolDownLevel(),
                Constant.MAX_UPGRADE_LEVEL);

    }
}
