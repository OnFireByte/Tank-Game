package logic;

import java.util.concurrent.ConcurrentLinkedQueue;

import common.Constant;
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
    private static int maxEnemy;
    private static int playerScore;
    private static boolean isGameOver;
    private int currentMapId;
    private double timeFrame;

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
        maxEnemy = 0;
        playerScore = 0;
        isGameOver = true;
        timeFrame = 0;
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
        timeFrame++;

        int playerLevel = player.getSizeLevel() + player.getMaxHpLevel()
                + player.getSpeedLevel() + player.getShootCoolDownLevel();

        maxEnemy = playerLevel / 3 + 5;

        // Clear screen for redrawing
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        GameUtil.attemptSpawnEnemy(currentNanoTime);

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
        particles.clear();
        upgraders.clear();
        playerScore = 0;
        timeFrame = 0;
        initialize();
    }

    public void initialize() {
        // Create border walls
        isGameOver = false;
        for (int i = 0; i < 24; i++) {
            new Wall(12.5f, i * 25 + 12.5f, 25);
            new Wall(Constant.GAME_WIDTH - 12.5f, i * 25 + 12.5f, 25);
        }
        for (int i = 0; i < 40; i++) {
            new Wall(i * 25 + 12.5f, 12.5f, 25);
            new Wall(i * 25 + 12.5f, Constant.GAME_HEIGHT - 12.5f, 25);
        }

        // Load map
        GameUtil.mapLoader(currentMapId);

        // Populate player and enemies
        player = GameUtil.spawnPlayerToRandomPos();
        for (int i = 0; i < 5; i++) {
            GameUtil.spawnEnemy();
        }

    }

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void setGameOver(boolean isGameOver) {
        GameController.isGameOver = isGameOver;
    }

    public int getCurrentMapId() {
        return currentMapId;
    }

    public void setCurrentMapId(int currentMapId) {
        this.currentMapId = currentMapId;
    }

    public double getTimeFrame() {
        return timeFrame;
    }

}
