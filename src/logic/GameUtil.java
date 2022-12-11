package logic;

import java.util.concurrent.ThreadLocalRandom;

import common.Constant;
import common.Direction;
import entity.BotTank;
import entity.BreakableWall;
import entity.Wall;
import entity.base.BaseEntity;
import entity.base.Tank;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class GameUtil {
    public static boolean isCollided(BaseEntity a, BaseEntity b) {
        if (a instanceof BreakableWall && ((BreakableWall) a).isBroken()) {
            return false;
        }
        if (b instanceof BreakableWall && ((BreakableWall) b).isBroken()) {
            return false;
        }
        return (a.getX() - a.getWidth() / 2) < (b.getX() + b.getWidth() / 2)
                && (a.getX() + a.getWidth() / 2) > (b.getX() - b.getWidth() / 2)
                && (a.getY() - a.getHeight() / 2) < (b.getY() + b.getHeight() / 2)
                && (a.getY() + a.getHeight() / 2) > (b.getY() - b.getHeight() / 2);
    }

    public static void spawnEnemy() {
        var a = new BotTank(ThreadLocalRandom.current().nextInt(0, Constant.GAME_WIDTH),
                ThreadLocalRandom.current().nextInt(41, Constant.GAME_HEIGHT - 40), (float) 1, Direction.LEFT);
        while (true) {
            boolean isCollided = false;
            for (Tank tank : GameController.getInstance().getTanks()) {
                if (tank != a && GameUtil.isCollided(tank, a)) {
                    a.setX(ThreadLocalRandom.current().nextInt(0, Constant.GAME_WIDTH));
                    a.setY(ThreadLocalRandom.current().nextInt(0, Constant.GAME_HEIGHT));
                    isCollided = true;
                    break;
                }
            }
            for (Wall wall : GameController.getInstance().getWalls()) {
                if (GameUtil.isCollided(wall, a)) {
                    a.setX(ThreadLocalRandom.current().nextInt(0, Constant.GAME_WIDTH));
                    a.setY(ThreadLocalRandom.current().nextInt(0, Constant.GAME_HEIGHT));
                    isCollided = true;
                    break;
                }
            }
            if (!isCollided) {
                break;
            }
        }

    }

    public static void attemptSpawnEnemy(long currentNanoTime) {
        if (currentNanoTime % 60 != 0) {
            return;
        }
        if (GameController.getInstance().getTanks().size() - 1 < GameController.getMaxEnemy()
                && ThreadLocalRandom.current().nextInt(0, 2) == 0) {
            spawnEnemy();
        }
    }

    public static void mapLoader() {
        // load map from image file
        PixelReader pr = RenderableHolder.map.getPixelReader();
        for (int i = 0; i < RenderableHolder.map.getWidth(); i++) {
            for (int j = 0; j < RenderableHolder.map.getHeight(); j++) {
                if (toHex(pr.getColor(i, j)).equals("#CCCCCC")) {
                    new BreakableWall(i * 25 + 37.5f, j * 25 + 37.5f, 25);
                }
            }
        }

    }

    public static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
