package scenes;

import common.AppConstant;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.GameController;
import logic.InputUtil;

public class MainGameScene extends Scene {

	public MainGameScene() {

		super(new Pane(), AppConstant.APP_WIDTH, AppConstant.APP_HEIGHT);
		Canvas canvas = new Canvas(AppConstant.APP_WIDTH, AppConstant.APP_HEIGHT);
		StackPane root = new StackPane(canvas);
		setRoot(root);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GameController.getInstance().setGC(gc);
		GameController.getInstance().run();

		setOnKeyPressed(e -> {
			InputUtil.setKeyPressed(e.getCode());
		});

		setOnKeyReleased(e -> {
			InputUtil.setKeyReleased(e.getCode());
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				GameController.getInstance().nextFrame(currentNanoTime);
			}
		};
		timer.start();
	}
}
