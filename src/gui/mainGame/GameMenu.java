package gui.mainGame;

import common.Constant;
import common.Updatable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.RenderableHolder;

public class GameMenu extends GridPane implements Updatable {

    private Text hp;
    private Text score;

    public GameMenu() {
        super();

        Font font = RenderableHolder.getFont(20);
        setPrefSize(Constant.GAME_WIDTH, Constant.APP_HEIGHT - Constant.GAME_HEIGHT);
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, new Insets(0))));

        hp = new Text("HP : 0/0");
        hp.setFont(font);
        hp.setFill(Color.WHITE);

        score = new Text("Score : 0");
        score.setFont(font);
        score.setFill(Color.WHITE);

        add(hp, 0, 0);
        add(score, 0, 1);
        setValignment(hp, VPos.CENTER);
        setValignment(score, VPos.CENTER);
    }

    @Override
    public void update() {
        hp.setText("HP : " + GameController.getInstance().getPlayer().getHp() + "/"
                + GameController.getInstance().getPlayer().getMaxHp());

        score.setText("Score : " + GameController.getPlayerScore());
    }
}
