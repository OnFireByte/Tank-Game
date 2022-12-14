package entity.base;

import common.Updatable;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseEntity implements Updatable {
    protected int width;
    protected int height;
    protected float x;
    protected float y;
    protected boolean visible;

    public BaseEntity(int width, int height, float x, float y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        visible = true;
    }

    public abstract void draw(GraphicsContext gc);

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

}
