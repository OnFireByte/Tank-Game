package entity;

import common.Updatable;
import entity.base.BaseEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;

public class Wall extends BaseEntity implements Updatable {

    private int size;

    public Wall(float x, float y, int size) {
        super(size, size, x, y, 10);
        this.size = size;
        GameController.getInstance().getWalls().add(this);
    }

    @Override
    public int getZ() {
        return 10;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x - size / 2, y - size / 2, size, size);

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void update() {
        draw(GameController.getInstance().getGC());
    }

}
