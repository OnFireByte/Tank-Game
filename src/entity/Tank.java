package entity;

import common.Direction;
import entity.base.MovableEntity;
import entity.interfaces.Hittable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import logic.GameController;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public abstract class Tank extends MovableEntity implements Hittable {

    protected boolean isPlayerSide;
    protected int shootCoolDown;
    protected int shootCoolDownCounter;
    protected Image sprite;

    public boolean isPlayerSide() {
        return isPlayerSide;
    }

    public Tank(float x, float y, float speed, Direction direction, boolean isPlayerSide) {
        super(40, 40, x, y, 2, direction);
        this.isPlayerSide = isPlayerSide;
        shootCoolDown = 10;
        shootCoolDownCounter = 0;
        this.speed = speed;
        GameController.getInstance().getTanks().add(this);
        sprite = RenderableHolder.Tank1;

    }

    public Tank(float x, float y, boolean isPlayerSide) {

        super(40, 40, x, y, 2, Direction.UP);
        this.isPlayerSide = isPlayerSide;
        shootCoolDown = 30;
        shootCoolDownCounter = 0;
        sprite = RenderableHolder.Tank1;
    }

    @Override
    public void update() {
        if (shootCoolDownCounter > 0) {
            shootCoolDownCounter--;

        }

        if (shootInput()) {
            shoot();
        }
        forward();

        draw(GameController.getInstance().getGC());

    }

    abstract protected boolean shootInput();

    protected void shoot() {
        if (shootCoolDownCounter > 0) {
            return;
        }
        Pair<Float, Float> pair = getFacePos();
        new Bullet(pair.getKey(), pair.getValue(), 10, direction, isPlayerSide);

        shootCoolDownCounter = shootCoolDown;
    }

    @Override
    public void draw(GraphicsContext gc) {
        draw(gc, false);
    }

    public void draw(GraphicsContext gc, boolean isHitted) {
        gc.save();
        gc.translate(x, y);
        switch (direction) {
            case UP:
                gc.rotate(0);
                break;

            case DOWN:
                gc.rotate(180);

                break;
            case LEFT:
                gc.rotate(270);

                break;
            case RIGHT:
                gc.rotate(90);

                break;

            default:
                break;
        }
        gc.drawImage(isHitted ? RenderableHolder.Tank1Hit : sprite, -width / 2, -height / 2, width,
                height);
        gc.restore();

    }

    public Pair<Float, Float> getFacePos() {
        float fx;
        float fy;
        switch (direction) {
            case UP:
                fx = x;
                fy = y - 20;
                break;

            case DOWN:
                fx = x;
                fy = y + 20;
                break;
            case LEFT:
                fx = x - 20;
                fy = y;
                break;
            case RIGHT:
                fx = x + 20;
                fy = y;
                break;

            default:
                fx = x;
                fy = y;
                break;
        }
        return new Pair<Float, Float>(fx, fy);
    }

    protected void forward() {
        Direction nextDirection = getNextDirection();
        if (direction == null) {
            direction = Direction.HOLD;
        }
        if (nextDirection != Direction.HOLD) {
            direction = nextDirection;
        }

        switch (nextDirection) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;

        }
        for (Tank tank : GameController.getInstance().getTanks()) {
            if (tank != this && GameLogic.isCollided(tank, this)) {
                unforward();
                break;
            }
        }
        for (Wall wall : GameController.getInstance().getWalls()) {
            if (GameLogic.isCollided(wall, this)) {
                unforward();
                break;
            }
        }
    }

    protected void unforward() {

        switch (direction) {
            case UP:
                y += speed;
                break;
            case DOWN:
                y -= speed;
                break;
            case LEFT:
                x += speed;
                break;
            case RIGHT:
                x -= speed;
                break;
            default:
                break;

        }
    }

    abstract protected Direction getNextDirection();

    public void setPlayerSide(boolean isPlayerSide) {
        this.isPlayerSide = isPlayerSide;
    }

    public int getShootCoolDown() {
        return shootCoolDown;
    }

    public void setShootCoolDown(int shootCoolDown) {
        this.shootCoolDown = shootCoolDown;
    }

    public int getShootCoolDownCounter() {
        return shootCoolDownCounter;
    }

    public void setShootCoolDownCounter(int shootCoolDownCounter) {
        this.shootCoolDownCounter = shootCoolDownCounter;
    }

    @Override
    public void hit() {
        draw(GameController.getInstance().getGC(), true);

    }

}
