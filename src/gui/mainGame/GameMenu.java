package gui.mainGame;

import common.Constant;
import common.Updatable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.RenderableHolder;

public class GameMenu extends BorderPane implements Updatable {

    private Text hp;
    private Text score;
    private Label time;

    public GameMenu() {
        super();

        Font font = RenderableHolder.getFont(20);
        setPrefSize(Constant.GAME_WIDTH, Constant.APP_HEIGHT - Constant.GAME_HEIGHT);
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, new Insets(0))));

        hp = new Text("HP : 0/0");
        hp.setFont(font);
        hp.setFill(Color.WHITE);

        score = new Text("Score : 0");
        score.setFont(font);
        score.setFill(Color.WHITE);

        time = new Label("00:00");
        time.setFont(RenderableHolder.getFont(40));
        time.setTextFill(Color.WHITE);

        VBox left = new VBox();
        left.getChildren().addAll(hp, score);
        left.setSpacing(20);

        VBox right = new VBox(time);

        // getChildren().addAll(left, right);
        setLeft(left);
        setRight(right);
        setAlignment(left, Pos.CENTER_LEFT);
        setAlignment(right, Pos.CENTER_RIGHT);
    }

    @Override
    public void update() {
        hp.setText("HP : " + GameController.getInstance().getPlayer().getHp() + "/"
                + GameController.getInstance().getPlayer().getMaxHp());

        score.setText("Score : " + GameController.getPlayerScore());
        int min = (int) (GameController.getInstance().getTimeFrame() / 3600);
        int sec = (int) (GameController.getInstance().getTimeFrame() / 60) % 60;
        String timeFormat = String.format("%02d:%02d", min, sec);
        time.setText(timeFormat);
    }
}
