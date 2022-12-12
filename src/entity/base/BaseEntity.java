package entity.base;

import common.Updatable;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseEntity implements Updatable {
    protected int width;
    protected int height;
    protected float x;
    protected float y;
    protected int z;
    protected boolean visible;

    public BaseEntity(int width, int height, float x, float y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        visible = true;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public abstract void draw(GraphicsContext gc);

}
