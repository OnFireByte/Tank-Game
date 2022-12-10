package entity;

import java.util.concurrent.ThreadLocalRandom;

import common.Constant;
import common.Direction;
import entity.base.Tank;
import logic.GameController;
import logic.GameLogic;
import logic.InputUtil;

public class PlayerTank extends Tank {

    private int maxHpLevel;
    private int speedLevel;
    private int shootCoolDownLevel;
    private int sizeLevel;

    public PlayerTank(int x, int y, Direction direction) {
        super(x, y, 2, 3, direction, true);
        setMaxHpLevel(0);
        setSpeedLevel(0);
        setShootCoolDownLevel(0);
        setSizeLevel(0);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    protected Direction getNextDirection() {
        return InputUtil.getPlayerKeyDirection();
    }

    @Override
    protected boolean shootInput() {
        return InputUtil.isPlayerShoot();
    }

    @Override
    public void update() {

        for (Upgrader upgrader : GameController.getInstance().getUpgraders()) {
            if (GameLogic.isCollided(upgrader, this)) {
                GameController.getInstance().getUpgraders().remove(upgrader);
                upgrade();
                break;
            }
        }
        super.update();
    }

    private void upgrade() {
        switch (ThreadLocalRandom.current().nextInt(4)) {
            case 0:
                addMaxHpLevel(1);
                break;
            case 1:
                addSpeedLevel(1);
                break;
            case 2:
                addShootCoolDownLevel(1);
                break;
            case 3:
                addSizeLevel(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void hit() {
        // Invincible frame
        if (hitCounter > 0) {
            return;
        }
        super.hit();
    }

    @Override
    public void kill() {
    }

    public int getMaxHpLevel() {
        return maxHpLevel;
    }

    public void setMaxHpLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.maxHpLevel = level;

        int newMaxHp = Constant.PLAYER_TANK_MAX_HP_LEVEL.get(level);
        int diff = newMaxHp - maxHp;
        maxHp = newMaxHp;
        hp += diff;
    }

    public void addMaxHpLevel(int level) {
        setMaxHpLevel(maxHpLevel + level);
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.speedLevel = level;
        speed = Constant.PLAYER_TANK_SPEED_LEVEL.get(level);
    }

    public void addSpeedLevel(int level) {
        setSpeedLevel(speedLevel + level);
    }

    public int getShootCoolDownLevel() {
        return shootCoolDownLevel;
    }

    public void setShootCoolDownLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.shootCoolDownLevel = level;
        shootCoolDown = Constant.PLAYER_TANK_SHOOT_COOLDOWN_LEVEL.get(level);
    }

    public void addShootCoolDownLevel(int level) {
        setShootCoolDownLevel(shootCoolDownLevel + level);
    }

    public int getSizeLevel() {
        return sizeLevel;
    }

    public void setSizeLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.sizeLevel = level;
        width = Constant.PLAYER_TANK_SIZE_LEVEL.get(level);
        height = Constant.PLAYER_TANK_SIZE_LEVEL.get(level);
    }

    public void addSizeLevel(int level) {
        setSizeLevel(sizeLevel + level);
    }

}
