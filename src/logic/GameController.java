package logic;

import java.util.concurrent.ConcurrentLinkedQueue;

import common.Constant;
import entity.Bullet;
import entity.PlayerTank;
import entity.Upgrader;
import entity.Wall;
import entity.base.Particle;
import entity.base.Tank;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sharedObject.RenderableHolder;

public class GameController {
    private static GameController instance;
    private static int maxEnemy;
    private static int playerScore;
    private static boolean isGameOver;
    public static void addPlayerScore(int score) {
        setPlayerScore(getPlayerScore() + score);
    }
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public static int getMaxEnemy() {
        return maxEnemy;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static boolean isGameOver() {
        return isGameOver;
    }
    public static void setGameOver(boolean isGameOver) {
        GameController.isGameOver = isGameOver;
    }
    public static void setMaxEnemy(int maxEnemy) {
        GameController.maxEnemy = maxEnemy;
    }
    public static void setPlayerScore(int playerScore) {
        if (playerScore < 0) {
            playerScore = 0;
        }

        GameController.playerScore = playerScore;

    }

    private int currentMapId;

    private long timeFrame;

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

    public void drawFrame() {
        if (!isGameRunning) {
            return;
        }
        // Clear screen for redrawing
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        for (Upgrader upgrader : upgraders) {
            upgrader.draw(getGC());
        }
        for (Tank tank : tanks) {
            tank.draw(getGC());
        }
        for (Bullet bullet : bullets) {
            bullet.draw(getGC());
        }
        for (Wall wall : walls) {
            wall.draw(getGC());
        }
        for (Particle particle : particles) {
            particle.draw(getGC());
        }
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    public int getCurrentMapId() {
        return currentMapId;
    }

    public GraphicsContext getGC() {
        return gc;
    }

    public ConcurrentLinkedQueue<Particle> getParticles() {
        return particles;
    }

    public PlayerTank getPlayer() {
        return player;
    }

    public ConcurrentLinkedQueue<Tank> getTanks() {
        return tanks;
    }

    public double getTimeFrame() {
        return timeFrame;
    }

    public ConcurrentLinkedQueue<Upgrader> getUpgraders() {
        return upgraders;
    }

    public ConcurrentLinkedQueue<Wall> getWalls() {
        return walls;
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

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void nextFrame() {
        if (!isGameRunning) {
            return;
        }
        timeFrame++;

        int playerLevel = player.getSizeLevel() + player.getMaxHpLevel()
                + player.getSpeedLevel() + player.getShootCoolDownLevel();

        maxEnemy = playerLevel / 3 + 5;

        GameUtil.attemptSpawnEnemy(timeFrame);

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
        Platform.runLater(() -> {
            drawFrame();
        });
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
        RenderableHolder.mainGameMusic.seek(Duration.ZERO);
    }

    public void setCurrentMapId(int currentMapId) {
        this.currentMapId = currentMapId;
    }

    public void setGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setPlayer(PlayerTank player) {
        this.player = player;
    }

    public void toggleGameRunning() {
        isGameRunning = !isGameRunning;
    }

}
