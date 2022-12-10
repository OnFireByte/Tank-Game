package logic;

import java.util.concurrent.ConcurrentLinkedQueue;

import common.Constant;
import common.Direction;
import entity.BreakableWall;
import entity.Bullet;
import entity.PlayerTank;
import entity.Upgrader;
import entity.Wall;
import entity.base.Particle;
import entity.base.Tank;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController {
    private static GameController instance;
    private static int maxEnemy = 10;
    private static int playerScore;

    private GraphicsContext gc;

    private ConcurrentLinkedQueue<Tank> tanks = new ConcurrentLinkedQueue<>();

    private ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Wall> walls = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Upgrader> upgraders = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Particle> particles = new ConcurrentLinkedQueue<>();

    private PlayerTank player;

    private boolean isGameRunning;

    private GameController() {
        isGameRunning = false;
        playerScore = 0;
    }

    public static int getMaxEnemy() {
        return maxEnemy;
    }

    public static void setMaxEnemy(int maxEnemy) {
        GameController.maxEnemy = maxEnemy;
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        if (playerScore < 0) {
            playerScore = 0;
        }

        GameController.playerScore = playerScore;

    }

    public static void addPlayerScore(int score) {
        setPlayerScore(getPlayerScore() + score);
    }

    public ConcurrentLinkedQueue<Wall> getWalls() {
        return walls;
    }

    public ConcurrentLinkedQueue<Tank> getTanks() {
        return tanks;
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    public ConcurrentLinkedQueue<Upgrader> getUpgraders() {
        return upgraders;
    }

    public ConcurrentLinkedQueue<Particle> getParticles() {
        return particles;
    }

    public PlayerTank getPlayer() {
        return player;
    }

    public void setPlayer(PlayerTank player) {
        this.player = player;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void toggleGameRunning() {
        isGameRunning = !isGameRunning;
    }

    public void setGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }

    public void nextFrame(long currentNanoTime) {
        if (!isGameRunning) {
            return;
        }
        // Clear screen for redrawing
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        GameLogic.attemptSpawnEnemy(currentNanoTime);

        for (Upgrader upgrader : upgraders) {
            upgrader.update();
        }
        for (Tank tank : tanks) {
            tank.update();
        }
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        for (Wall wall : walls) {
            wall.update();
        }
        for (Particle particle : particles) {
            particle.update();
        }
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    public GraphicsContext getGC() {
        return gc;
    }

    public void reset() {
        tanks.clear();
        bullets.clear();
        walls.clear();
        playerScore = 0;
        initialize();
    }

    public void initialize() {
        // Create border walls
        for (int i = 0; i < 40; i++) {
            new Wall(i * 25 + 10, 10, 25);
            new Wall(i * 25 + 10, Constant.GAME_HEIGHT - 10, 25);
        }
        for (int i = 0; i < 24; i++) {
            new Wall(10, i * 25 + 10, 25);
            new Wall(Constant.GAME_WIDTH - 10, i * 25 + 10, 25);
        }

        // Create breakable walls
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                new BreakableWall(160 * j, i * 25 + 80, 25);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 10; j++) {
                new BreakableWall(160 * j, i * 25 + 400, 25);
            }
        }

        // Populate player and enemies
        player = new PlayerTank(50, 50, Direction.RIGHT);
        for (int i = 0; i < 5; i++) {
            GameLogic.spawnEnemy();
        }
    }
}
