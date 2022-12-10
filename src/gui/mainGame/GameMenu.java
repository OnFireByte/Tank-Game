package gui.mainGame;

import common.Constant;
import common.Updatable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class GameMenu extends GridPane implements Updatable {

    private Text hp;
    private Text score;

    public GameMenu() {
        super();

        Font font = RenderableHolder.getFont(20);
        setPrefSize(Constant.GAME_WIDTH, Constant.APP_HEIGHT - Constant.GAME_HEIGHT);
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> {
            GameController.getInstance().toggleGameRunning();
            this.requestFocus();

        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            SceneManager.getInstance().setToMainMenu();
            this.requestFocus();

        });

        hp = new Text("HP : 0/0");
        hp.setFont(font);

        score = new Text("Score : 0");
        score.setFont(font);
        add(hp, 0, 0);
        add(score, 0, 1);
        // add(backButton, 2, 0);
        // add(pauseButton, 2, 1);
    }

    @Override
    public void update() {
        hp.setText("HP : " + GameController.getInstance().getPlayer().getHp() + "/"
                + GameController.getInstance().getPlayer().getMaxHp());

        score.setText("Score : " + GameController.getPlayerScore());
    }
}
