package entity;

import entity.base.Tank;
import entity.interfaces.Hittable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;
import logic.GameUtil;
import sharedObject.RenderableHolder;

public class BreakableWall extends Wall implements Hittable {

    private int hp;
    private int maxHp;
    private boolean isBroken;

    private int respawnTime = 15 * 60;
    private int respawnTimeCounter = 0;

    public BreakableWall(float x, float y, int size, int hp) {
        super(x, y, size);
        this.hp = hp;
        this.maxHp = hp;
    }

    public BreakableWall(float x, float y, int size) {
        super(x, y, size);
        this.maxHp = 2;
        this.hp = maxHp;
    }

    @Override
    public void hit() {
        hp--;
        if (hp <= 0) {
            isBroken = true;
            respawnTimeCounter = respawnTime;
        }
    }

    @Override
    public void update() {
        if (isBroken) {
            if (respawnTimeCounter > 0) {
                respawnTimeCounter--;
            } else {
                isBroken = false;
                for (Tank tank : GameController.getInstance().getTanks()) {
                    if (GameUtil.isCollided(tank, this)) {
                        isBroken = true;
                        respawnTimeCounter = 60;
                        break;
                    }
                }
                hp = isBroken ? 0 : maxHp;
            }
        }
        super.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!isBroken) {
            Image wall = hp == maxHp ? RenderableHolder.wall1 : RenderableHolder.wall2;
            gc.drawImage(wall, x - size / 2, y - size / 2, size, size);
        }
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

}
