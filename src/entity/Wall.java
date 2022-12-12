package entity;

import entity.base.BaseEntity;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Wall extends BaseEntity {

    protected int size;

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
        gc.drawImage(RenderableHolder.unbreakableStone, x - size / 2, y - size / 2, size, size);

    }

    @Override
    public void update() {
    }

}
