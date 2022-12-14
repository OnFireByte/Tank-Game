package entity.base;

import common.Direction;
import entity.Bullet;
import entity.Wall;
import entity.Particle.ExplosionParticle;
import entity.interfaces.Hittable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.GameController;
import logic.GameUtil;
import sharedObject.RenderableHolder;

public abstract class Tank extends MovableEntity implements Hittable {

    protected int hitFrame = 15;
    protected int hitCounter = 0;
    protected int bulletSpeed = 10;
    protected boolean isAlive;
    protected boolean isPlayerSide;
    protected int shootCoolDown;
    protected int shootCoolDownCounter;
    protected Image sprite;

    protected int hp;
    protected int maxHp;

    public Tank(float x, float y, float speed, int maxHp, Direction direction, boolean isPlayerSide) {
        super(40, 40, x, y, 2, direction);
        this.isPlayerSide = isPlayerSide;
        shootCoolDown = 50;
        shootCoolDownCounter = 0;
        this.speed = speed;
        GameController.getInstance().getTanks().add(this);
        sprite = RenderableHolder.tank1;
        this.maxHp = maxHp;
        this.hp = maxHp;

    }

    public Tank(float x, float y, int maxHp, boolean isPlayerSide) {

        super(40, 40, x, y, 2, Direction.UP);
        this.isPlayerSide = isPlayerSide;
        shootCoolDown = 50;
        shootCoolDownCounter = 0;
        sprite = RenderableHolder.tank1;
        this.maxHp = maxHp;
        hp = maxHp;
    }

    @Override
    public void draw(GraphicsContext gc) {
        draw(gc, hitCounter > 0);
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
        gc.drawImage(isHitted ? RenderableHolder.tank1Hit : sprite, -width / 2, -height / 2, width,
                height);
        gc.restore();

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
            if (tank != this && GameUtil.isCollided(tank, this)) {
                unforward();
                break;
            }
        }
        for (Wall wall : GameController.getInstance().getWalls()) {
            if (GameUtil.isCollided(wall, this)) {
                unforward();
                break;
            }
        }
    }

    public Pair<Float, Float> getFacePos() {
        float fx;
        float fy;
        switch (direction) {
            case UP:
                fx = x;
                fy = y - height / 2;
                break;

            case DOWN:
                fx = x;
                fy = y + height / 2;
                break;
            case LEFT:
                fx = x - width / 2;
                fy = y;
                break;
            case RIGHT:
                fx = x + width / 2;
                fy = y;
                break;

            default:
                fx = x;
                fy = y;
                break;
        }
        return new Pair<>(fx, fy);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    abstract protected Direction getNextDirection();

    public int getShootCoolDown() {
        return shootCoolDown;
    }

    public int getShootCoolDownCounter() {
        return shootCoolDownCounter;
    }

    @Override
    public void hit() {
        hitCounter = hitFrame;
        draw(GameController.getInstance().getGC(), true);
        setHp(hp - 1);
        if (hp <= 0) {
            kill();
        }

    }

    public boolean isPlayerSide() {
        return isPlayerSide;
    }

    public void kill() {
        GameController.getInstance().getTanks().remove(this);
        isAlive = false;
        new ExplosionParticle(x, y);
    }

    public void setHp(int hp) {
        if (hp > maxHp) {
            hp = maxHp;
        }
        if (hp < 0) {
            hp = 0;
        }
        this.hp = hp;
    }

    public void setMaxHp(int hp) {
        if (hp < 1) {
            hp = 1;
        }
        this.maxHp = hp;
    }

    public void setPlayerSide(boolean isPlayerSide) {
        this.isPlayerSide = isPlayerSide;
    }

    public void setShootCoolDown(int shootCoolDown) {
        if (shootCoolDown < 0) {
            shootCoolDown = 0;
        }
        this.shootCoolDown = shootCoolDown;
    }

    public void setShootCoolDownCounter(int shootCoolDownCounter) {
        if (shootCoolDownCounter < 0) {
            shootCoolDownCounter = 0;
        }
        this.shootCoolDownCounter = shootCoolDownCounter;
    }

    protected void shoot() {
        if (shootCoolDownCounter > 0) {
            return;
        }
        Pair<Float, Float> pair = getFacePos();
        new Bullet(pair.getKey(), pair.getValue(), bulletSpeed, direction, isPlayerSide);

        shootCoolDownCounter = shootCoolDown;
    }

    abstract protected boolean shootInput();

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

    @Override
    public void update() {
        if (shootCoolDownCounter > 0) {
            shootCoolDownCounter--;
        }

        if (shootInput()) {
            shoot();
        }
        forward();
        if (hitCounter > 0) {
            hitCounter--;
        }

    }

}
