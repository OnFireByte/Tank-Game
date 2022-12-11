package entity;

import entity.base.BaseEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Upgrader extends BaseEntity {

    private int expireTime;
    private int expireCounter;

    public Upgrader(float x, float y, int expireTime) {
        super(20, 20, x, y, 9);
        this.expireTime = expireTime;
        expireCounter = expireTime;
        GameController.getInstance().getUpgraders().add(this);
    }

    public Upgrader(float x, float y) {
        this(x, y, 60 * 10);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.upgrade, x - width / 2, y - height / 2, width, height);
    }

    @Override
    public boolean isDestroyed() {
        return !isExpired();
    }

    @Override
    public void update() {
        if (isExpired()) {
            GameController.getInstance().getUpgraders().remove(this);
            return;
        }
        decreaseExpireCounter();
        draw(GameController.getInstance().getGC());
    }

    public boolean isExpired() {
        return expireCounter <= 0;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public int getExpireCounter() {
        return expireCounter;
    }

    public void setExpireCounter(int expireCounter) {
        if (expireCounter < 0) {
            expireCounter = 0;
        }
        this.expireCounter = expireCounter;
    }

    public void decreaseExpireCounter() {
        setExpireCounter(expireCounter - 1);
    }
}
