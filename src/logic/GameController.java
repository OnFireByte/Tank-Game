package logic;

import java.util.concurrent.ConcurrentLinkedQueue;

import common.AppConstant;
import common.Direction;
import entity.BreakableWall;
import entity.Bullet;
import entity.PlayerTank;
import entity.Tank;
import entity.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController {
    private static GameController instance;
    private GraphicsContext gc;
    private ConcurrentLinkedQueue<Tank> tanks = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Wall> walls = new ConcurrentLinkedQueue<>();

    private Tank player;
    private static int maxEnemy = 10;

    public ConcurrentLinkedQueue<Wall> getWalls() {
        return walls;
    }

    public static int getMaxEnemy() {
        return maxEnemy;
    }

    public static void setMaxEnemy(int maxEnemy) {
        GameController.maxEnemy = maxEnemy;
    }

    public ConcurrentLinkedQueue<Tank> getTanks() {
        return tanks;
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    public Tank getPlayer() {
        return player;
    }

    public void setPlayer(Tank player) {
        this.player = player;
    }

    private boolean isGameRunning;

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }

    private GameController() {
        isGameRunning = false;
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void nextFrame(long currentNanoTime) {
        if (!isGameRunning) {
            return;
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, AppConstant.APP_WIDTH, AppConstant.APP_HEIGHT);

        GameLogic.attemptSpawnEnemy(currentNanoTime);
        for (Tank tank : tanks) {
            tank.update();
        }

        for (Bullet bullet : bullets) {
            bullet.update();
        }
        for (Wall wall : walls) {
            wall.update();
        }
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    public GraphicsContext getGC() {
        return gc;
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            new Wall(i * 20 + 10, 10, 20);
            new Wall(i * 20 + 10, AppConstant.APP_HEIGHT - 10, 20);
        }
        for (int i = 0; i < 30; i++) {
            new Wall(10, i * 20 + 10, 20);
            new Wall(AppConstant.APP_WIDTH - 10, i * 20 + 10, 20);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                new BreakableWall(160 * j, i * 20 + 80, 20);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                new BreakableWall(160 * j, i * 20 + 500, 20);
            }
        }
        player = new PlayerTank(50, 50, Direction.RIGHT);
        for (

                int i = 0; i < 10; i++) {
            GameLogic.spawnEnemy();
        }
    }
}
