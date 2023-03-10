package gui.mainGame;

import common.Constant;
import common.Updatable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import logic.GameController;
import sharedObject.RenderableHolder;

public class UpgradeModal extends GridPane implements Updatable {
    UpgradeBox sizeBox;
    UpgradeBox speedBox;
    UpgradeBox hpBox;
    UpgradeBox fireRateBox;
    UpgradeBox healBox;

    public UpgradeModal() {
        super();
        setVisible(false);
        sizeBox = new UpgradeBox("Size", "Make your tank smaller, easier to dodge. Less is more_",
                RenderableHolder.sizeUpgrade,
                GameController.getInstance().getPlayer().getSizeLevel(), Constant.MAX_UPGRADE_LEVEL);

        speedBox = new UpgradeBox("Speed", "Make your tank faster, tank goes whoop whoop!",
                RenderableHolder.speedUpgrade,
                GameController.getInstance().getPlayer().getSpeedLevel(), Constant.MAX_UPGRADE_LEVEL);
        hpBox = new UpgradeBox("HP", "Increase tank's health, stay healthy!", RenderableHolder.maxHpUpgrade,
                GameController.getInstance().getPlayer().getMaxHpLevel(), Constant.MAX_UPGRADE_LEVEL);
        fireRateBox = new UpgradeBox("Fire Rate",
                "Increase tank's fire rate speed. Pew pew",
                RenderableHolder.fireRateUpgrade,
                GameController.getInstance().getPlayer().getShootCoolDownLevel(), Constant.MAX_UPGRADE_LEVEL);

        healBox = new UpgradeBox("Heal", "Heal your tank, get back to the battlefield!", RenderableHolder.heal, 0, 0);

        setMaxHeight(550);
        setMaxWidth(800);
        setPadding(new Insets(80));
        setVgap(5);
        setHgap(5);
        setBackground(new Background(new BackgroundImage(RenderableHolder.modalBackGround, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(800, 500, isFocusTraversable(), isDisabled(), isDisable(), isCache()))));

        sizeBox.setOnMouseClicked(e -> {
            if (GameController.getInstance().getPlayer().getSizeLevel() == Constant.MAX_UPGRADE_LEVEL) {
                return;
            }
            GameController.getInstance().getPlayer().addSizeLevel(1);
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        speedBox.setOnMouseClicked(e -> {
            if (GameController.getInstance().getPlayer().getSpeedLevel() == Constant.MAX_UPGRADE_LEVEL) {
                return;
            }
            GameController.getInstance().getPlayer().addSpeedLevel(1);
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        hpBox.setOnMouseClicked(e -> {
            if (GameController.getInstance().getPlayer().getMaxHpLevel() == Constant.MAX_UPGRADE_LEVEL) {
                return;
            }
            GameController.getInstance().getPlayer().addMaxHpLevel(1);
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        fireRateBox.setOnMouseClicked(e -> {
            if (GameController.getInstance().getPlayer().getShootCoolDownLevel() == Constant.MAX_UPGRADE_LEVEL) {
                return;
            }
            GameController.getInstance().getPlayer().addShootCoolDownLevel(1);
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        healBox.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().heal();
            setVisible(false);
            GameController.getInstance().setGameRunning(true);
        });

        add(fireRateBox, 0, 0);
        add(sizeBox, 1, 0);
        add(speedBox, 2, 0);
        add(hpBox, 0, 1);
        add(healBox, 1, 1);
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
