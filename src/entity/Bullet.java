package entity;

import common.Constant;
import common.Direction;
import entity.Particle.BulletHitParticle;
import entity.Particle.BulletShootParticle;
import entity.base.MovableEntity;
import entity.base.Tank;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameUtil;

public class Bullet extends MovableEntity {

    private boolean isPlayerSide;
    private boolean isDestroyed;

    public Bullet(float x, float y, float speed, Direction direction, boolean isPlayerSide) {
        super(6, 6, x, y, speed, direction);
        this.isPlayerSide = isPlayerSide;
        GameController.getInstance().getBullets().add(this);
        isDestroyed = false;
        new BulletShootParticle(getX(), getY());

    }

    public boolean isPlayerSide() {
        return isPlayerSide;
    }

    @Override
    public void update() {

        switch (direction) {
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

        hitCheck();

        if (0 > x || x > Constant.GAME_WIDTH || 0 > y || y > Constant.GAME_HEIGHT) {
            return;
        }


    }

    @Override
    public void draw(GraphicsContext gc) {
        if (isDestroyed) {
            return;
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(x - width / 2, y - height / 2, width, height);

    }

    public void destroy() {
        GameController.getInstance().getBullets().remove(this);
        isDestroyed = true;
    }

    private void hitCheck() {
        for (Tank tank : GameController.getInstance().getTanks()) {
            if (tank.isPlayerSide() == isPlayerSide) {
                continue;
            }
            if (GameUtil.isCollided(tank, this)) {
                tank.hit();
                new BulletHitParticle(getX(), getY());
                GameController.getInstance().getBullets().remove(this);
                return;
            }
        }
        for (Wall wall : GameController.getInstance().getWalls()) {
            if (GameUtil.isCollided(wall, this)) {
                if (wall instanceof BreakableWall) {
                    ((BreakableWall) wall).hit();
                }
                new BulletHitParticle(getX(), getY());
                GameController.getInstance().getBullets().remove(this);
                return;
            }
        }
    }

}
