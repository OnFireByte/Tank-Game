package entity;

import common.AppConstant;
import common.Direction;
import entity.base.MovableEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameLogic;

public class Bullet extends MovableEntity {

    private boolean isPlayerSide;
    private boolean isDestroyed;

    public Bullet(float x, float y, float speed, Direction direction, boolean isPlayerSide) {
        super(6, 6, x, y, speed, direction);
        this.isPlayerSide = isPlayerSide;
        GameController.getInstance().getBullets().add(this);
        isDestroyed = false;

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

        if (0 > x || x > AppConstant.APP_WIDTH || 0 > y || y > AppConstant.APP_HEIGHT) {
            return;
        }

        draw(GameController.getInstance().getGC());

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
            if (tank.isDestroyed()) {
                continue;
            }
            if (GameLogic.isCollided(tank, this)) {
                tank.hit();
                GameController.getInstance().getBullets().remove(this);
                return;
            }
        }
        for (Wall wall : GameController.getInstance().getWalls()) {
            if (!(wall instanceof BreakableWall)) {
                continue;
            }

            if (GameLogic.isCollided(wall, this)) {
                ((BreakableWall) wall).hit();
                GameController.getInstance().getBullets().remove(this);
                return;
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

}
