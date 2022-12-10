package scenes;

import common.Constant;
import gui.mainGame.EndGameModal;
import gui.mainGame.GameMenu;
import gui.mainGame.UpgradeModal;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.InputUtil;

public class MainGameScene extends Scene {
	boolean isEscHold;
	UpgradeModal upgradeModal;
	EndGameModal endGameModal;

	public MainGameScene() {

		super(new Pane(), Constant.APP_WIDTH, Constant.APP_HEIGHT);
		Canvas canvas = new Canvas(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		GameMenu gameMenu = new GameMenu();
		StackPane gamePane = new StackPane(canvas);
		VBox root = new VBox();
		root.getChildren().addAll(gameMenu, gamePane);
		gamePane.setPrefSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		setRoot(root);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GameController.getInstance().setGC(gc);
		GameController.getInstance().initialize();

		endGameModal = new EndGameModal();
		upgradeModal = new UpgradeModal();
		gamePane.getChildren().addAll(upgradeModal, endGameModal);

		isEscHold = false;
		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE && !isEscHold) {
				GameController.getInstance().toggleGameRunning();
				isEscHold = true;
			}
			InputUtil.setKeyPressed(e.getCode());
		});

		setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				isEscHold = false;
			}
			InputUtil.setKeyReleased(e.getCode());
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				GameController.getInstance().nextFrame(currentNanoTime);
				gameMenu.update();
				upgradeModal.update();
			}
		};
		timer.start();
	}

	public void openUpgradeModal() {
		upgradeModal.setVisible(true);
		GameController.getInstance().setGameRunning(false);

	}

	public void closeUpgradeModal() {
		upgradeModal.setVisible(false);
		GameController.getInstance().setGameRunning(true);
	}

	public void openEndGameModal() {
		endGameModal.setVisible(true);
		GameController.getInstance().setGameRunning(false);
	}

}
