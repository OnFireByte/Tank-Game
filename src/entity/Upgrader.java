package entity;

import entity.base.BaseEntity;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Upgrader extends BaseEntity {

    private int expireTime;
    private int expireCounter;

    public Upgrader(float x, float y) {
        this(x, y, 60 * 10);
    }

    public Upgrader(float x, float y, int expireTime) {
        super(20, 20, x, y);
        this.expireTime = expireTime;
        expireCounter = expireTime;
        GameController.getInstance().getUpgraders().add(this);
    }

    public void decreaseExpireCounter() {
        setExpireCounter(expireCounter - 1);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.upgrade, x - width / 2, y - height / 2, width, height);
    }

    public int getExpireCounter() {
        return expireCounter;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public boolean isExpired() {
        return expireCounter <= 0;
    }

    public void setExpireCounter(int expireCounter) {
        if (expireCounter < 0) {
            expireCounter = 0;
        }
        this.expireCounter = expireCounter;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public void update() {
        if (isExpired()) {
            GameController.getInstance().getUpgraders().remove(this);
            return;
        }
        decreaseExpireCounter();
    }
}
