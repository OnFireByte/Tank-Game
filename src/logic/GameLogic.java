package logic;

import java.util.concurrent.ThreadLocalRandom;

import common.AppConstant;
import common.Direction;
import entity.BotTank;
import entity.BreakableWall;
import entity.Tank;
import entity.Wall;
import entity.base.BaseEntity;

public class GameLogic {
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
        var a = new BotTank(ThreadLocalRandom.current().nextInt(0, AppConstant.APP_WIDTH),
                ThreadLocalRandom.current().nextInt(41, AppConstant.APP_HEIGHT - 40), (float) 1, Direction.LEFT);
        while (true) {
            boolean isCollided = false;
            for (Tank tank : GameController.getInstance().getTanks()) {
                if (tank != a && GameLogic.isCollided(tank, a)) {
                    a.setX(ThreadLocalRandom.current().nextInt(0, AppConstant.APP_WIDTH));
                    a.setY(ThreadLocalRandom.current().nextInt(0, AppConstant.APP_HEIGHT));
                    isCollided = true;
                    break;
                }
            }
            for (Wall wall : GameController.getInstance().getWalls()) {
                if (GameLogic.isCollided(wall, a)) {
                    a.setX(ThreadLocalRandom.current().nextInt(0, AppConstant.APP_WIDTH));
                    a.setY(ThreadLocalRandom.current().nextInt(0, AppConstant.APP_HEIGHT));
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
}
